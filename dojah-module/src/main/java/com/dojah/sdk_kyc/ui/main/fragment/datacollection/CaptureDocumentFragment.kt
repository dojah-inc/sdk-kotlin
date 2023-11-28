package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCaptureDocumentBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CameraPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.openAppSystemSettings
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import javax.inject.Inject


@AndroidEntryPoint
class CaptureDocumentFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureDocumentBinding.bind(it) }

    private lateinit var cameraContract: ActivityResultLauncher<String>

    private var imageCapture: ImageCapture? = null

    private var cameraProvider: ProcessCameraProvider? = null


    private lateinit var permissionContract: ActivityResultLauncher<Array<String>>

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_id_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var currentPermission: String? = null
    private lateinit var documentContract: ActivityResultLauncher<Array<String>>
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireActivity())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            cameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.camera.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider?.unbindAll()

                // Bind use cases to camera
                cameraProvider?.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Timber.e("Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(this.requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cameraContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    startCamera()
                } else {
//                    cameraContract.launch(Manifest.permission.CAMERA)
                    showPermissionError()
                }
            }

        cameraContract.launch(Manifest.permission.CAMERA)
        binding.apply {
            val selectedDoc = viewModel.docTypeLiveData.value
            if (selectedDoc != GovDocType.DRIVER_LICENCE) {
                title.text = selectedDoc?.title
                infoText.text = selectedDoc?.info
            }
            camera.setOnClickListener {
                cameraContract.launch(Manifest.permission.CAMERA)
            }
            captureBtn.setOnClickListener {

                val photoFile =
                    File.createTempFile("driveLicence", ".jpg", requireContext().cacheDir)

                // Create output options object which contains file + metadata
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                imageCapture?.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(requireContext()),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exc: ImageCaptureException) {
                            cameraProvider?.unbindAll()
                            Toast.makeText(requireContext(), exc.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                            cameraProvider?.unbindAll()

                            val savedUri = Uri.fromFile(photoFile)
                            viewModel.setFrontDocUri(savedUri)
                            navViewModel.navigate(R.id.frag_preview_doc)

                        }
                    })

            }

            uploadBtn.setOnClickListener {
                navViewModel.navigate(R.id.frag_upload_front_doc)
            }

        }
    }


    private fun showPermissionError() {

        CameraPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = {
                requireContext().openAppSystemSettings()
            }
            onExitClick = {
//                navViewModel.popBackStack()
            }

            show(this@CaptureDocumentFragment.childFragmentManager, null)
        }
    }
}