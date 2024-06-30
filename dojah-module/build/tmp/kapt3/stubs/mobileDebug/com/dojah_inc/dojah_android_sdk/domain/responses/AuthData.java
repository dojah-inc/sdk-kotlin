package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001:Bi\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u000b\u0010,\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003Jr\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u00104J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u00020\u0003H\u00d6\u0001J\t\u00109\u001a\u00020\u0005H\u00d6\u0001R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0011\"\u0004\b\u001a\u0010\u0013R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\"R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\'\u0010\u001c\"\u0004\b(\u0010\u001eR \u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010\u0013\u00a8\u0006;"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;", "", "stepNumber", "", "referenceId", "", "verificationId", "steps", "", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/Step;", "userData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;", "verificationTypeSelected", "sessionId", "countryAlpha2Code", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCountryAlpha2Code", "()Ljava/lang/String;", "setCountryAlpha2Code", "(Ljava/lang/String;)V", "pages", "getPages", "()Ljava/util/List;", "getReferenceId", "setReferenceId", "getSessionId", "setSessionId", "getStepNumber", "()Ljava/lang/Integer;", "setStepNumber", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSteps", "setSteps", "(Ljava/util/List;)V", "getUserData", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;", "setUserData", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;)V", "getVerificationId", "setVerificationId", "getVerificationTypeSelected", "setVerificationTypeSelected", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData;", "equals", "", "other", "hashCode", "toString", "UserData", "dojah-module_mobileDebug"})
public final class AuthData {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "step_number")
    private java.lang.Integer stepNumber;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "reference_id")
    private java.lang.String referenceId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "verification_id")
    private java.lang.Integer verificationId;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "steps")
    private java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> steps;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "user_data")
    private com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData userData;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "verification_type_selected")
    private java.lang.String verificationTypeSelected;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "session_id")
    private java.lang.String sessionId;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "country")
    private java.lang.String countryAlpha2Code;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer stepNumber, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.Integer verificationId, @org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> steps, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData userData, @org.jetbrains.annotations.Nullable
    java.lang.String verificationTypeSelected, @org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String countryAlpha2Code) {
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
    
    public AuthData() {
        super();
    }
    
    public AuthData(@org.jetbrains.annotations.Nullable
    java.lang.Integer stepNumber, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.Integer verificationId, @org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> steps, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData userData, @org.jetbrains.annotations.Nullable
    java.lang.String verificationTypeSelected, @org.jetbrains.annotations.Nullable
    java.lang.String sessionId, @org.jetbrains.annotations.Nullable
    java.lang.String countryAlpha2Code) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
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
    public final java.lang.String component2() {
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
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVerificationId() {
        return null;
    }
    
    public final void setVerificationId(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> getSteps() {
        return null;
    }
    
    public final void setSteps(@org.jetbrains.annotations.NotNull
    java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData getUserData() {
        return null;
    }
    
    public final void setUserData(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getVerificationTypeSelected() {
        return null;
    }
    
    public final void setVerificationTypeSelected(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
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
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCountryAlpha2Code() {
        return null;
    }
    
    public final void setCountryAlpha2Code(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> getPages() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000bJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Ji\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f\u00a8\u0006-"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthData$UserData;", "", "firstName", "", "lastName", "middleName", "dob", "email", "mobile", "nationality", "residenceCountry", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDob", "()Ljava/lang/String;", "setDob", "(Ljava/lang/String;)V", "getEmail", "setEmail", "getFirstName", "setFirstName", "getLastName", "setLastName", "getMiddleName", "setMiddleName", "getMobile", "setMobile", "getNationality", "setNationality", "getResidenceCountry", "setResidenceCountry", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "dojah-module_mobileDebug"})
    public static final class UserData {
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "first_name")
        private java.lang.String firstName;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "last_name")
        private java.lang.String lastName;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "middle_name")
        private java.lang.String middleName;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "dob")
        private java.lang.String dob;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "email")
        private java.lang.String email;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "mobile")
        private java.lang.String mobile;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "nationality")
        private java.lang.String nationality;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "residence_country")
        private java.lang.String residenceCountry;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthData.UserData copy(@org.jetbrains.annotations.Nullable
        java.lang.String firstName, @org.jetbrains.annotations.Nullable
        java.lang.String lastName, @org.jetbrains.annotations.Nullable
        java.lang.String middleName, @org.jetbrains.annotations.Nullable
        java.lang.String dob, @org.jetbrains.annotations.Nullable
        java.lang.String email, @org.jetbrains.annotations.Nullable
        java.lang.String mobile, @org.jetbrains.annotations.Nullable
        java.lang.String nationality, @org.jetbrains.annotations.Nullable
        java.lang.String residenceCountry) {
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
        
        public UserData() {
            super();
        }
        
        public UserData(@org.jetbrains.annotations.Nullable
        java.lang.String firstName, @org.jetbrains.annotations.Nullable
        java.lang.String lastName, @org.jetbrains.annotations.Nullable
        java.lang.String middleName, @org.jetbrains.annotations.Nullable
        java.lang.String dob, @org.jetbrains.annotations.Nullable
        java.lang.String email, @org.jetbrains.annotations.Nullable
        java.lang.String mobile, @org.jetbrains.annotations.Nullable
        java.lang.String nationality, @org.jetbrains.annotations.Nullable
        java.lang.String residenceCountry) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getFirstName() {
            return null;
        }
        
        public final void setFirstName(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getLastName() {
            return null;
        }
        
        public final void setLastName(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getMiddleName() {
            return null;
        }
        
        public final void setMiddleName(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getDob() {
            return null;
        }
        
        public final void setDob(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getEmail() {
            return null;
        }
        
        public final void setEmail(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getMobile() {
            return null;
        }
        
        public final void setMobile(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getNationality() {
            return null;
        }
        
        public final void setNationality(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getResidenceCountry() {
            return null;
        }
        
        public final void setResidenceCountry(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
    }
}