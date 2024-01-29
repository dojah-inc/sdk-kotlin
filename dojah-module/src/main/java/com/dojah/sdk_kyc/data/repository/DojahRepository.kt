package com.dojah.sdk_kyc.data.repository

import com.google.gson.Gson
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.core.mock_data.enumData
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.network.NetworkManager
import com.dojah.sdk_kyc.data.network.service.DojahService
import com.dojah.sdk_kyc.data.repository.base.BaseRepository
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import com.dojah.sdk_kyc.domain.request.EventRequest
import com.dojah.sdk_kyc.domain.request.ImageAnalysisRequest
import com.dojah.sdk_kyc.domain.request.LivenessCheckRequest
import com.dojah.sdk_kyc.domain.request.LivenessVerifyRequest
import com.dojah.sdk_kyc.domain.request.OtpRequest
import com.dojah.sdk_kyc.domain.responses.AuthResponse
import com.dojah.sdk_kyc.domain.responses.BvnLookUpResponse
import com.dojah.sdk_kyc.domain.responses.CheckIpResponse
import com.dojah.sdk_kyc.domain.responses.DojahEnum
import com.dojah.sdk_kyc.domain.responses.DriverLicenceResponse
import com.dojah.sdk_kyc.domain.responses.GetIpResponse
import com.dojah.sdk_kyc.domain.responses.ImageAnalysisResponse
import com.dojah.sdk_kyc.domain.responses.LivenessCheckResponse
import com.dojah.sdk_kyc.domain.responses.LivenessVerifyResponse
import com.dojah.sdk_kyc.domain.responses.NinLookUpResponse
import com.dojah.sdk_kyc.domain.responses.PreAuthResponse
import com.dojah.sdk_kyc.domain.responses.SendOtpResponse
import com.dojah.sdk_kyc.domain.responses.SimpleResponse
import com.dojah.sdk_kyc.domain.responses.ValidateOtpResponse
import com.dojah.sdk_kyc.domain.responses.VninLookUpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
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


    val getDojahEnum
        get(): Result.Success<DojahEnum> {
            val savedResponse = enumData.replace("\n", "").toResponseBody()
            return Response.success(savedResponse)
                .getResult(DojahEnum::class.java) as Result.Success
        }

    suspend fun doPreAuth(widgetId: String): Flow<Result<PreAuthResponse>> {
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
                prefManager.setPKey(result.data.publicKey ?: "")
                prefManager.setAppId(result.data.app?.id ?: "")

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
                prefManager.setSessionId(result.data.sessionId ?: "")
                prefManager.setReference(result.data.initData?.authData?.referenceId ?: "")

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
                    SharedPreferenceManager.KEY_CHECK_IP_RESPONSE
                )
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun logEvent(data: EventRequest): Flow<Result<SimpleResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.logEvent(data)
                response.getResult(SimpleResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun lookUpBvn(bvn: String): Flow<Result<BvnLookUpResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.lookUpBvn(bvn)
                response.getResult(BvnLookUpResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun lookUpNin(nin: String): Flow<Result<NinLookUpResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.lookUpNin(nin)
                response.getResult(NinLookUpResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun lookUpVnin(vNin: String): Flow<Result<VninLookUpResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.lookUpVNin(vNin)
                response.getResult(VninLookUpResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun lookUpDriverLicense(licenseNumber: String): Flow<Result<DriverLicenceResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.lookUpDriverLicence(licenseNumber)
                response.getResult(DriverLicenceResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendOtp(request: OtpRequest): Flow<Result<SendOtpResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.sendOtp(request)
                response.getResult(SendOtpResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun validateOtp(
        code: String, referenceId: String
    ): Flow<Result<ValidateOtpResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.validateOtp(code, referenceId)
                response.getResult(ValidateOtpResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun performImageAnalysis(
        image: String, imageType: String
    ): Flow<Result<ImageAnalysisResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.performImageAnalysis(ImageAnalysisRequest(image, imageType))
                response.getResult(ImageAnalysisResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun checkLiveness(
        request: LivenessCheckRequest
    ): Flow<Result<LivenessCheckResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.livenessCheck(request)
                response.getResult(LivenessCheckResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun verifyLiveness(
        request: LivenessVerifyRequest
    ): Flow<Result<LivenessVerifyResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.verifyLiveness(request)
                response.getResult(LivenessVerifyResponse::class.java)
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

