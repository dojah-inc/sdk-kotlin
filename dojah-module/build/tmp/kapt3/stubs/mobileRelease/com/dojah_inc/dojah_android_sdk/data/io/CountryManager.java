package com.dojah_inc.dojah_android_sdk.data.io;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0011\u001a\u00020\u00102\u001c\u0010\u0012\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u0013J\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170\bH\u0002J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R.\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b@BX\u0082\u000e\u00a2\u0006\b\n\u0000\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/CountryManager;", "", "context", "Landroid/content/Context;", "phoneNumberUtil", "Lcom/google/i18n/phonenumbers/PhoneNumberUtil;", "(Landroid/content/Context;Lcom/google/i18n/phonenumbers/PhoneNumberUtil;)V", "value", "", "Lcom/dojah_inc/dojah_android_sdk/domain/Country;", "countries", "setCountries", "(Ljava/util/List;)V", "listeners", "", "Lkotlin/Function1;", "", "addCallback", "callback", "Lcom/dojah_inc/dojah_android_sdk/data/io/CountryCallback;", "getCountries", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCountryImagePath", "", "id", "list", "start", "scope", "Lkotlinx/coroutines/CoroutineScope;", "dojah-module_mobileRelease"})
public final class CountryManager {
    private final android.content.Context context = null;
    private final com.google.i18n.phonenumbers.PhoneNumberUtil phoneNumberUtil = null;
    private final java.util.List<kotlin.jvm.functions.Function1<java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country>, kotlin.Unit>> listeners = null;
    private java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country> countries;
    
    public CountryManager(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.google.i18n.phonenumbers.PhoneNumberUtil phoneNumberUtil) {
        super();
    }
    
    private final void setCountries(java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country> value) {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    private final java.lang.Object getCountries(kotlin.coroutines.Continuation<? super java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country>> continuation) {
        return null;
    }
    
    /**
     * Add a callback which will be called when countries has been fetched
     * it calls the function immediately if the countries are already available
     * it also auto clears the callback after it has been called
     */
    public final void addCallback(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country>, kotlin.Unit> callback) {
    }
    
    private final java.lang.String getCountryImagePath(java.lang.String id, java.util.List<java.lang.String> list) {
        return null;
    }
}