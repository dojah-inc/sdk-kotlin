package com.dojah_inc.dojah_android_sdk.domain.request;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0011J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u0010\u00106\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u000b\u00107\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u008a\u0001\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010:J\u0013\u0010;\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010=\u001a\u00020>H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R \u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0013\"\u0004\b.\u0010\u0015\u00a8\u0006@"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "", "publicKey", "", "appId", "type", "reviewProcess", "steps", "", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthReqSteps;", "duplicateCheck", "", "directFeedback", "rules", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget$Rules;", "referenceId", "email", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget$Rules;Ljava/lang/String;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "setAppId", "(Ljava/lang/String;)V", "getDirectFeedback", "()Ljava/lang/Boolean;", "setDirectFeedback", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDuplicateCheck", "setDuplicateCheck", "getEmail", "setEmail", "getPublicKey", "setPublicKey", "getReferenceId", "setReferenceId", "getReviewProcess", "setReviewProcess", "getRules", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget$Rules;", "setRules", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget$Rules;)V", "getSteps", "()Ljava/util/List;", "setSteps", "(Ljava/util/List;)V", "getType", "setType", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/dojah_inc/dojah_android_sdk/domain/responses/Widget$Rules;Ljava/lang/String;Ljava/lang/String;)Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "equals", "other", "hashCode", "", "toString", "dojah-module_mobileReleaseNoMinify"})
public final class AuthRequest {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "public_key")
    private java.lang.String publicKey;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "app_id")
    private java.lang.String appId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "type")
    private java.lang.String type;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "review_process")
    private java.lang.String reviewProcess;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "steps")
    private java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> steps;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "duplicate_check")
    private java.lang.Boolean duplicateCheck;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "direct_feedback")
    private java.lang.Boolean directFeedback;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "rules")
    private com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules rules;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "reference_id")
    private java.lang.String referenceId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "email")
    private java.lang.String email;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest copy(@org.jetbrains.annotations.Nullable
    java.lang.String publicKey, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.String reviewProcess, @org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> steps, @org.jetbrains.annotations.Nullable
    java.lang.Boolean duplicateCheck, @org.jetbrains.annotations.Nullable
    java.lang.Boolean directFeedback, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules rules, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.String email) {
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
    
    public AuthRequest() {
        super();
    }
    
    public AuthRequest(@org.jetbrains.annotations.Nullable
    java.lang.String publicKey, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.String reviewProcess, @org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> steps, @org.jetbrains.annotations.Nullable
    java.lang.Boolean duplicateCheck, @org.jetbrains.annotations.Nullable
    java.lang.Boolean directFeedback, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules rules, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.String email) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
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
    public final java.lang.String component2() {
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
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReviewProcess() {
        return null;
    }
    
    public final void setReviewProcess(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> getSteps() {
        return null;
    }
    
    public final void setSteps(@org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.request.AuthReqSteps> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getDuplicateCheck() {
        return null;
    }
    
    public final void setDuplicateCheck(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getDirectFeedback() {
        return null;
    }
    
    public final void setDirectFeedback(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules getRules() {
        return null;
    }
    
    public final void setRules(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.Widget.Rules p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReferenceId() {
        return null;
    }
    
    public final void setReferenceId(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
}