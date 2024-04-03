package com.dojah.sdk_kyc.ui.utils.widget

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.text.toSpannable
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.ItemSpinnerBinding
import com.dojah.sdk_kyc.databinding.PopupSpinnerBinding
import com.dojah.sdk_kyc.databinding.WidgetEditTextWithCountryBinding
import com.dojah.sdk_kyc.domain.Country
import com.dojah.sdk_kyc.ui.utils.*

class EditTextSpinner : LinearLayout {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country) =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
        }
    }

    val binding: WidgetEditTextWithCountryBinding =
        WidgetEditTextWithCountryBinding.inflate(LayoutInflater.from(context), this)

    private var spinnerLayout =
        PopupSpinnerBinding.inflate(LayoutInflater.from(context), null, false).apply {
            recyclerView.adapter = SpinnerAdapter()
        }

    val spinnerPopUp: PopupWindow
        get() = spinnerPopup

    val editText: EditText
        get() = binding.textInputDigits.editText!!

    private var strokeWidthFocused = 0

    private var strokeWidth = 0

    private var cornerSize = 0

    private var strokeColor: ColorStateList? = null

    private var textInputText = ""

    private var selectedCountryId = ""
    private var selectedCountry: Country? = null

    private val errorColor by lazy { ContextCompat.getColor(context, R.color.red) }

    var isFlagViewEnabled = true
        set(value) {
            field = value

            binding.layoutSpinner.isEnabled = value
        }

    var items: List<Country> = mutableListOf()
        set(value) {
            (field as MutableList<Country>).apply {
                clear()
                addAll(value)
                setSelectedItem(field.find { it.id.equals("ng", ignoreCase = true) }!!)
                spinnerLayout.progressIndicator.isVisible = false
                (spinnerLayout.recyclerView.adapter as SpinnerAdapter).submitList(value)
            }
        }

    private var defaultInputType: Int = 0

    private lateinit var spinnerPopup: PopupWindow

    var window: Window? = null

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
            R.styleable.EditTextSpinner,
            R.attr.editTextSpinnerStyle,
            defStyleRes
        ).use {
            strokeWidthFocused =
                it.getDimensionPixelSize(R.styleable.EditTextSpinner_boxStrokeSizeFocused, 0)
            strokeWidth = it.getDimensionPixelSize(R.styleable.EditTextSpinner_boxStrokeSize, 0)
            cornerSize = it.getDimensionPixelSize(R.styleable.EditTextSpinner_cornerLength, 0)
            strokeColor = it.getColorStateList(R.styleable.EditTextSpinner_strokeTint)
        }
    }

    init {
        orientation = VERTICAL

        binding.textInputDigits.addOnEditTextAttachedListener {
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
                if (isErrorShown) errorColor else this@EditTextSpinner.strokeColor!!.defaultColor
            setStrokeTint(color)
            strokeWidth = this@EditTextSpinner.strokeWidth.toFloat()
            setTint(Color.TRANSPARENT)

            setCornerSize(cornerSize.toFloat())

            binding.divider.updateLayoutParams<LayoutParams> {
                width = strokeWidth.toInt() * 2
            }
            binding.divider.setBackgroundColor(color)
        }
        binding.textInputDigits.background = MaterialShapeDrawable().apply {
            val color =
                if (isErrorShown) errorColor else this@EditTextSpinner.strokeColor!!.defaultColor
            setStrokeTint(color)
            strokeWidth = this@EditTextSpinner.strokeWidth.toFloat()
            setTint(Color.TRANSPARENT)
        }
        (binding.textInputDigits.background.mutate() as MaterialShapeDrawable).apply {

            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setBottomLeftCornerSize(0F)
                .setBottomRightCornerSize(cornerSize.toFloat())
                .setTopLeftCornerSize(0F)
                .setTopRightCornerSize(cornerSize.toFloat())
                .build()

            binding.textInputDigits.background = this
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setFocusListeners()

        setClickListeners()

        createPopupWindow()
    }

    private fun setFocusListeners() {
        binding.textInputDigits.addOnEditTextAttachedListener {
            it.editText!!.setOnFocusChangeListener { _, hasFocus ->
                onFocusChange(hasFocus)
            }

            binding.textInputDigits.clearOnEditTextAttachedListeners()
        }

        binding.textInputDigits.setOnFocusChangeListener { _, hasFocus ->
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
                inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
                setOnDismissListener { onSpinnerDialogDismiss() }

                contentView.post {

                    setTouchInterceptor { _: View?, motionEvent: MotionEvent ->
                        val spinnerLoc = IntArray(2)
                        this@EditTextSpinner.getLocationOnScreen(spinnerLoc)

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
            val defaultColor = this@EditTextSpinner.strokeColor!!.defaultColor
            setStrokeTint(defaultColor)
            strokeWidth = this@EditTextSpinner.strokeWidth.toFloat()
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

        binding.divider.updateLayoutParams<LayoutParams> {
            width = stroke.toInt()
        }
        binding.divider.setBackgroundColor(color)
    }

    private fun onSpinnerClicked() {
        binding.apply {
            window?.let { hideSoftKeyboard(it) }

            layoutSpinner.isVisible = false
            divider.isVisible = false

            textInputDigits.setStartIconDrawable(R.drawable.ic_search)
            textInputDigits.isHintEnabled = false
            textInputDigits.hint = context.getString(R.string.search)
            textInputDigits.placeholderText = context.getString(R.string.search_country)
            textInputDigits.prefixText = null
            textInputText = binding.textInputDigits.getText()
            textInputDigits.editText?.inputType = InputType.TYPE_CLASS_TEXT
            textInputDigits.editText?.text = null

            textInputDigits.editText!!.addTextChangedListener { text ->
                val filteredList =
                    items.filter { it.name.contains(text.toString(), ignoreCase = true) }

                (spinnerLayout.recyclerView.adapter as SpinnerAdapter).submitList(filteredList)
            }

            changeDrawable()

            if (spinnerPopup.isShowing) {
                spinnerPopup.dismiss()
            } else {
                spinnerPopup.showAsDropDown(layoutSpinner, 0, 0, Gravity.BOTTOM)
            }
        }
    }

    private fun changeDrawable() {
        spinnerPopup.contentView.post {

            val spinnerLoc = IntArray(2)
            this@EditTextSpinner.getLocationOnScreen(spinnerLoc)

            val popupLoc = IntArray(2)
            spinnerPopup.contentView.getLocationOnScreen(popupLoc)

            spinnerLayout.root.background = getPopupBackground(popupLoc[1] > spinnerLoc[1])
            updateBackgroundDrawable(true, popupLoc[1] > spinnerLoc[1])
        }
    }

    private fun onSpinnerDialogDismiss() {
        binding.apply {
            layoutSpinner.isVisible = true
            divider.isVisible = true

            textInputDigits.startIconDrawable = null
            textInputDigits.isHintEnabled = false
            textInputDigits.hint = null
            textInputDigits.placeholderText = null

            textInputDigits.editText?.inputType = defaultInputType
            textInputDigits.setText(textInputText)

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

    private fun setSelectedItem(item: Country) {
        binding.apply {
            selectedCountry = item
            selectedCountryId = item.id
            code.text = item.code.removePrefix("+")
            imageCountry.load(item.path)
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

    fun getText() = binding.textInputDigits.getText(withPrefix = false, withoutWhitespace = true).trim()
    fun getTextWithPrefix() = if (getText().startsWith("0")) {
        val alteredPhoneNumber = getText().replaceFirst("0", selectedCountry?.code ?: "")
//        logger.log("sendOtp alteredPhoneNumber @ 0: $alteredPhoneNumber")
        alteredPhoneNumber
    } else if (getText().length <= 10) {
        val alteredPhone = "${selectedCountry?.code ?: ""}${getText()}".trim()
//        logger.log("sendOtp alteredPhone @ <=10: $alteredPhone")
        alteredPhone
    } else {
        getText()
    }


    fun setText(phone: String) {
        binding.textInputDigits.setText(phone)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        window = null
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

                (item.name + "  " + item.code).toSpannable().apply {
                    setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(
                                context,
                                R.color.color_text_alpha_30
                            )
                        ),
                        indexOf(item.code), indexOf(item.code) + item.code.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    textName.text = this
                }

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