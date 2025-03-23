package com.dojah.kyc_sdk_kotlin.core

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.data.network.interceptor.HeaderInterceptor
import java.io.InputStream

@GlideModule
//@Excludes(OkHttpLibraryGlideModule::class)
class DojahGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {

        val newClient = DojahSdk.dojahContainer.client.newBuilder()
                .run {
                    interceptors().let { it.removeIf { it is HeaderInterceptor } }

                    build()
                }

        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(newClient))
    }

}