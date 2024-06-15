package com.dojah_inc.dojah_android_sdk.ui.utils.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.window.OnBackInvokedDispatcher
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.use
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.ItemSpinnerBinding
import com.dojah_inc.dojah_android_sdk.databinding.PopupSpinnerBinding
import com.dojah_inc.dojah_android_sdk.databinding.WidgetSpinnerCountryBinding
import com.dojah_inc.dojah_android_sdk.domain.Country
import com.dojah_inc.dojah_android_sdk.ui.utils.*
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import okhttp3.logging.HttpLoggingInterceptor


class CountryPickSpinner : LinearLayout {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country) =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
        }
    }

    val binding = WidgetSpinnerCountryBinding.inflate(LayoutInflater.from(context), this)

    private var spinnerLayout =
        PopupSpinnerBinding.inflate(LayoutInflater.from(context), null, false).apply {
            edtSearchInput.isVisible = true
            edtSearchInput.editText?.addTextChangedListener { edt ->
                val filteredList =
                    items.filter { it.name.contains(edt.toString(), ignoreCase = true) }

                (recyclerView.adapter as SpinnerAdapter).submitList(filteredList)
            }
            edtSearchInput.editText?.setOnClickListener {
                edtSearchInput.editText?.clearFocus()
                edtSearchInput.editText?.requestFocus()
                HttpLoggingInterceptor.Logger.DEFAULT.log("edt clicked")
                val inputMgr =
                    getSystemService(context, InputMethodManager::class.java)
                inputMgr?.showSoftInput(edtSearchInput.editText, InputMethodManager.SHOW_IMPLICIT)
            }
            recyclerView.adapter = SpinnerAdapter()
        }

    private var strokeWidthFocused = 0

    private var strokeWidth = 0

    private var cornerSize = 0

    private var strokeColor: ColorStateList? = null

    private var textInputText = ""

    private var selectedCountryId = ""

    private val errorColor by lazy { ContextCompat.getColor(context, R.color.red) }

    var isFlagViewEnabled = true
        set(value) {
            field = value

            binding.layoutSpinner.isEnabled = value
        }

    val spinnerPopUp: PopupWindow
        get() = spinnerPopup

    var items: List<Country> = mutableListOf()
        set(value) {
            (field as MutableList<Country>).apply {
                clear()
                addAll(value)
                field.find { it.selected }?.let { setSelectedItem(it) }
                spinnerLayout.progressIndicator.isVisible = false
                (spinnerLayout.recyclerView.adapter as SpinnerAdapter).submitList(value)
            }
        }

    private var defaultInputType: Int = 0

    private lateinit var spinnerPopup: PopupWindow


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
            R.styleable.CountryPickSpinner,
            R.attr.editTextSpinnerStyle,
            defStyleRes
        ).use {
            strokeWidthFocused =
                it.getDimensionPixelSize(R.styleable.CountryPickSpinner_spinBoxStrokeSizeFocused, 0)
            strokeWidth =
                it.getDimensionPixelSize(R.styleable.CountryPickSpinner_spinBoxStrokeSize, 0)
            cornerSize =
                it.getDimensionPixelSize(R.styleable.CountryPickSpinner_spinCornerLength, 0)
            strokeColor = it.getColorStateList(R.styleable.CountryPickSpinner_spinStrokeTint)
        }
    }

    init {
        orientation = VERTICAL


        doOnAttach {
            setDrawable()
        }
    }

    private fun setDrawable(isErrorShown: Boolean = false) {
        binding.layoutRoot.background = MaterialShapeDrawable().apply {
            val color =
                if (isErrorShown) errorColor else this@CountryPickSpinner.strokeColor!!.defaultColor
            setStrokeTint(color)
            strokeWidth = this@CountryPickSpinner.strokeWidth.toFloat()
            setTint(ContextCompat.getColor(context, R.color.card_back_color))

            setCornerSize(cornerSize.toFloat())

        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()


        setClickListeners()

        createPopupWindow()
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
            spinnerPopup = PopupWindow(spinnerLayout.root, measuredWidth, popupHeight).apply {
                elevation = 1.0.toFloat()
                isOutsideTouchable = false
                isTouchable = true
                isFocusable = true
//                update()

//                inputMethodMode = PopupWindow.INPUT_METHOD_FROM_FOCUSABLE
//                softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE

                setOnDismissListener { onSpinnerDialogDismiss() }

                contentView.post {
//
//                    setTouchInterceptor { _: View?, motionEvent: MotionEvent ->
//                        val spinnerLoc = IntArray(2)
//                        this@CountryPickSpinner.getLocationOnScreen(spinnerLoc)
//
//                        val popupLoc = IntArray(2)
//                        contentView.getLocationOnScreen(popupLoc)
//                        val x1 = contentView.x
//                        val x2 = contentView.x + contentView.width
//                        val y1 = contentView.y
//                        val y2 = contentView.y + contentView.height
//                        val y1WithTextField =
//                            y1 + if (popupLoc[1] > spinnerLoc[1]) measuredHeight.unaryMinus() else 0
//                        val y2WithTextField =
//                            y2 + if (popupLoc[1] > spinnerLoc[1]) 0 else measuredHeight
//
//                        val isWithinY = motionEvent.y in y1..y2
//                        val isWithinTextFieldY: Boolean =
//                            motionEvent.y in y1WithTextField..y2WithTextField
//                        val isWithinX: Boolean = motionEvent.x in x1..x2
//
//                        if (isWithinX && isWithinY) contentView.performClick()
//                        else isWithinX && isWithinTextFieldY //it auto dismisses if false is returned
//                    }
                }
            }
        }
    }

    private fun getPopupBackground(isAnchoredAtBottom: Boolean): MaterialShapeDrawable {
        return MaterialShapeDrawable().apply {
            val defaultColor = this@CountryPickSpinner.strokeColor!!.defaultColor
            setStrokeTint(defaultColor)
            strokeWidth = this@CountryPickSpinner.strokeWidth.toFloat()
            setTint(context.getColorBackground())

            shapeAppearanceModel = ShapeAppearanceModel.builder()
                .setTopRightCornerSize(if (isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setTopLeftCornerSize(if (isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setBottomLeftCornerSize(if (!isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .setBottomRightCornerSize(if (!isAnchoredAtBottom) 0f else cornerSize.toFloat())
                .build()
        }
    }

    override fun findOnBackInvokedDispatcherForChild(
        child: View,
        requester: View
    ): OnBackInvokedDispatcher? {
        if (spinnerPopup.isShowing) {
            spinnerPopup.dismiss()
            return super.findOnBackInvokedDispatcherForChild(child, requester)
        }
        return super.findOnBackInvokedDispatcherForChild(child, requester)
    }

    private fun onSpinnerClicked() {
        binding.apply {
            changeDrawable()

            if (spinnerPopup.isShowing) {
                spinnerPopup.dismiss()
            } else {
//                spinnerPopup.showAtLocation(layoutSpinner, Gravity.CENTER, 0, 0)
                spinnerPopup.showAsDropDown(layoutSpinner, 0, 0, Gravity.BOTTOM)
//                spinnerPopUp.contentView?.clearFocus()
//                spinnerPopUp.contentView.findViewById<AutoCompleteTextView>(R.id.edt_search)?.requestFocus()

            }

        }
    }

    private fun changeDrawable() {
        spinnerPopup.contentView.post {

            val spinnerLoc = IntArray(2)
            this@CountryPickSpinner.getLocationOnScreen(spinnerLoc)

            val popupLoc = IntArray(2)
            spinnerPopup.contentView.getLocationOnScreen(popupLoc)

            spinnerLayout.root.background = getPopupBackground(popupLoc[1] > spinnerLoc[1])
            updateBackgroundDrawable(true, popupLoc[1] > spinnerLoc[1])
        }
    }

    private fun onSpinnerDialogDismiss() {
        binding.apply {
//            layoutSpinner.isVisible = true


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

    var onCountrySelected: ((country: Country) -> Unit)? = null

    fun setSelectedItem(item: Country) {
        binding.apply {
            selectedCountryId = item.id
            countryName.text = item.name
            imageCountry.load(item.path)
            onCountrySelected?.let { it(item) }
        }
    }

    fun showError(text: String) {
        binding.apply {
            setDrawable(isErrorShown = true)
            textError.setTextColor(errorColor)
            textError.text = text
//            textError.isVisible = true
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


    private inner class SpinnerAdapter :
        ListAdapter<Country, SpinnerAdapter.ViewHolder>(DIFF_UTIL) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false).run {
                ViewHolder(this)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.apply {
                val item = currentList[position]

                if (item.selected) {
                    setSelectedItem(currentList[position])
                }
                textName.text = item.name

                imageFlag.load(item.path)
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val binding = ItemSpinnerBinding.bind(view)

            init {
                itemView.setOnClickListener {
                    setSelectedItem(currentList[bindingAdapterPosition])
                    spinnerPopup.dismiss()
                }
            }
        }
    }
}