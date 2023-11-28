package com.dojah.sdk_kyc.data.repository

import com.google.gson.Gson
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.network.NetworkManager
import com.dojah.sdk_kyc.data.network.service.DojahService
import com.dojah.sdk_kyc.data.repository.base.BaseRepository
import com.dojah.sdk_kyc.domain.responses.DataSampleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(
    networkManager: NetworkManager,
    gson: Gson,
    private val prefManager: SharedPreferenceManager,
    private val service: DojahService
) : BaseRepository(networkManager, gson) {


    suspend fun getWeeklyUpdate(): Flow<Result<DataSampleResponse>> {

        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.getData()
                response.getResult(DataSampleResponse::class.java)
            }
            if (result is Result.Success) {
                //save response to sharedPref if successful
                prefManager.saveJsonResponse(
                    result.toJson,
                    SharedPreferenceManager.KEY_ANALYSIS_RESPONSE
                )
            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    fun <T> getLocalRewardResponse(key: String, responseClass: Class<T>): Result.Success<T>? {
        val savedResponse: Response<ResponseBody>? =
            prefManager.getSavedJsonResponse(key)
        if (savedResponse != null) {
            val result = savedResponse.getResult(responseClass);
            if (result is Result.Success) {
                return result
            }
            return null
        }
        return null
    }


}