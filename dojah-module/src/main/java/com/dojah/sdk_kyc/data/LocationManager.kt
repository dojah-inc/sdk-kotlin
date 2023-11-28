package com.dojah.sdk_kyc.data

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("MissingPermission")
@Singleton
class LocationManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val prefManager: SharedPreferenceManager
) {

    var hasPermission = false

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    init
    {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        locationRequest = LocationRequest()
        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                if (locationResult.locations.isNotEmpty()) {
                    // get latest location
                    val location =
                        locationResult.lastLocation
                    // use your location object
                    // get latitude , longitude and other info from this
                    prefManager.setLocation(location.latitude, location.longitude)
                }
            }
        }
    }

    fun startLocationUpdates() {
        if(hasPermission)
        {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null /* Looper */
            )
        }
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}