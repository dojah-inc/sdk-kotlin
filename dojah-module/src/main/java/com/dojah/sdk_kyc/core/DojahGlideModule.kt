package com.dojah.sdk_kyc.core

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.dojah.sdk_kyc.data.network.interceptor.HeaderInterceptor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
@Excludes(OkHttpLibraryGlideModule::class)
class DojahGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = EntryPointAccessors.fromApplication(
                context.applicationContext,
                DojahGlideEntryPoint::class.java)
                .okhttpClient()

        val newClient = client.newBuilder()
                .run {
//                    interceptors().let { it.removeIf { it is VersionCodeInterceptor } }
                    interceptors().let { it.removeIf { it is HeaderInterceptor } }

                    build()
                }

        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(newClient))
    }


    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DojahGlideEntryPoint {
        fun okhttpClient(): OkHttpClient
    }
}