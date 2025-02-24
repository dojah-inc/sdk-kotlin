package com.dojah.kyc_sdk_kotlin.ui.utils.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.CheckResult
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.ItemSpinnerBinding
import com.dojah.kyc_sdk_kotlin.databinding.PopupSpinnerBinding
import com.dojah.kyc_sdk_kotlin.databinding.WidgetEditTextSpinnerBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getColorBackground
import com.dojah.kyc_sdk_kotlin.ui.utils.hideSoftKeyboard
import com.dojah.kyc_sdk_kotlin.ui.utils.setText
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.SimpleEditTextSpinner.SpinnerAdapter.PlaceAutocomplete
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SimpleEditTextSpinner : LinearLayout {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<SpinnerAdapter.PlaceAutocomplete>() {
            override fun areItemsTheSame(
                oldItem: SpinnerAdapter.PlaceAutocomplete,
                newItem: SpinnerAdapter.PlaceAutocomplete
            ) =
                oldItem.placeId == newItem.placeId

            override fun areContentsTheSame(
                oldItem: SpinnerAdapter.PlaceAutocomplete,
                newItem: SpinnerAdapter.PlaceAutocomplete
            ) = oldItem == newItem
        }
    }

    val binding: WidgetEditTextSpinnerBinding =
        WidgetEditTextSpinnerBinding.inflate(LayoutInflater.from(context), this)

    private var itemSelected: Boolean = false

    private var spinnerLayout =
        PopupSpinnerBinding.inflate(LayoutInflater.from(context), null, false).apply {
            recyclerView.adapter = SpinnerAdapter {
                itemSelected = true
            }
        }

    private var strokeWidthFocused = 0

    private var strokeWidth = 0

    private var cornerSize = 0

    private var strokeColor: ColorStateList? = null

    private var textInputText = ""

    private var selectedPlaceId = ""
    var selectedPlace: Place? = null
    val popupWindow: PopupWindow?
        get() = spinnerPopup

    private val errorColor by lazy { ContextCompat.getColor(context, R.color.red) }

    var items: List<SpinnerAdapter.PlaceAutocomplete> = mutableListOf()
        set(value) {
            (field as MutableList<SpinnerAdapter.PlaceAutocomplete>).apply {
                clear()
                addAll(value)
//                setSelectedItem(field.find { it.placeId.equals("ng", ignoreCase = true) }!!)
                spinnerLayout.progressIndicator.isVisible = false
                (spinnerLayout.recyclerView.adapter as SpinnerAdapter).submitList(value)
            }
        }

    private var defaultInputType: Int = 0

    private var spinnerPopup: PopupWindow? = null

    var window: Window? = null

    var previousSearch: String = ""

    fun predictTextChange(fragment: Fragment) {
        binding.apply {
            edtLayoutSpinner.textChanges()
                .filterNot { it.isNullOrBlank() && itemSelected }
                .debounce(3000)
                .flatMapLatest {
                    flow {
                        if (itemSelected) {
                            itemSelected = false
                            emit(it to emptyList<PlaceAutocomplete>())
                        } else {
                            val results =
                                (spinnerLayout.recyclerView.adapter as SpinnerAdapter).getPredictions(
                                    it.toString()
                                )
                            previousSearch = it.toString()

                            emit(it to results)
                        }

                    }.flowOn(Dispatchers.IO)

                }
                .onEach {
                    if (it.second.isEmpty()) {
                        spinnerPopup?.dismiss()
                        return@onEach
                    } else {
                        changeDrawable()
                        if (it.second.isNotEmpty()) {
                            (spinnerLayout.recyclerView.adapter as SpinnerAdapter).submitList(it.second)
                            if (spinnerPopup?.isShowing == false) {

                                spinnerPopup?.showAsDropDown(
                                    binding.layoutSpinner,
                                    0,
                                    0,
                                    Gravity.BOTTOM
                                )
                            }
                        }
                    }
                }
                .launchIn(fragment.lifecycleScope)
        }
    }

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(
        context,
        attributeSet,
        0,
        0
    )

    constructor(context: Context, attributeSet: AttributeSet? = null, defAttrStyle: Int) : this(
        context,
        attributeSet,
        defAttrStyle,
        0
    )

    constructor(
        context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, R.attr.editTextSpinnerStyle) {

        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.SimpleEditTextSpinner,
            R.attr.editTextSpinnerStyle,
            defStyleRes
        ).use {
            strokeWidthFocused =
                it.getDimensionPixelSize(R.styleable.SimpleEditTextSpinner_sboxStrokeSizeFocused, 0)
            strokeWidth =
                it.getDimensionPixelSize(R.styleable.SimpleEditTextSpinner_sboxStrokeSize, 0)
            cornerSize =
                it.getDimensionPixelSize(R.styleable.SimpleEditTextSpinner_scornerLength, 0)
            strokeColor = it.getColorStateList(R.styleable.SimpleEditTextSpinner_sstrokeTint)
        }
    }

    init {
        orientation = VERTICAL

        binding.layoutSpinner.addOnEditTextAttachedListener {
            defaultInputType = it.editText!!.inputType
            it.clearOnEditTextAttachedListeners()
        }

        doOnAttach {
            setDrawable()
        }
    }


    private fun setDrawable(isErrorShown: Boolean = false) {
        binding.layoutRoot.background = MaterialShapeDrawable().apply {
            val color =
                if (isErrorShown) errorColor else this@SimpleEditTextSpinner.strokeColor!!.defaultColor
            setStrokeTint(color)
            strokeWidth = this@SimpleEditTextSpinner.strokeWidth.toFloat()
            setTint(Color.TRANSPARENT)

            setCornerSize(cornerSize.toFloat())
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setFocusListeners()

        setClickListeners()

        createPopupWindow()
    }

    private fun setFocusListeners() {
        binding.layoutSpinner.addOnEditTextAttachedListener {
            it.editText!!.setOnFocusChangeListener { _, hasFocus ->
                onFocusChange(hasFocus)
            }

            binding.layoutSpinner.clearOnEditTextAttachedListeners()
        }

        binding.layoutSpinner.setOnFocusChangeListener { _, hasFocus ->
            onFocusChange(hasFocus)
        }

        binding.layoutSpinner.setOnFocusChangeListener { _, hasFocus ->
            onFocusChange(hasFocus)
        }
    }

    private fun setClickListeners() {
        binding.layoutSpinner.setOnClickListener {
            onSpinnerClicked()
        }
    }

    private fun createPopupWindow() {
        doOnLayout {
            val popupHeight =
                context.resources.getDimension(R.dimen.height_dropdown_spinner_dialog).toInt()
            spinnerPopup = PopupWindow(
                spinnerLayout.root,
                measuredWidth,
                WindowManager.LayoutParams.WRAP_CONTENT
            ).apply {
                elevation = 1.0.toFloat()
                isOutsideTouchable = true
                isTouchable = true

                inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED;

                setOnDismissListener { onSpinnerDialogDismiss() }

                contentView.post {

                    setTouchInterceptor { _: View?, motionEvent: MotionEvent ->
                        val spinnerLoc = IntArray(2)
                        this@SimpleEditTextSpinner.getLocationOnScreen(spinnerLoc)

                        val popupLoc = IntArray(2)
                        contentView.getLocationOnScreen(popupLoc)
                        val x1 = contentView.x
                        val x2 = contentView.x + contentView.width
                        val y1 = contentView.y
                        val y2 = contentView.y + contentView.height
                        val y1WithTextField =
                            y1 + if (popupLoc[1] > spinnerLoc[1]) measuredHeight.unaryMinus() else 0
                        val y2WithTextField =
                            y2 + if (popupLoc[1] > spinnerLoc[1]) 0 else measuredHeight

                        val isWithinY = motionEvent.y in y1..y2
                        val isWithinTextFieldY: Boolean =
                            motionEvent.y in y1WithTextField..y2WithTextField
                        val isWithinX: Boolean = motionEvent.x in x1..x2

                        if (isWithinX && isWithinY) contentView.performClick()
                        else isWithinX && isWithinTextFieldY //it auto dismisses if false is returned
                    }
                }
            }
        }
    }

    private fun getPopupBackground(isAnchoredAtBottom: Boolean): MaterialShapeDrawable {
        return MaterialShapeDrawable().apply {
            val defaultColor = this@SimpleEditTextSpinner.strokeColor!!.defaultColor
            setStrokeTint(defaultColor)
            strokeWidth = this@SimpleEditTextSpinner.strokeWidth.toFloat()
            setTint(context.getColorBackground())

            shapeAppearanceModel = ShapeAppearanceModel.builder()
                .setTopRightCornerSize(if (isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setTopLeftCornerSize(if (isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setBottomLeftCornerSize(if (!isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setBottomRightCornerSize(if (!isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .build()
        }
    }

    private fun onFocusChange(hasFocus: Boolean) {
        val drawable = binding.layoutRoot.background as MaterialShapeDrawable
        val stroke = (if (hasFocus) strokeWidthFocused else strokeWidth).toFloat()
        val color = if (hasFocus) strokeColor!!.getColorForState(
            intArrayOf(android.R.attr.state_focused),
            0
        )
        else strokeColor!!.defaultColor

        binding.layoutRoot.background = drawable.apply {
            strokeWidth = stroke
            setStrokeTint(color)
        }
    }

    private fun onSpinnerClicked() {
        binding.apply {
            window?.let { hideSoftKeyboard(it) }

//            layoutSpinner.isVisible = false
//
//            layoutSpinner.setStartIconDrawable(R.drawable.ic_search)
//            layoutSpinner.isHintEnabled = false
//            layoutSpinner.hint = context.getString(R.string.search)
//            layoutSpinner.placeholderText = context.getString(R.string.search_country)
//            layoutSpinner.prefixText = null
//            textInputText = binding.layoutSpinner.getText()
//            layoutSpinner.editText?.inputType = InputType.TYPE_CLASS_TEXT
//            layoutSpinner.editText?.text = null

        }
    }

    private fun changeDrawable() {
        spinnerPopup?.contentView?.post {

            val spinnerLoc = IntArray(2)
            this@SimpleEditTextSpinner.getLocationOnScreen(spinnerLoc)

            val popupLoc = IntArray(2)
            spinnerPopup?.contentView?.getLocationOnScreen(popupLoc)

            spinnerLayout.root.background = getPopupBackground(popupLoc[1] > spinnerLoc[1])
            updateBackgroundDrawable(true, popupLoc[1] > spinnerLoc[1])
        }
    }

    private fun onSpinnerDialogDismiss() {
        binding.apply {
            layoutSpinner.isVisible = true

            layoutSpinner.startIconDrawable = null
            layoutSpinner.isHintEnabled = false
            layoutSpinner.hint = null
            layoutSpinner.placeholderText = null

            layoutSpinner.editText?.inputType = defaultInputType
            layoutSpinner.setText(textInputText)

            updateBackgroundDrawable(false, false)
        }
    }

    private fun updateBackgroundDrawable(isSpinnerShown: Boolean, isAnchoredAtBottom: Boolean) {
        (binding.layoutRoot.background.mutate() as MaterialShapeDrawable).apply {

            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setBottomLeftCornerSize(if (isSpinnerShown && isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setBottomRightCornerSize(if (isSpinnerShown && isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setTopLeftCornerSize(if (isSpinnerShown && !isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setTopRightCornerSize(if (isSpinnerShown && !isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .build()

            binding.layoutRoot.background = this
        }
    }

    private fun setSelectedItem(item: SpinnerAdapter.PlaceAutocomplete, place: Place) {
        binding.apply {
            selectedPlaceId = item.placeId.toString()
            selectedPlace = place
            HttpLoggingInterceptor.Logger.DEFAULT.log("lat_lang is:${place.latLng}")

            itemSelected = true
            layoutSpinner.setText(item.address)
        }
    }

    fun showError(text: String) {
        binding.apply {
            setDrawable(isErrorShown = true)
            textError.setTextColor(errorColor)
            textError.text = text
            textError.isVisible = true
        }
    }

    fun clearError() {
        binding.apply {
            setDrawable()
            textError.setTextColor(errorColor)
            textError.text = null
            textError.isVisible = false
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        window = null
    }

    inner class SpinnerAdapter(val onItemClicked: (place: PlaceAutocomplete) -> Unit) :
        ListAdapter<SpinnerAdapter.PlaceAutocomplete, SpinnerAdapter.ViewHolder>(DIFF_UTIL),
        Filterable {
        private val placesClient: PlacesClient = Places.createClient(context)
        private var mResultList = ArrayList<PlaceAutocomplete>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false).run {
                ViewHolder(this)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.apply {
                val item = currentList[position]
                imageFlag.visibility = View.GONE
                textName.text = item.address
            }
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence): FilterResults {
                    val results = FilterResults()
                    // Skip the autocomplete query if no constraints are given.
                    // Query the autocomplete API for the (constraint) search string.
                    mResultList = getPredictions(constraint)
                    items = mResultList
                    // The API successfully returned results.
                    results.values = mResultList
                    results.count = mResultList.size
                    return results
                }

                override fun publishResults(constraint: CharSequence, results: FilterResults) {
                    if (results.count > 0) {
                        // The API returned at least one result, update the data.
                        notifyDataSetChanged()
                        if (constraint.toString().toList().size == 1) {
                            spinnerPopup?.showAsDropDown(
                                binding.layoutSpinner,
                                0,
                                0,
                                Gravity.BOTTOM
                            )
                        }
                    } else {
                        // The API did not return any results, invalidate the data set.
                        //notifyDataSetInvalidated();
                    }
                }
            }
        }

        fun getPredictions(constraint: CharSequence): ArrayList<PlaceAutocomplete> {
            val resultList = ArrayList<PlaceAutocomplete>()

            // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
            // and once again when the user makes a selection (for example when calling fetchPlace()).
            val token = AutocompleteSessionToken.newInstance()

            val request =
                FindAutocompletePredictionsRequest.builder() // Call either setLocationBias() OR setLocationRestriction().
                    //.setLocationBias(bounds)
                    //.setCountry("BD")
//                    .setTypeFilter(TypeFilter.ADDRESS)
                    .setSessionToken(token)
                    .setQuery(constraint.toString())
                    .build()
            val autocompletePredictions: Task<FindAutocompletePredictionsResponse> =
                placesClient.findAutocompletePredictions(request)

            // This method should have been called off the main UI thread. Block and wait for at most
            // 60s for a result from the API.
            try {
                Tasks.await(autocompletePredictions, 60, TimeUnit.SECONDS)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: TimeoutException) {
                e.printStackTrace()
            }
            return if (autocompletePredictions.isSuccessful) {
                val findAutocompletePredictionsResponse: FindAutocompletePredictionsResponse =
                    autocompletePredictions.result
                for (prediction in findAutocompletePredictionsResponse.autocompletePredictions) {
                    resultList.add(
                        PlaceAutocomplete(
                            prediction.placeId,
                            prediction.getPrimaryText(StyleSpan(Typeface.NORMAL)).toString(),
                            prediction.getFullText(StyleSpan(Typeface.BOLD)).toString()
                        )
                    )
                }
                resultList
            } else {
                resultList
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val binding = ItemSpinnerBinding.bind(view)


            init {
                itemView.setOnClickListener {
                    val item = currentList[bindingAdapterPosition]
                    onItemClicked(item)
                    val placeId: String = java.lang.String.valueOf(item.placeId)
                    val placeFields: List<Place.Field> = listOf(
                        Place.Field.ID,
                        Place.Field.NAME,
                        Place.Field.LAT_LNG,
                        Place.Field.ADDRESS
                    )
                    val request = FetchPlaceRequest.builder(placeId, placeFields).build()
                    placesClient.fetchPlace(request)
                        .addOnSuccessListener { response ->
                            val place: Place = response.place
                            setSelectedItem(item, place)
                        }.addOnFailureListener { exception ->
                            if (exception is ApiException) {
                                Toast.makeText(context, exception.message + "", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    spinnerPopup?.dismiss()

                }
            }
        }

        /**
         * Holder for Places Geo Data Autocomplete API results.
         */
        inner class PlaceAutocomplete constructor(
            var placeId: CharSequence,
            var area: CharSequence,
            var address: CharSequence
        ) {
            override fun toString(): String {
                return area.toString()
            }


            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as PlaceAutocomplete

                if (placeId != other.placeId) return false
                if (area != other.area) return false
                if (address != other.address) return false

                return true
            }

            override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + area.hashCode()
                result = 31 * result + address.hashCode()
                return result
            }
        }
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                HttpLoggingInterceptor.Logger.DEFAULT.log("afterTextChanged: $s")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                HttpLoggingInterceptor.Logger.DEFAULT.log("beforeTextChanged: $s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                HttpLoggingInterceptor.Logger.DEFAULT.log("onTextChanged: $s")

                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

