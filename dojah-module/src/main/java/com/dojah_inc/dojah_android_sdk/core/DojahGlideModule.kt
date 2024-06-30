package com.dojah_inc.dojah_android_sdk.core

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import com.dojah_inc.dojah_android_sdk.DojahSdk
import com.dojah_inc.dojah_android_sdk.data.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
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