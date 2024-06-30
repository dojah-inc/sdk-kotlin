package com.dojah_inc.dojah_android_sdk.core.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n \u0011*\u0004\u0018\u00010%0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n \u0011*\u0004\u0018\u00010\'0\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020)\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020-\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019\u00a8\u00062"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/di/DojahContainer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "getContext", "()Landroid/content/Context;", "countryManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/CountryManager;", "getCountryManager", "()Lcom/dojah_inc/dojah_android_sdk/data/io/CountryManager;", "dojahService", "Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahService;", "kotlin.jvm.PlatformType", "fileManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/FileManager;", "getFileManager", "()Lcom/dojah_inc/dojah_android_sdk/data/io/FileManager;", "govViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getGovViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "gson", "Lcom/google/gson/Gson;", "locationManager", "Lcom/dojah_inc/dojah_android_sdk/data/LocationManager;", "getLocationManager", "()Lcom/dojah_inc/dojah_android_sdk/data/LocationManager;", "navViewModelFactory", "getNavViewModelFactory", "networkManager", "Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;", "phoneUtil", "Lcom/google/i18n/phonenumbers/PhoneNumberUtil;", "retrofit", "Lretrofit2/Retrofit;", "sharedPreferenceManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "getSharedPreferenceManager", "()Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "userRepository", "Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "getUserRepository", "()Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "verificationViewModelFactory", "getVerificationViewModelFactory", "dojah-module_mobileDebug"})
public final class DojahContainer {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    private final com.dojah_inc.dojah_android_sdk.data.network.NetworkManager networkManager = null;
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager sharedPreferenceManager = null;
    private final com.google.i18n.phonenumbers.PhoneNumberUtil phoneUtil = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.io.CountryManager countryManager = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.LocationManager locationManager = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.io.FileManager fileManager = null;
    @org.jetbrains.annotations.NotNull
    private final okhttp3.OkHttpClient client = null;
    private final retrofit2.Retrofit retrofit = null;
    private final com.dojah_inc.dojah_android_sdk.data.network.service.DojahService dojahService = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository userRepository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.ViewModelProvider.Factory navViewModelFactory = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.ViewModelProvider.Factory verificationViewModelFactory = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.ViewModelProvider.Factory govViewModelFactory = null;
    
    public DojahContainer(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager getSharedPreferenceManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.io.CountryManager getCountryManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.LocationManager getLocationManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.io.FileManager getFileManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient getClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository getUserRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.ViewModelProvider.Factory getNavViewModelFactory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.ViewModelProvider.Factory getVerificationViewModelFactory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.ViewModelProvider.Factory getGovViewModelFactory() {
        return null;
    }
}