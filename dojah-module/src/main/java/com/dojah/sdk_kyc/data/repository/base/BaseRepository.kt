package com.dojah.sdk_kyc.data.repository.base

import com.google.gson.Gson
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.network.NetworkManager
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class BaseRepository(private val networkManager: NetworkManager, private val gson: Gson) {
    private companion object {
        val successCode = arrayOf("success", "00", "ok")
    }

    /**
     * Before sending a request, it checks to ensure internet is available.
     * It also surrounds the action being performed to catch any possible error
     */
    protected suspend fun <O, T : Result<O>> checkNetworkAndStartRequest(action: suspend () -> T): T {
        return if (networkManager.isConnected) {
            try {
                action.invoke()
            } catch (e: UnknownHostException) {
                e.printStackTrace();Result.Error.TimeoutError as T
            } catch (e: SocketTimeoutException) {
                e.printStackTrace();Result.Error.TimeoutError as T
            } catch (e: Exception) {
                e.printStackTrace();Result.Error.ApiError(null) as T
            }
        } else Result.Error.NetworkError as T
    }

    /**
     * A replacement for the default response.isSuccessful since body status codes are used
     * instead of header status codes for determining the status of a request
     * Sigh.
     */
    private val String.isSuccess: Boolean
        get() {
            val jsonMap = gson.fromJson<Map<String, String>>(this, Map::class.java)

            val status = when {
                jsonMap?.containsKey("status") == true -> {
                    jsonMap["status"]

                }

                jsonMap?.containsKey("Status") == true -> {
                    jsonMap["Status"]
                }

                jsonMap?.containsKey("data") == true && (jsonMap.getValue("data") != null) -> {
                    "00"
                }

                else -> ""
            }

//            Timber.tag("getResult>").d(">isSuccess$status")

            return successCode.contains(status)
        }

    /**
     * After a request has been performed, it checks if it is successful or not
     * and returns a [Result], either a [Result.Success] or an [Result.Error].
     * @param titleKey is the key with a possible title error
     * @param messageKey is the key with a possible error message
     */
    protected fun <T> Response<ResponseBody>.getResult(successType: Class<T>): Result<T> {
        return body()?.use {

            val responseString = it.string()

            if (responseString.isSuccess) Result.Success(gson.fromJson(responseString, successType))
            else {
                val jsonMap = gson.fromJson<Map<Any, Any>>(responseString, Map::class.java)
                Result.Error.ApiError(jsonMap)
            }

        } ?: Result.Error.NoDataError
    }

    protected fun <T> String.stringToType(type: Type): T {

        return gson.fromJson(this, type)
    }

    protected val <T> Result<T>.toJson: String?
        get() {
            if (this is Result.Success) {
                return gson.toJson(this.data)
            } else if (this is Result.Error.ApiError) {
                return gson.toJson(this.error)
            }
            return null
        }

}