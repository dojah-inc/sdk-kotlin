package com.dojah_inc.dojah_android_sdk.data.repository

import com.google.gson.Gson
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.core.mock_data.enumData
import com.dojah_inc.dojah_android_sdk.core.mock_data.pricing
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.data.network.NetworkManager
import com.dojah_inc.dojah_android_sdk.data.network.service.DojahService
import com.dojah_inc.dojah_android_sdk.data.repository.base.BaseRepository
import com.dojah_inc.dojah_android_sdk.domain.request.AdditionalDocRequest
import com.dojah_inc.dojah_android_sdk.domain.request.AddressRequest
import com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest
import com.dojah_inc.dojah_android_sdk.domain.request.BaseAddressRequest
import com.dojah_inc.dojah_android_sdk.domain.request.CheckIpRequest
import com.dojah_inc.dojah_android_sdk.domain.request.EventRequest
import com.dojah_inc.dojah_android_sdk.domain.request.ImageAnalysisRequest
import com.dojah_inc.dojah_android_sdk.domain.request.LivenessCheckRequest
import com.dojah_inc.dojah_android_sdk.domain.request.LivenessVerifyRequest
import com.dojah_inc.dojah_android_sdk.domain.request.OtpRequest
import com.dojah_inc.dojah_android_sdk.domain.request.UserDataRequest
import com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.BizLookupResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.BvnLookUpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.CheckIpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.DecisionResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.DocImageAnalysisResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum
import com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing
import com.dojah_inc.dojah_android_sdk.domain.responses.DriverLicenceResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.GetIpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.ImageAnalysisResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.LivenessCheckResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.LivenessVerifyResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.NinLookUpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse
import com.dojah_inc.dojah_android_sdk.domain.responses.VninLookUpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

class DojahRepository (
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

    val dojahPricing
        get(): Result.Success<DojahPricing> {
            val savedResponse = pricing.trimIndent().toResponseBody()
            return Response.success(savedResponse)
                .getResult(DojahPricing::class.java) as Result.Success
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
                prefManager.addIdToHistory(result.data)
                saveAuthDetailsToPrefs(result)
            }

            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    fun saveAuthDetailsToPrefs(result: Result.Success<AuthResponse>) {
        prefManager.saveJsonResponse(
            result.toJson,
            SharedPreferenceManager.KEY_AUTH_RESPONSE
        )
        prefManager.setSessionId(result.data.sessionId ?: "")
        prefManager.setReference(result.data.initData?.authData?.referenceId ?: "")
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
                    service.logEvent(
                        data.copy(
                            appId = prefManager.getAppId(),
                            sessionId = prefManager.getSessionId()
                        )
                    )
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

    suspend fun performDocImageAnalysis(
        image: String,
    ): Flow<Result<DocImageAnalysisResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.performImageAnalysis(ImageAnalysisRequest(image, "id"))
                response.getResult(DocImageAnalysisResponse::class.java)
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

    suspend fun sendUserData(
        request: UserDataRequest
    ): Flow<Result<SimpleResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.sendUserData(request)
                response.getResult(SimpleResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun uploadAdditionalFile(
        request: AdditionalDocRequest
    ): Flow<Result<SimpleResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.uploadAdditionalFile(request)
                response.getResult(SimpleResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun makeFinalDecision(
        verificationId: Int,
        sessionId: String
    ): Flow<Result<DecisionResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.makeFinalDecision(verificationId, sessionId)
                response.getResult(DecisionResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendBaseAddress(
        selectedAddressLatitude: Double,
        selectedAddressLongitude: Double,
        addressName: String,
    ): Flow<Result<SimpleResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response =
                    service.sendBaseAddress(
                        BaseAddressRequest(
                            prefManager.getAppId() ?: throw Exception("AppId not found"),
                            selectedAddressLatitude,
                            selectedAddressLongitude,
                            addressName,
                            sessionId = prefManager.getSessionId()
                                ?: throw Exception("SessionId not found"),
                        )
                    )
                response.getResult(SimpleResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendAddress(
        match: Boolean,
    ): Flow<Result<SimpleResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val location = prefManager.location
                val response =
                    service.sendAddress(
                        AddressRequest(
                            prefManager.getAppId() ?: throw Exception("AppId not found"),
                            location?.first ?: throw Exception("Latitude not found"),
                            location.second,
                            match,
                            sessionId = prefManager.getSessionId()
                                ?: throw Exception("SessionId not found"),
                        )
                    )
                response.getResult(SimpleResponse::class.java)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    suspend fun lookupCac(
        rcNumber: String,
        companyName: String,
        appId: String,
    ): Flow<Result<BizLookupResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.lookupCac(rcNumber, companyName,"cac",appId)
                HttpLoggingInterceptor.Logger.DEFAULT.log("lookupCac response:${response}")
                response.getResult(BizLookupResponse::class.java)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun lookupTin(
        tin: String,
        companyName: String,
        appId: String,
    ): Flow<Result<BizLookupResponse>> {
        return flow {
            val result = checkNetworkAndStartRequest {
                val response = service.lookUpTin(tin, companyName,appId)
                response.getResult(BizLookupResponse::class.java)
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

