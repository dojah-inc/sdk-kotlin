package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.LocationManager
import com.dojah.sdk_kyc.databinding.FragmentHomeAddressBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CameraPermissionDialogFragment
import com.dojah.sdk_kyc.ui.dialog.LocationPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class HomeAddressFragment : ErrorFragment(R.layout.fragment_home_address) {
    private val binding by viewBinding { FragmentHomeAddressBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val navViewModel by activityViewModels<NavigationViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    @Inject
    lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Places.initialize(context, resources.getString(R.string.prediction_api_key))
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                val isPermitted = it.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    false
                ) || it.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)
                locationManager.hasPermission = isPermitted
                if (isPermitted) {
                    locationManager.startLocationUpdates()
                } else {
                    showPermissionError(onAllowClicked = {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        )
                    })
                }
            }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            showPermissionError(onAllowClicked = {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            })
        } else {
            locationManager.hasPermission = true
            locationManager.startLocationUpdates()
        }
        viewModel.submitAddressLiveData.observe(requireActivity()) {
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result .Success) {
                    navViewModel.navigateNextStep()
                } else if (it is Result.Error) {
                    navigateToErrorPage(it)
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                if (addressSpinner.popupWindow?.isShowing == true) {
                    addressSpinner.popupWindow?.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            btnContinue.setOnClickListener {
                val selectedPlace = addressSpinner.selectedPlace
                val latLng = selectedPlace?.latLng
                 if (latLng != null) {
                    viewModel.sendAddress( 
                        latLng.latitude,
                        latLng.longitude,
                        selectedPlace.address?.toString() ?: "",
                        match = locationManager.withinRange(
                            selectedLocation = latLng.latitude to latLng.longitude,
                            deviceLocation = locationManager.lastLocation
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager?.stopLocationUpdates()
    }


    private fun showPermissionError(onAllowClicked: () -> Unit) {

        LocationPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = onAllowClicked
            onExitClick = {
                navViewModel.popDojahBackStack()
            }
            show(this@HomeAddressFragment.childFragmentManager, null)
        }
    }
}
