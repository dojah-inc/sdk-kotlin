package com.dojah.sdk_kyc.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.sdk_kyc.core.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A ViewModel created in the Activity and used in every Fragment that
 * defines a new destination, which will be sent through this ViewModel
 * to the Activity who will then handle the navigation. This ensures all
 * navigations are performed from one point
 */
class NavigationViewModel : ViewModel() {

    private val _navigationLiveData = MutableLiveData<Event<Triple<Int, Bundle?, PopAction?>>>()

    val navigationLiveData: LiveData<Event<Triple<Int, Bundle?, PopAction?>>>
        get() = _navigationLiveData

    private val _popBackStackLiveData = MutableLiveData<Event<Pair<Int, Boolean>>>()

    val popBackStackLiveData: LiveData<Event<Pair<Int, Boolean>>>
        get() = _popBackStackLiveData

    fun navigate(destination: Int, args: Bundle? = null, popAction: PopAction? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            _navigationLiveData.postValue(Event(Triple(destination, args, popAction)))
        }
    }

    /**
     * Sends a popBackStack message to the activity
     * @param destination is the id of the destination to popUpTo. Pass -1 if the default
     * [androidx.navigation.findNavController]
     */
    fun popBackStack(destination: Int = -1, inclusive: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            _popBackStackLiveData.postValue(Event(Pair(destination, inclusive)))
        }
    }

    data class PopAction(@IdRes val popupTo: Int, val inclusive: Boolean)
}