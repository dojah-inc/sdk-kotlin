package com.dojah.kyc_sdk_kotlin.data

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@SuppressLint("MissingPermission")
class LocationManager(
    private val context: Context,
    val prefManager: SharedPreferenceManager
) {

    var hasPermission = false

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationRequest = LocationRequest()
        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
        locationRequest.priority =
            LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return

                if (locationResult.locations.isNotEmpty()) {
                    // get latest location
                    val location =
                        locationResult.lastLocation
                    // use your location object
                    // get latitude , longitude and other info from this
                    if (location != null) {
                        prefManager.setLocation(location.latitude, location.longitude)
                    }
                }
            }
        }
    }

    val lastLocation
        get(): Pair<Double, Double>? {
            val lat = prefManager.location?.first
            val long = prefManager.location?.second
            if (lat != null && long != null)
                return Pair(lat, long)
            return null
        }

    fun startLocationUpdates() {
        if (hasPermission) {
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

    companion object {
        fun withinRange(
            selectedLocation: Pair<Double, Double>,
            deviceLocation: Pair<Double, Double>,
            range: Double = 0.05
        ): Boolean {
            val lat1 = selectedLocation.first
            val long1 = selectedLocation.second
            val lat2 = deviceLocation.first
            val long2 = deviceLocation.second

            // Converts numeric degrees to radians
            fun toRad(value: Double): Double {
                return (value * Math.PI) / 180;
            };

            val r = 6371; // km
            val dLat = toRad(lat2 - lat1);
            val dLon = toRad(long2 - long1);
            val latitude1 = toRad(lat1);
            val latitude2 = toRad(lat2);

            val a =
                sin(dLat / 2) * Math.sin(dLat / 2) +
                        sin(dLon / 2) *
                        sin(dLon / 2) *
                        cos(latitude1) *
                        cos(latitude2);
            val c = 2 * atan2(sqrt(a), sqrt(1 - a));
            val d = r * c;
            return d <= range;
        }
    }
}