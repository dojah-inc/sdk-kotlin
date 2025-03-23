package com.dojah.kyc_sdk_kotlin.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

@Suppress("DEPRECATION")
class NetworkManager(
    private val context: Context
) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val callbacks = mutableSetOf<NetworkChangeCallback>()

    init {
        subscribeToNetworkChanges()
    }

    var isConnected: Boolean = true
        private set

    private fun subscribeToNetworkChanges() {
        NetworkCapabilities.TRANSPORT_CELLULAR
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected = true
                    callbacks.forEach { it.onNetworkChange(true) }
                }

                override fun onLost(network: Network) {
                    isConnected = false
                    callbacks.forEach { it.onNetworkChange(false) }
                }

                override fun onUnavailable() {
                    isConnected = false
                    callbacks.forEach { it.onNetworkChange(false) }
                }
            })
    }

    fun addNetworkChangeCallback(callback: NetworkChangeCallback) {
        callback.onNetworkChange(isConnected)
        callbacks.add(callback)
    }

    fun removeNetworkCallback(callback: NetworkChangeCallback) {
        callbacks.remove(callback)
    }

    fun getDeviceIMEI(): String {
        return ""
    }

    interface NetworkChangeCallback {
        fun onNetworkChange(isAvailable: Boolean)
    }
}

