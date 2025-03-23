package com.dojah.kyc_sdk_kotlin.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Event
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.DecisionResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.SimpleResponseEntity
import com.dojah.kyc_sdk_kotlin.ui.main.DojahMainActivity
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.DecisionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * A ViewModel created in the Activity and used in every Fragment that
 * defines a new destination, which will be sent through this ViewModel
 * to the Activity who will then handle the navigation. This ensures all
 * navigations are performed from one point
 */
class NavigationViewModel (
    private val prefManager: SharedPreferenceManager,
    private val repo: DojahRepository,
) : ViewModel() {
    val currentPage: String?
        get() =
            _currentStepLiveData.value?.lastOrNull()

    private val _currentStepLiveData = MutableLiveData<ArrayDeque<String>>(ArrayDeque())

    val currentStepLiveData: LiveData<ArrayDeque<String>>
        get() = _currentStepLiveData

    private val _autoNavigateLiveData = MutableLiveData<Event<Pair<Bundle?, PopAction?>>>()

    val autoNavigateLiveData: LiveData<Event<Pair<Bundle?, PopAction?>>>
        get() = _autoNavigateLiveData

    private val _navigationLiveData = MutableLiveData<Event<Triple<String, Bundle?, PopAction?>>>()

    val navigationLiveData: LiveData<Event<Triple<String, Bundle?, PopAction?>>>
        get() = _navigationLiveData

    private val _popBackStackLiveData = MutableLiveData<Event<Pair<Int, Boolean>>>()

    val popBackStackLiveData: LiveData<Event<Pair<Int, Boolean>>>
        get() = _popBackStackLiveData

    private val _finalDecisionLiveData = MutableLiveData<Result<DecisionResponse>?>()
    val finalDecisionLiveData: LiveData<Result<DecisionResponse>?>
        get() = _finalDecisionLiveData

    fun resetDecisionLiveData() {
        _finalDecisionLiveData.value = null
    }


    fun navigateOld(destination: Int, args: Bundle? = null, popAction: PopAction? = null) {
        viewModelScope.launch(Dispatchers.Main) {
//            _navigationLiveData.postValue(Event(Triple(destination, args, popAction)))
        }
    }

    fun navigate(destination: String, args: Bundle? = null, popAction: PopAction? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            _navigationLiveData.postValue(Event(Triple(destination, args, popAction)))
        }
    }

    fun navigateNextStep(args: Bundle? = null, popAction: PopAction? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            _autoNavigateLiveData.postValue(Event(Pair(args, popAction)))
        }
    }

    fun pushNextDojahRoute(currentRoute: String) {
        _currentStepLiveData.value?.addLast(currentRoute)
        _currentStepLiveData.postValue(_currentStepLiveData.value)
    }

    fun popLastDojahRoute() {
        val removeLastOrNull = _currentStepLiveData.value?.removeLastOrNull()
        if (removeLastOrNull != null) {
            _currentStepLiveData.postValue(_currentStepLiveData.value)
        }
    }

    fun popDojahBackStack() {
        popLastDojahRoute()
        popBackStack()
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


    fun makeFinalDecision() {
        val verificationId = authDataFromPref?.initData?.authData?.verificationId
            ?: throw Exception("Verification id is null")
        val sessionId = authDataFromPref?.sessionId
            ?: throw Exception("session id is null")
        viewModelScope.launch {
            ///make the API call to decision
            repo.makeFinalDecision(verificationId, sessionId)
                .onStart {
                    _finalDecisionLiveData.postValue(Result.Loading)
                }
                .collect {
                    _finalDecisionLiveData.postValue(it)
                }
        }
    }

    fun resumeOngoingVerification(
        activity: DojahMainActivity,
        emailEntity: SimpleResponseEntity?,
    ) {
        if (emailEntity?.continueVerification == true) {
            updateCurrentSteps(
                emptyList()
            )

            activity.handleNavigations(isReset = true)

        } else if (emailEntity?.duplicateReference == true) {
            if (emailEntity.result?.entity?.verificationStatus?.lowercase() == DecisionStatus.pending.name) {
                navigate(
                    "${Routes.success_route}/${
                        ContextCompat.getString(
                            activity,
                            R.string.error_verification_pending
                        )
                    }"
                )
            } else {
                navigate(
                    "${Routes.success_route}/${
                        ContextCompat.getString(
                            activity,
                            R.string.error_verification_success
                        )
                    }"
                )
            }
        } else {
            navigateNextStep()
        }
    }

    fun updateCurrentSteps(steps: List<String>) {
        val stepsDeque = ArrayDeque(steps)
        _currentStepLiveData.postValue(stepsDeque)
    }

    private val authDataFromPref: AuthResponse?
        get() {
            return repo.getLocalResponse(
                SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
            )?.data
        }

    data class PopAction(@IdRes val popupTo: Int, val inclusive: Boolean)
}