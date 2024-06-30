package com.dojah_inc.dojah_android_sdk.data.io;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 ?2\u00020\u0001:\u0002>?B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001aJ\b\u0010 \u001a\u0004\u0018\u00010\u000eJ\b\u0010!\u001a\u0004\u0018\u00010\u000eJ\b\u0010\"\u001a\u0004\u0018\u00010\u000eJ\b\u0010#\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%2\u0006\u0010\'\u001a\u00020\u000eJ\b\u0010(\u001a\u0004\u0018\u00010\u000eJ\n\u0010)\u001a\u0004\u0018\u00010\u000eH\u0002J\u001c\u0010*\u001a\u00020\u001a2\b\u0010+\u001a\u0004\u0018\u00010\u00062\b\u0010\'\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010,\u001a\u00020\u001a2\b\u0010-\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\'\u001a\u00020\u000eJ\u000e\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u000eJ\u0010\u00100\u001a\u00020\u001a2\b\u00101\u001a\u0004\u0018\u00010\u000eJ\u0016\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u0015J\u0010\u00105\u001a\u00020\u001a2\b\u00106\u001a\u0004\u0018\u00010\u000eJ\u000e\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u000eJ\u000e\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u000eJ\u000e\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u000eJ\u000e\u0010=\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u000eR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0018\u00010\r8F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appPref", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "callbacks", "", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager$Callback;", "getIdHistory", "", "Lkotlin/Pair;", "", "getGetIdHistory", "()Ljava/util/List;", "getMaterialButtonBgColor", "getGetMaterialButtonBgColor", "()Ljava/lang/String;", "location", "", "getLocation", "()Lkotlin/Pair;", "userPref", "addCallback", "", "callback", "addIdToHistory", "data", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "clearTemporaryPref", "getAppId", "getBearerToken", "getPKey", "getReference", "getSavedJsonResponse", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "key", "getSessionId", "getWidgetId", "onSharedPreferenceChanged", "sharedPreferences", "saveJsonResponse", "responseString", "setAppId", "appId", "setBearerToken", "token", "setLocation", "latitude", "longitude", "setMaterialButtonBgColor", "color", "setPKey", "pkey", "setReference", "ref", "setSessionId", "id", "setWidgetId", "Callback", "Companion", "dojah-module_mobileReleaseNoMinify"})
public final class SharedPreferenceManager implements android.content.SharedPreferences.OnSharedPreferenceChangeListener {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_BEARER_TOKEN = " bearer token";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_NOTIFICATION_TOKEN = "notification_token";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SESSION_ID = "session id";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_PKEY = "key pkey";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_REF = "key ref";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_APP_ID = "key app id";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_LOCATION = "location";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_AUTH_RESPONSE = "key_analysis_response";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_PRE_AUTH_RESPONSE = "key_pre_auth_response";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_CHECK_IP_RESPONSE = "key_check_ip_response";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_GET_IP_RESPONSE = "key_get_ip_response";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_BTN_COLOR = "key_btn_color";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_ID_HISTORY = "id_history";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_Widget_ID = "key_widget_id";
    private final android.content.SharedPreferences userPref = null;
    private final android.content.SharedPreferences appPref = null;
    private final java.util.List<com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager.Callback> callbacks = null;
    
    public SharedPreferenceManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void addCallback(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager.Callback callback) {
    }
    
    @java.lang.Override
    public void onSharedPreferenceChanged(@org.jetbrains.annotations.Nullable
    android.content.SharedPreferences sharedPreferences, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
    }
    
    public final void setLocation(double latitude, double longitude) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.Pair<java.lang.Double, java.lang.Double> getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBearerToken() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSessionId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPKey() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReference() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAppId() {
        return null;
    }
    
    public final void setPKey(@org.jetbrains.annotations.NotNull
    java.lang.String pkey) {
    }
    
    public final void setReference(@org.jetbrains.annotations.NotNull
    java.lang.String ref) {
    }
    
    public final void setAppId(@org.jetbrains.annotations.NotNull
    java.lang.String appId) {
    }
    
    public final void setBearerToken(@org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void setSessionId(@org.jetbrains.annotations.NotNull
    java.lang.String id) {
    }
    
    public final void setWidgetId(@org.jetbrains.annotations.NotNull
    java.lang.String appId) {
    }
    
    private final java.lang.String getWidgetId() {
        return null;
    }
    
    public final void addIdToHistory(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse data) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getGetIdHistory() {
        return null;
    }
    
    public final void saveJsonResponse(@org.jetbrains.annotations.Nullable
    java.lang.String responseString, @org.jetbrains.annotations.NotNull
    java.lang.String key) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final retrofit2.Response<okhttp3.ResponseBody> getSavedJsonResponse(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    public final void setMaterialButtonBgColor(@org.jetbrains.annotations.Nullable
    java.lang.String color) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getGetMaterialButtonBgColor() {
        return null;
    }
    
    public final void clearTemporaryPref() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager$Callback;", "", "onChange", "", "key", "", "dojah-module_mobileReleaseNoMinify"})
    public static abstract interface Callback {
        
        public abstract void onChange(@org.jetbrains.annotations.NotNull
        java.lang.String key);
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager$Companion;", "", "()V", "KEY_APP_ID", "", "KEY_AUTH_RESPONSE", "KEY_BEARER_TOKEN", "KEY_BTN_COLOR", "KEY_CHECK_IP_RESPONSE", "KEY_GET_IP_RESPONSE", "KEY_ID_HISTORY", "KEY_LOCATION", "KEY_NOTIFICATION_TOKEN", "KEY_PKEY", "KEY_PRE_AUTH_RESPONSE", "KEY_REF", "KEY_SESSION_ID", "KEY_Widget_ID", "dojah-module_mobileReleaseNoMinify"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}