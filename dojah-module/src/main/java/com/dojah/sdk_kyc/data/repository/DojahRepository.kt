package com.dojah.sdk_kyc.data.repository

import com.google.gson.Gson
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.network.NetworkManager
import com.dojah.sdk_kyc.data.network.service.DojahService
import com.dojah.sdk_kyc.data.repository.base.BaseRepository
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import com.dojah.sdk_kyc.domain.responses.AuthResponse
import com.dojah.sdk_kyc.domain.responses.CheckIpResponse
import com.dojah.sdk_kyc.domain.responses.GetIpResponse
import com.dojah.sdk_kyc.domain.responses.PreAuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class DojahRepository @Inject constructor(
    networkManager: NetworkManager,
    gson: Gson,
    private val prefManager: SharedPreferenceManager,
    private val service: DojahService
) : BaseRepository(networkManager, gson) {


    suspend fun doPreAuth(widgetId: String): Flow<Result<PreAuthResponse>> {
//        prefManager.setBearerToken(null)
//        prefManager.setSessionId("")
        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.doPreAuth(widgetId)
                response.getResult(PreAuthResponse::class.java)
            }
            if (result is Result.Success) {
                //save response to sharedPref if successful
                prefManager.saveJsonResponse(
                    result.toJson,
                    SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE
                )
                prefManager.setBearerToken(Random.nextInt(200).toString())
            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun doAuth(authRequest: AuthRequest): Flow<Result<AuthResponse>> {

        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.doAuth(authRequest)
                response.getResult(AuthResponse::class.java)
            }
            if (result is Result.Success) {
                //save response to sharedPref if successful
                prefManager.saveJsonResponse(
                    result.toJson,
                    SharedPreferenceManager.KEY_AUTH_RESPONSE
                )
                prefManager.setSessionId(result.data.sessionId ?: "empty")

            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserIp(): Flow<Result<GetIpResponse>> {

        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.getUserIp()
                response.getResult(GetIpResponse::class.java)
            }
            if (result is Result.Success) {
                prefManager.saveJsonResponse(
                    result.toJson,
                    SharedPreferenceManager.KEY_CHECK_IP_RESPONSE
                )
            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun checkUserIp(request: CheckIpRequest): Flow<Result<CheckIpResponse>> {

        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.checkUserIp(request)
                response.getResult(CheckIpResponse::class.java)
            }
            if (result is Result.Success) {
                //save response to sharedPref if successful
                prefManager.saveJsonResponse(
                    result.toJson,
                    SharedPreferenceManager.KEY_GET_IP_RESPONSE
                )
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    fun <T> getLocalResponse(key: String, responseClass: Class<T>): Result.Success<T>? {
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

    fun deleteAllAuthData() {
        prefManager.setBearerToken(null)
        prefManager.setSessionId("")
        prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE)
        prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_AUTH_RESPONSE)
        prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_CHECK_IP_RESPONSE)
        prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_GET_IP_RESPONSE)
    }


}