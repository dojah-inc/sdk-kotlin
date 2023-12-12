package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentPreviewSelfieBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.load
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PreviewSelfieFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentPreviewSelfieBinding.bind(it) }


    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.selfie_nav_graph) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_selfie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            val type = govViewModel.verificationTypeLiveData.value
            title.text = type?.preview

            val radius = 20f

            val decorView = cardPreview
            // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
            val rootView: ViewGroup = cardPreview
            val windowBackground: Drawable? = decorView.background


            val uri: Uri? =
                viewModel.selfieUriLiveData.value

            if (type == VerificationType.Video) {
                videoPreview.isVisible = true
                cameraPreview.isVisible = false
                videoPreview.setVideoURI(uri)
//                val mediaController = MediaController(context)
//                mediaController.setAnchorView(videoPreview)
//                mediaController.setMediaPlayer(videoPreview)
//                videoPreview.setMediaController(mediaController)
                videoPreview.start()
            } else {
                cameraPreview.load(uri, isCenterCrop = true)
            }

            btnRetake.setOnClickListener {
                navViewModel.popBackStack()
            }
            btnContinue.setOnClickListener {
                if (type == VerificationType.Video) {
                    videoPreview.pause()

                    val mediaReceiver = MediaMetadataRetriever()
                    mediaReceiver.setDataSource(requireContext(), uri)
                    val frameAtTime =
                        mediaReceiver.getFrameAtTime(videoPreview.currentPosition.toLong())
                    videoPreview.isVisible = false
                    cameraPreview.isVisible = true
                    cameraPreview.load(frameAtTime, isCenterCrop = true)

                }
                blurView.setupWith(
                    rootView,
                    RenderScriptBlur(requireContext())
                ) // or RenderEffectBlur
                    .setFrameClearDrawable(windowBackground) // Optional
                    .setBlurRadius(radius)
                processing.visibility = View.VISIBLE
                viewModel.viewModelScope.launch {
                    delay(2000)
                    processing.visibility = View.GONE
                    ///go to success screen
                    navViewModel.navigateOld(R.id.frag_success, Bundle().apply {
                        putString(
                            Constants.SUCCESS_BUNDLE,
                            getString(R.string.success_msg)
                        )
                    })
                }

            }
        }
    }
}