package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

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
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.data.LocationManager
import com.dojah_inc.dojah_android_sdk.databinding.FragmentHomeAddressBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.dialog.LocationPermissionDialogFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
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
