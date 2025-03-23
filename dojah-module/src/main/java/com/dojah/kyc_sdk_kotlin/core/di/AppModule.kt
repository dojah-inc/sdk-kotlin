//package com.dojah_inc.dojah_android_sdk.core.di
//
////import androidx.room.Room
//import com.google.gson.Gson
//import com.google.i18n.phonenumbers.PhoneNumberUtil
//import com.dojah_inc.dojah_android_sdk.BuildConfig
//import com.dojah_inc.dojah_android_sdk.core.Constants
////import com.dojah.sdk_kyc.data.io.DojahDatabase
//import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
//import com.dojah_inc.dojah_android_sdk.data.network.interceptor.HeaderInterceptor
//import com.dojah_inc.dojah_android_sdk.data.network.service.*
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//
//object
//AppModule {
//
//    @Singleton
//    @Provides
//    fun provideGson(): Gson {
//        return Gson()
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(gson: Gson, manager: SharedPreferenceManager): OkHttpClient =
//        OkHttpClient.Builder().run {
//            connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
//            readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
////            addInterceptor(VersionCodeInterceptor(BuildConfig.VERSION_CODE, gson))
//            addInterceptor(HeaderInterceptor(manager))
//
//            cache(null)
//
//            if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//
//            build()
//        }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideDojahService(retrofit: Retrofit): DojahService {
////        if (BuildConfig.DEBUG) {
////            print(":::::::::::::::::::::Using DojahServiceMock:::::::::::::::::::::")
////            return DojahServiceMock()
////        }
//
//        return retrofit.create(DojahService::class.java)
//    }
//
////    @Singleton
////    @Provides
////    fun provideDatabase(@ApplicationContext context: Context): DojahDatabase {
////        return Room.databaseBuilder(context, DojahDatabase::class.java, DojahDatabase.DB_NAME)
////            .run {
////                addMigrations(DojahDatabase.migration_1_2)
////                build()
////            }
////    }
//
//    @Provides
//    @Singleton
//    fun providePhoneUtil(): PhoneNumberUtil {
//        return PhoneNumberUtil.getInstance()
//    }
//
//}