package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J2\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006 "}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;", "", "success", "", "msg", "", "authData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;", "(Ljava/lang/Boolean;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;)V", "getAuthData", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;", "setAuthData", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;)V", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "getSuccess", "()Ljava/lang/Boolean;", "setSuccess", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;)Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;", "equals", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
public final class InitData {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "success")
    private java.lang.Boolean success;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "msg")
    private java.lang.String msg;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "data")
    private com.dojah_inc.dojah_android_sdk.domain.responses.AuthData authData;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.InitData copy(@org.jetbrains.annotations.Nullable
    java.lang.Boolean success, @org.jetbrains.annotations.Nullable
    java.lang.String msg, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData authData) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public InitData() {
        super();
    }
    
    public InitData(@org.jetbrains.annotations.Nullable
    java.lang.Boolean success, @org.jetbrains.annotations.Nullable
    java.lang.String msg, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData authData) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getSuccess() {
        return null;
    }
    
    public final void setSuccess(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMsg() {
        return null;
    }
    
    public final void setMsg(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData getAuthData() {
        return null;
    }
    
    public final void setAuthData(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData p0) {
    }
}