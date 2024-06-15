package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.devs.vectorchildfinder.VectorChildFinder
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.DialogDisclaimerBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.*
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class IndexDisclaimerFragment : ErrorFragment(R.layout.dialog_disclaimer) {
    private val binding by viewBinding { DialogDisclaimerBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvents(KycPages.INDEX.serverKey)
//        viewModel.eventLiveData.observe(this) {
//            if (it.second is Result.Loading) {
//                showLoading("Loading...")
//            } else {
//                dismissLoading()
//                if (it.second is Result.Success) {
//                    when (it.first.eventType) {
//                        EventTypes.STEP_COMPLETED.serverKey -> {
//                            navViewModel.navigateNextStep()
//                        }
//                    }
//                } else {
//                    navViewModel.navigate(Routes.error_fragment)
//                }
//            }
//        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        binding.apply {

            val vector =
                VectorChildFinder(requireContext(), R.drawable.ic_verification_avatar, logo)

            SharedPreferenceManager(requireContext()).getMaterialButtonBgColor?.also {
                val color = Color.parseColor(it)
                vector.findPathByName("path1").fillColor = color
                vector.findPathByName("path1").fillAlpha = 0.4f
                vector.findPathByName("path2").strokeColor = color
                vector.findPathByName("path3").fillColor = color
            }

            logo.invalidate()

            btnContinue.setOnClickListener {
                val stepNumber = viewModel.getStepWithPageName(KycPages.INDEX.serverKey)?.id
                    ?: throw Exception("No stepNumber")

                viewModel.logEvent(
                    EventTypes.STEP_COMPLETED.serverKey,
                    KycPages.INDEX.serverKey,
                    stepNumber = stepNumber,
                )
            }

        }
    }

}


/**
 * [Drawable] helper class.
 *
 * @author Filipe Bezerra
 * @version 18/01/2016
 * @since 18/01/2016
 */
class DrawableHelper(var mContext: Context) {
    private var mColor = ContextCompat.getColor(mContext, (R.color.red))
    private var mDrawable: Drawable? = null
    private var mWrappedDrawable: Drawable? = null
    fun withDrawable(@DrawableRes drawableRes: Int): DrawableHelper {
        mDrawable = ContextCompat.getDrawable(mContext, drawableRes)
        return this
    }

    fun withDrawable(drawable: Drawable): DrawableHelper {
        mDrawable = drawable
        return this
    }

    fun withColor(@ColorRes colorRes: Int): DrawableHelper {
        mColor = ContextCompat.getColor(mContext, colorRes)
        return this
    }

    fun tint(): DrawableHelper {
        if (mDrawable == null) {
            throw NullPointerException("É preciso informar o recurso drawable pelo método withDrawable()")
        }
        mWrappedDrawable = mDrawable!!.mutate()
        mWrappedDrawable = DrawableCompat.wrap(mWrappedDrawable!!)
        DrawableCompat.setTint(mWrappedDrawable!!, mColor)
        DrawableCompat.setTintMode(mWrappedDrawable!!, PorterDuff.Mode.ADD)
        return this
    }

    @Suppress("deprecation")
    fun applyToBackground(view: View) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        view.background = mWrappedDrawable
    }

    fun applyTo(imageView: ImageView) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        imageView.setImageDrawable(mWrappedDrawable)
    }

    fun applyTo(menuItem: MenuItem) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        menuItem.icon = mWrappedDrawable
    }

    fun get(): Drawable? {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        return mWrappedDrawable
    }

    companion object {
        fun withContext(context: Context): DrawableHelper {
            return DrawableHelper(context)
        }
    }
}
