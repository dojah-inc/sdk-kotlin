package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

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
import com.dojah.kyc_sdk_kotlin.BuildConfig
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.LocationManager
import com.dojah.kyc_sdk_kotlin.databinding.FragmentHomeAddressBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.dialog.LocationPermissionDialogFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.google.android.libraries.places.api.Places


@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class HomeAddressFragment : ErrorFragment(R.layout.fragment_home_address) {
    private val binding by viewBinding { FragmentHomeAddressBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private val locationManager: LocationManager by lazy {
        DojahSdk.dojahContainer.locationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { Places.initialize(it, BuildConfig.PLACE_KEY) }
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
            this.addressSpinner.predictTextChange(this@HomeAddressFragment)
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
        locationManager.stopLocationUpdates()
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
