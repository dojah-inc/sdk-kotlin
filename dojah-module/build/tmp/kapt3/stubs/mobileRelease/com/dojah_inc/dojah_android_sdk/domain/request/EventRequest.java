package com.dojah_inc.dojah_android_sdk.domain.request;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000b\u00100\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J~\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u00102J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00106\u001a\u00020\u0003H\u00d6\u0001J\t\u00107\u001a\u00020\u0006H\u00d6\u0001R \u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\n\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b&\u0010\u0014\"\u0004\b\'\u0010\u0016\u00a8\u00068"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "", "verificationId", "", "stepNumber", "eventType", "", "eventValue", "services", "", "sessionId", "appId", "cost", "pageKey", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "setAppId", "(Ljava/lang/String;)V", "getCost", "()Ljava/lang/Integer;", "setCost", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getEventType", "setEventType", "getEventValue", "setEventValue", "getPageKey", "setPageKey", "getServices", "()Ljava/util/List;", "setServices", "(Ljava/util/List;)V", "getSessionId", "setSessionId", "getStepNumber", "setStepNumber", "getVerificationId", "setVerificationId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "equals", "", "other", "hashCode", "toString", "dojah-module_mobileRelease"})
public final class EventRequest {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "verification_id")
    private java.lang.Integer verificationId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "step_number")
    private java.lang.Integer stepNumber;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "event_type")
    private java.lang.String eventType;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "event_value")
    private java.lang.String eventValue;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "services")
    private java.util.List<java.lang.String> services;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "session_id")
    private java.lang.String sessionId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "app_id")
    private java.lang.String appId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "cost")
    private java.lang.Integer cost;
    @org.jetbrains.annotations.Nullable
    private java.lang.String pageKey;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.request.EventRequest copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer verificationId, @org.jetbrains.annotations.Nullable
    java.lang.Integer stepNumber, @org.jetbrains.annotations.Nullable
    java.lang.String eventType, @org.jetbrains.annotations.Nullable
    java.lang.String eventValue, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> services, @org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.Nullable
    java.lang.Integer cost, @org.jetbrains.annotations.Nullable
    java.lang.String pageKey) {
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
    
    public EventRequest() {
        super();
    }
    
    public EventRequest(@org.jetbrains.annotations.Nullable
    java.lang.Integer verificationId, @org.jetbrains.annotations.Nullable
    java.lang.Integer stepNumber, @org.jetbrains.annotations.Nullable
    java.lang.String eventType, @org.jetbrains.annotations.Nullable
    java.lang.String eventValue, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> services, @org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.Nullable
    java.lang.Integer cost, @org.jetbrains.annotations.Nullable
    java.lang.String pageKey) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVerificationId() {
        return null;
    }
    
    public final void setVerificationId(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getStepNumber() {
        return null;
    }
    
    public final void setStepNumber(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEventType() {
        return null;
    }
    
    public final void setEventType(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEventValue() {
        return null;
    }
    
    public final void setEventValue(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getServices() {
        return null;
    }
    
    public final void setServices(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
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
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAppId() {
        return null;
    }
    
    public final void setAppId(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getCost() {
        return null;
    }
    
    public final void setCost(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPageKey() {
        return null;
    }
    
    public final void setPageKey(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
}