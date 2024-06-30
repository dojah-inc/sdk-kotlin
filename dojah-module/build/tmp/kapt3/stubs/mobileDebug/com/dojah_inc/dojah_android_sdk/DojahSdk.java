package com.dojah_inc.dojah_android_sdk;

import java.lang.System;

@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00110\u0010J&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0012J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/DojahSdk;", "", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "dojahContainer", "Lcom/dojah_inc/dojah_android_sdk/core/di/DojahContainer;", "getDojahContainer", "()Lcom/dojah_inc/dojah_android_sdk/core/di/DojahContainer;", "setDojahContainer", "(Lcom/dojah_inc/dojah_android_sdk/core/di/DojahContainer;)V", "getIdHistory", "", "Lkotlin/Pair;", "", "launch", "", "widgetId", "referenceId", "email", "with", "dojah-module_mobileDebug"})
public final class DojahSdk {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.DojahSdk INSTANCE = null;
    @org.jetbrains.annotations.Nullable
    private static android.content.Context context;
    public static com.dojah_inc.dojah_android_sdk.core.di.DojahContainer dojahContainer;
    
    private DojahSdk() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.Nullable
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.core.di.DojahContainer getDojahContainer() {
        return null;
    }
    
    public final void setDojahContainer(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.di.DojahContainer p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.DojahSdk with(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getIdHistory() {
        return null;
    }
    
    public final void launch(@org.jetbrains.annotations.NotNull
    java.lang.String widgetId, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.String email) {
    }
}