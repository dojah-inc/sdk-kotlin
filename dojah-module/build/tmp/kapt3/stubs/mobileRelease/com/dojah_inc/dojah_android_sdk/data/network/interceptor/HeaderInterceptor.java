package com.dojah_inc.dojah_android_sdk.data.network.interceptor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/network/interceptor/HeaderInterceptor;", "Lokhttp3/Interceptor;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "(Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;)V", "appId", "", "bearer", "pkey", "ref", "sessionId", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "dojah-module_mobileRelease"})
public final class HeaderInterceptor implements okhttp3.Interceptor {
    private java.lang.String bearer;
    private java.lang.String sessionId;
    private java.lang.String pkey;
    private java.lang.String ref;
    private java.lang.String appId;
    
    public HeaderInterceptor(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
}