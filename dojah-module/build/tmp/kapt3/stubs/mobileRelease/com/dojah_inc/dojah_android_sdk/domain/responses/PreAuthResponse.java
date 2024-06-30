package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\u001e\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005J\t\u0010\"\u001a\u00020\u0005H\u00d6\u0001J\u0012\u0010#\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020%0$H\u0002R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006&"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/PreAuthResponse;", "", "widget", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget;", "publicKey", "", "app", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;)V", "getApp", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;", "setApp", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;)V", "getPublicKey", "()Ljava/lang/String;", "setPublicKey", "(Ljava/lang/String;)V", "getWidget", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget;", "setWidget", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toAuthRequest", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "referenceId", "email", "toString", "getNearestFirstIndex", "", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthReqSteps;", "dojah-module_mobileRelease"})
public final class PreAuthResponse {
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "widget")
    private com.dojah_inc.dojah_android_sdk.domain.responses.Widget widget;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "public_key")
    private java.lang.String publicKey;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "app")
    private com.dojah_inc.dojah_android_sdk.domain.responses.App app;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse copy(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget widget, @org.jetbrains.annotations.Nullable
    java.lang.String publicKey, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.App app) {
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
    
    public PreAuthResponse() {
        super();
    }
    
    public PreAuthResponse(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget widget, @org.jetbrains.annotations.Nullable
    java.lang.String publicKey, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.App app) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Widget component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Widget getWidget() {
        return null;
    }
    
    public final void setWidget(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPublicKey() {
        return null;
    }
    
    public final void setPublicKey(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.App component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.App getApp() {
        return null;
    }
    
    public final void setApp(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.App p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest toAuthRequest(@org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.String email) {
        return null;
    }
    
    private final int getNearestFirstIndex(java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> $this$getNearestFirstIndex) {
        return 0;
    }
}