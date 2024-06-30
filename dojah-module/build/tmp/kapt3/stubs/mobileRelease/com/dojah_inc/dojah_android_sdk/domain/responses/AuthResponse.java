package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010!J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\fH\u00c6\u0003Jb\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0002\u0010-J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u000200J\u0013\u00101\u001a\u00020\u00062\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u000204H\u00d6\u0001J\t\u00105\u001a\u00020\u0003H\u00d6\u0001R \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u00a8\u00066"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "", "sessionId", "", "companyName", "whiteLabel", "", "ucode", "environment", "initData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;", "app", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;)V", "getApp", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;", "setApp", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;)V", "getCompanyName", "()Ljava/lang/String;", "setCompanyName", "(Ljava/lang/String;)V", "getEnvironment", "setEnvironment", "getInitData", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;", "setInitData", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;)V", "getSessionId", "setSessionId", "getUcode", "setUcode", "getWhiteLabel", "()Ljava/lang/Boolean;", "setWhiteLabel", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/InitData;Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;)Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "copyUpdateFromEmail", "emailResponse", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SimpleResponse;", "equals", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
public final class AuthResponse {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "session_id")
    private java.lang.String sessionId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "company_name")
    private java.lang.String companyName;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "white_label")
    private java.lang.Boolean whiteLabel;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "ucode")
    private java.lang.String ucode;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "environment")
    private java.lang.String environment;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "init_data")
    private com.dojah_inc.dojah_android_sdk.domain.responses.InitData initData;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "app")
    private com.dojah_inc.dojah_android_sdk.domain.responses.App app;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse copy(@org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String companyName, @org.jetbrains.annotations.Nullable
    java.lang.Boolean whiteLabel, @org.jetbrains.annotations.Nullable
    java.lang.String ucode, @org.jetbrains.annotations.Nullable
    java.lang.String environment, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.InitData initData, @org.jetbrains.annotations.Nullable
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
    
    public AuthResponse() {
        super();
    }
    
    public AuthResponse(@org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String companyName, @org.jetbrains.annotations.Nullable
    java.lang.Boolean whiteLabel, @org.jetbrains.annotations.Nullable
    java.lang.String ucode, @org.jetbrains.annotations.Nullable
    java.lang.String environment, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.InitData initData, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.App app) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSessionId() {
        return null;
    }
    
    public final void setSessionId(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCompanyName() {
        return null;
    }
    
    public final void setCompanyName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getWhiteLabel() {
        return null;
    }
    
    public final void setWhiteLabel(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUcode() {
        return null;
    }
    
    public final void setUcode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEnvironment() {
        return null;
    }
    
    public final void setEnvironment(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.InitData component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.InitData getInitData() {
        return null;
    }
    
    public final void setInitData(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.InitData p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.App component7() {
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
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse copyUpdateFromEmail(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse emailResponse) {
        return null;
    }
}