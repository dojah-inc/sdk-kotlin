package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.BuildConfig
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.LocationManager
import com.dojah.kyc_sdk_kotlin.databinding.FragmentHomeAddressBinding
import com.dojah.kyc_sdk_kotlin.domain.CountryState
import com.dojah.kyc_sdk_kotlin.domain.Location
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.base.SpinnerFragment
import com.dojah.kyc_sdk_kotlin.ui.dialog.LocationPermissionDialogFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.SimpleEditTextSpinner
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.textChanges
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnsafeRepeatOnLifecycleDetector")
class HomeAddressFragment : SpinnerFragment(R.layout.fragment_home_address) {
    private val binding by viewBinding { FragmentHomeAddressBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var clientUserLocation: Location? = null

    private lateinit var cameraContract: ActivityResultLauncher<String>

    private var selectedState: CountryState? = null
    private var selectedCity: String? = null

    private val locationManager: LocationManager by lazy {
        DojahSdk.dojahContainer.locationManager
    }

    private fun checkCameraPermission(onGranted: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                onGranted()
            }

            else -> {
                cameraContract.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun checkLocationPermission(onGranted: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                onGranted()
            }

            else -> {
                showPermissionError {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.extraUserDataFromPref?.location?.let {
            if (it.latitude != null && it.longitude != null) {
                clientUserLocation = it
            }
        }
        val address = viewModel.extraUserDataFromPref?.address

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                checkLocationPermission {
                    locationManager.hasPermission = true
                    locationManager.startLocationUpdates()
                }
            }

        viewModel.loadUserCountryStates()

        context?.let { Places.initialize(it, BuildConfig.PLACE_KEY) }

        checkLocationPermission {
            locationManager.hasPermission = true
            locationManager.startLocationUpdates()
        }

        viewModel.states.observe(requireActivity()) {

        }

        viewModel.cities.observe(requireActivity()) {
            if (it.isEmpty()) {
                return@observe
            }
            displaySpinnerDropdown(
                binding.homeLgaSpinner,
                it.map { city -> city },
                false
            ) { index ->
                selectedCity = viewModel.cities.value?.get(index)
                binding.homeLgaSpinner.setText(selectedCity ?: "")
            }
        }

        viewModel.submitAddressLiveData.observe(requireActivity()) {
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    viewModel.getStepWithPageName(KycPages.ADDRESS.serverKey)?.config?.let { config ->
                        if (config.utilityBill == true) {
                            // if utility bill is required, we navigate to the next step which is the utility bill upload page, otherwise we check for live location requirement or just navigate to the next step
                            checkCameraPermission {
                                navViewModel.navigate(Routes.utility_bill_route)
                            }
                        } else if (config.liveLocation == true) {
                            // navigate to live location page is required
                        } else {
                            navViewModel.navigateNextStep()
                        }
                    }
                } else if (it is Result.Error) {
                    if (clientUserLocation == null && address != null) {
                        binding.root.post {
                            binding.root.isVisible = true
                        }
                    } else {
                        navigateToErrorPage(it)
                    }
                }
            }
        }
    }

    private fun showSelectedCities() {
        if (selectedState?.name.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                "Please select a state first",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        viewModel.getStateCities(selectedState?.name ?: "", selectedCity ?: "")
    }

    private fun updateButtonState() = binding.apply {
        val enabled = homeStateSpinner.text?.isNotBlank() == true
                && homeLgaSpinner.text?.isNotBlank() == true
                && !addressSpinner.isEmpty()
                && addressSpinner.selectedPlace != null
        btnContinue.isEnabled = enabled
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cameraContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    navViewModel.navigate(Routes.utility_bill_route)
                } else {
                    showPermissionError {
                        cameraContract.launch(Manifest.permission.CAMERA)
                    }
                }
            }

        updateButtonState()

        binding.apply {
            if (clientUserLocation == null && viewModel.extraUserDataFromPref?.address != null) {
                root.isVisible = false
            } else {
                root.isVisible = true
            }
            requireActivity().onBackPressedDispatcher.addCallback {
                if (addressSpinner.popupWindow?.isShowing == true) {
                    addressSpinner.popupWindow?.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            this.addressSpinner.predictTextChange(this@HomeAddressFragment)

            addressSpinner.setOnItemSelectedListener(
                object : SimpleEditTextSpinner.OnItemSelectedListener {
                    override fun onItemSelected(text: String) {
                        updateButtonState()
                    }
                }
            )

            addressSpinner.setOnTextChangedListener(
                object : SimpleEditTextSpinner.OnTextChangedListener {
                    override fun onTextChanged(text: String) {
                        if (text.isBlank()) {
                            addressSpinner.selectedPlace = null
                        }
                        updateButtonState()
                    }
                }
            )

            homeStateSpinner.setOnClickListener {
                displaySpinnerDropdown(
                    it,
                    viewModel.states.value?.map { state -> state.name } ?: listOf(),
                    false
                ) { index ->
                    selectedState = viewModel.states.value?.get(index)
                    homeLgaSpinner.setText("")
                    selectedCity = null
                    homeStateSpinner.setText(selectedState?.name ?: "")
                    updateButtonState()
                    homeLgaSpinner.clearFocus() // Remove focus from homeLgaSpinner when state changes
                }
            }

            homeLgaSpinner.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showSelectedCities()
                }
            }

            homeLgaSpinner.setOnClickListener {
                showSelectedCities()
            }

            homeLgaSpinner.textChanges()
                .filterNot {
                    it.isNullOrBlank() || selectedCity?.equals(
                        it.toString(),
                        ignoreCase = true
                    ) == true
                }
                .debounce(500L)
                .onEach {
                    viewModel.getStateCities(selectedState?.name ?: "", it.toString())
                    updateButtonState()
                }
                .flowOn(Dispatchers.IO)
                .launchIn(requireActivity().lifecycleScope)

            btnContinue.setOnClickListener {
                val selectedPlace = addressSpinner.selectedPlace
                val latLng = selectedPlace?.latLng
                if (latLng != null) {
                    var deviceLocation: Pair<Double, Double>?

                    if (clientUserLocation != null) {
                        val lat = clientUserLocation?.latitude?.toDoubleOrNull()
                        val lng = clientUserLocation?.longitude?.toDoubleOrNull()
                        deviceLocation = if (lat != null && lng != null) {
                            lat to lng
                        } else {
                            locationManager.lastLocation
                        }
                    } else {
                        deviceLocation = locationManager.lastLocation
                    }
                    if (deviceLocation != null) {
                        viewModel.sendAddress(
                            latLng.latitude,
                            latLng.longitude,
                            selectedPlace.address ?: "",
                            deviceLocation,
                            match = LocationManager.withinRange(
                                selectedLocation = latLng.latitude to latLng.longitude,
                                deviceLocation = deviceLocation
                            ),
                            selectedState?.name,
                            selectedCity,
                            binding.homeLandmarkSpinner.text.toString()
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "your device location is not found",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
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
