package com.dojah.kyc_sdk_kotlin.testutil

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.network.NetworkManager
import com.dojah.kyc_sdk_kotlin.data.repository.base.BaseRepository
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.reflect.Type

/**
 * Exposes [BaseRepository] protected helpers for unit testing.
 */
class TestBaseRepository(
    networkManager: NetworkManager,
    gson: Gson = Gson(),
) : BaseRepository(networkManager, gson) {

    suspend fun <O> invokeCheckNetworkAndStartRequest(
        action: suspend () -> Result<O>,
    ): Result<O> = checkNetworkAndStartRequest(action)

    fun <T> invokeGetResult(
        response: Response<ResponseBody>,
        successType: Class<T>,
    ): Result<T> = response.getResult(successType)

    fun <T> invokeStringToType(json: String, type: Type): T = json.stringToType(type)

    fun <T> invokeToJson(result: Result<T>): String? = result.toJson
}
