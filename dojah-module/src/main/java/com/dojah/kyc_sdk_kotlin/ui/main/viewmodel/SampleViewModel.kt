//package com.dojah.sdk_kyc.ui.main.viewmodel
//
//import android.annotation.SuppressLint
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.dojah.sdk_kyc.core.Result
//import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
//import com.dojah.sdk_kyc.data.network.RemoteResourceManager
//import com.dojah.sdk_kyc.data.repository.AnalysisRepository
//import com.dojah.sdk_kyc.domain.responses.*
//import com.dojah.sdk_kyc.service.AnalyticsService
//import com.dojah.sdk_kyc.ui.utils.formatAnalysisDate
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@SuppressLint("StaticFieldLeak")
//@HiltViewModel
//class SampleViewModel @Inject constructor(
//    private val resourceManager: RemoteResourceManager,
//    private val prefManager: SharedPreferenceManager,
//    private val repo: AnalysisRepository
//) : ViewModel(), RemoteResourceManager.Callback {
//
//
//    private val _weeklyDataLiveData = MutableLiveData<Result<AnalysisWeeklyResponse>>()
//    val weeklyDataLiveData: LiveData<Result<AnalysisWeeklyResponse>>
//        get() = _weeklyDataLiveData
//
//    private val _weeklyDateRangeLiveData = MutableLiveData<String?>()
//    val weeklyDateRangeLiveData: LiveData<String?>
//        get() = _weeklyDateRangeLiveData
//
//    private val _weeklyLastUpdatedLiveData = MutableLiveData<String?>()
//    val weeklyLastUpdatedLivedata: LiveData<String?>
//        get() = _weeklyLastUpdatedLiveData
//
//    fun trackEvent(name: String) {
//        analyticsService.logEvent(event = name)
//    }
//
//    fun getWeeklyUpdate(isSilent: Boolean = true) {
//
//
//        val localResponse = repo.getLocalRewardResponse(
//            SharedPreferenceManager.KEY_ANALYSIS_RESPONSE,
//            AnalysisWeeklyResponse::class.java
//        )
//        if (localResponse != null) {
//            //post data from shared pref if present
//            _weeklyDataLiveData.postValue(localResponse!!)
//            postWeeklyData(localResponse.data.data)
//        }
//        viewModelScope.launch {
//            repo.getWeeklyUpdate()
//                .onStart { if (!isSilent) _weeklyDataLiveData.postValue(Result.Loading) }
//                .collect { it ->
//                    _weeklyDataLiveData.postValue(it)
//                    if (it is Result.Success) {
//                        val rewardHistoryData = it.data.data
//                        postWeeklyData(rewardHistoryData)
//                    }
//                }
//        }
//    }
//
//    private fun postWeeklyData(rewardHistoryData: AnalysisWeeklyData?) {
//        _weeklyLastUpdatedLiveData.postValue("Last updated: ${rewardHistoryData?.lastUpdated}")
//        _weeklyDateRangeLiveData.postValue(
//            "Data For ${
//                rewardHistoryData?.startDate.formatAnalysisDate()
//            } - ${rewardHistoryData?.endDate.formatAnalysisDate(true)}"
//        )
//    }
//
//    override fun onResultAvailable(res: Int) {
//    }
//}
