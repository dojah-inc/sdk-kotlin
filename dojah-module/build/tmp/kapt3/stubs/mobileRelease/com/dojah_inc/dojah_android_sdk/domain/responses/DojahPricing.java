package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001:\u0007GHIJKLMB\u0099\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0002\u0010\u0017J\u000b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0015H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\u00b1\u0001\u0010<\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00c6\u0001J\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0006\u0012\u0004\u0018\u00010\n0>J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010C\u001a\u00020DH\u00d6\u0001J\t\u0010E\u001a\u00020?H\u00d6\u0001J\u0014\u0010F\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0006\u0012\u0004\u0018\u00010\u00040>R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0019\u00a8\u0006N"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing;", "", "additionalDocument", "address", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;", "aml", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingAml;", "businessData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingBusinessData;", "businessId", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingDefault;", "countries", "email", "governmentData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentData;", "governmentDataVerification", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentDataVerification;", "id", "index", "phoneNumber", "selfie", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingSelfie;", "signature", "(Ljava/lang/Object;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingAml;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingBusinessData;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingDefault;Ljava/lang/Object;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentData;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentDataVerification;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingDefault;Ljava/lang/Object;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingSelfie;Ljava/lang/Object;)V", "getAdditionalDocument", "()Ljava/lang/Object;", "getAddress", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;", "getAml", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingAml;", "getBusinessData", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingBusinessData;", "getBusinessId", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingDefault;", "getCountries", "getEmail", "getGovernmentData", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentData;", "getGovernmentDataVerification", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentDataVerification;", "getId", "getIndex", "getPhoneNumber", "getSelfie", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingSelfie;", "getSignature", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "defaultMap", "", "", "equals", "", "other", "hashCode", "", "toString", "verificationMap", "DojahPricingAml", "DojahPricingBusinessData", "DojahPricingDefault", "DojahPricingGovernmentData", "DojahPricingGovernmentDataVerification", "DojahPricingSelfie", "DojahPricingVerification", "dojah-module_mobileRelease"})
public final class DojahPricing {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "additional-document")
    private final java.lang.Object additionalDocument = null;
    @org.jetbrains.annotations.Nullable
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification address = null;
    @org.jetbrains.annotations.Nullable
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml aml = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "business-data")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData businessData = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "business-id")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault businessId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object countries = null;
    @org.jetbrains.annotations.Nullable
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification email = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "government-data")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData governmentData = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "government-data-verification")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification governmentDataVerification = null;
    @org.jetbrains.annotations.Nullable
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault id = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object index = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "phone-number")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification phoneNumber = null;
    @org.jetbrains.annotations.Nullable
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie selfie = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object signature = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing copy(@org.jetbrains.annotations.Nullable
    java.lang.Object additionalDocument, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification address, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml aml, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData businessData, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault businessId, @org.jetbrains.annotations.Nullable
    java.lang.Object countries, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification email, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData governmentData, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification governmentDataVerification, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault id, @org.jetbrains.annotations.Nullable
    java.lang.Object index, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification phoneNumber, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie selfie, @org.jetbrains.annotations.Nullable
    java.lang.Object signature) {
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
    
    public DojahPricing(@org.jetbrains.annotations.Nullable
    java.lang.Object additionalDocument, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification address, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml aml, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData businessData, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault businessId, @org.jetbrains.annotations.Nullable
    java.lang.Object countries, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification email, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData governmentData, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification governmentDataVerification, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault id, @org.jetbrains.annotations.Nullable
    java.lang.Object index, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification phoneNumber, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie selfie, @org.jetbrains.annotations.Nullable
    java.lang.Object signature) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAdditionalDocument() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml getAml() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData getBusinessData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault getBusinessId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCountries() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData getGovernmentData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification getGovernmentDataVerification() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie getSelfie() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSignature() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification> verificationMap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault> defaultMap() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingVerification;", "", "verification", "", "(Ljava/lang/String;)V", "getVerification", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingVerification {
        @org.jetbrains.annotations.Nullable
        private final java.lang.String verification = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingVerification copy(@org.jetbrains.annotations.Nullable
        java.lang.String verification) {
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
        
        public DojahPricingVerification(@org.jetbrains.annotations.Nullable
        java.lang.String verification) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getVerification() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingAml;", "", "aml", "", "(Ljava/lang/String;)V", "getAml", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingAml {
        @org.jetbrains.annotations.Nullable
        private final java.lang.String aml = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingAml copy(@org.jetbrains.annotations.Nullable
        java.lang.String aml) {
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
        
        public DojahPricingAml(@org.jetbrains.annotations.Nullable
        java.lang.String aml) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getAml() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingBusinessData;", "", "cac", "", "(Ljava/lang/String;)V", "getCac", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingBusinessData {
        @org.jetbrains.annotations.Nullable
        private final java.lang.String cac = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingBusinessData copy(@org.jetbrains.annotations.Nullable
        java.lang.String cac) {
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
        
        public DojahPricingBusinessData(@org.jetbrains.annotations.Nullable
        java.lang.String cac) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getCac() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingDefault;", "", "default", "", "(Ljava/lang/String;)V", "getDefault", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingDefault {
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingDefault copy(@org.jetbrains.annotations.Nullable
        java.lang.String p0_772401952) {
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
        
        public DojahPricingDefault(@org.jetbrains.annotations.Nullable
        java.lang.String p0_772401952) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getDefault() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00a5\u0001\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u000201H\u00d6\u0001J\u0014\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000303J\t\u00104\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012\u00a8\u00065"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentData;", "", "aoNin", "", "bvn", "bvnAdvance", "dl", "ghDl", "ghVoter", "keDl", "keId", "keKra", "mobile", "nin", "vnin", "zaId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAoNin", "()Ljava/lang/String;", "getBvn", "getBvnAdvance", "getDl", "getGhDl", "getGhVoter", "getKeDl", "getKeId", "getKeKra", "getMobile", "getNin", "getVnin", "getZaId", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingGovernmentData {
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "ao-nin")
        private final java.lang.String aoNin = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String bvn = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String bvnAdvance = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String dl = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "gh-dl")
        private final java.lang.String ghDl = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "gh-voter")
        private final java.lang.String ghVoter = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "ke-dl")
        private final java.lang.String keDl = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "ke-id")
        private final java.lang.String keId = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "ke-kra")
        private final java.lang.String keKra = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String mobile = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String nin = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String vnin = null;
        @org.jetbrains.annotations.Nullable
        @com.google.gson.annotations.SerializedName(value = "za-id")
        private final java.lang.String zaId = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentData copy(@org.jetbrains.annotations.Nullable
        java.lang.String aoNin, @org.jetbrains.annotations.Nullable
        java.lang.String bvn, @org.jetbrains.annotations.Nullable
        java.lang.String bvnAdvance, @org.jetbrains.annotations.Nullable
        java.lang.String dl, @org.jetbrains.annotations.Nullable
        java.lang.String ghDl, @org.jetbrains.annotations.Nullable
        java.lang.String ghVoter, @org.jetbrains.annotations.Nullable
        java.lang.String keDl, @org.jetbrains.annotations.Nullable
        java.lang.String keId, @org.jetbrains.annotations.Nullable
        java.lang.String keKra, @org.jetbrains.annotations.Nullable
        java.lang.String mobile, @org.jetbrains.annotations.Nullable
        java.lang.String nin, @org.jetbrains.annotations.Nullable
        java.lang.String vnin, @org.jetbrains.annotations.Nullable
        java.lang.String zaId) {
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
        
        public DojahPricingGovernmentData(@org.jetbrains.annotations.Nullable
        java.lang.String aoNin, @org.jetbrains.annotations.Nullable
        java.lang.String bvn, @org.jetbrains.annotations.Nullable
        java.lang.String bvnAdvance, @org.jetbrains.annotations.Nullable
        java.lang.String dl, @org.jetbrains.annotations.Nullable
        java.lang.String ghDl, @org.jetbrains.annotations.Nullable
        java.lang.String ghVoter, @org.jetbrains.annotations.Nullable
        java.lang.String keDl, @org.jetbrains.annotations.Nullable
        java.lang.String keId, @org.jetbrains.annotations.Nullable
        java.lang.String keKra, @org.jetbrains.annotations.Nullable
        java.lang.String mobile, @org.jetbrains.annotations.Nullable
        java.lang.String nin, @org.jetbrains.annotations.Nullable
        java.lang.String vnin, @org.jetbrains.annotations.Nullable
        java.lang.String zaId) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getAoNin() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getBvn() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getBvnAdvance() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getDl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getGhDl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getGhVoter() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getKeDl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getKeId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getKeKra() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getMobile() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getNin() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getVnin() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getZaId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> toMap() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u001a"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingGovernmentDataVerification;", "", "emailOtp", "", "otp", "selfie", "video", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmailOtp", "()Ljava/lang/String;", "getOtp", "getSelfie", "getVideo", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingGovernmentDataVerification {
        @org.jetbrains.annotations.Nullable
        private final java.lang.String emailOtp = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String otp = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String selfie = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String video = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingGovernmentDataVerification copy(@org.jetbrains.annotations.Nullable
        java.lang.String emailOtp, @org.jetbrains.annotations.Nullable
        java.lang.String otp, @org.jetbrains.annotations.Nullable
        java.lang.String selfie, @org.jetbrains.annotations.Nullable
        java.lang.String video) {
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
        
        public DojahPricingGovernmentDataVerification(@org.jetbrains.annotations.Nullable
        java.lang.String emailOtp, @org.jetbrains.annotations.Nullable
        java.lang.String otp, @org.jetbrains.annotations.Nullable
        java.lang.String selfie, @org.jetbrains.annotations.Nullable
        java.lang.String video) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getEmailOtp() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getOtp() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getSelfie() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getVideo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> toMap() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0012J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing$DojahPricingSelfie;", "", "selfie", "", "video", "(Ljava/lang/String;Ljava/lang/String;)V", "getSelfie", "()Ljava/lang/String;", "getVideo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "toString", "dojah-module_mobileRelease"})
    public static final class DojahPricingSelfie {
        @org.jetbrains.annotations.Nullable
        private final java.lang.String selfie = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String video = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing.DojahPricingSelfie copy(@org.jetbrains.annotations.Nullable
        java.lang.String selfie, @org.jetbrains.annotations.Nullable
        java.lang.String video) {
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
        
        public DojahPricingSelfie(@org.jetbrains.annotations.Nullable
        java.lang.String selfie, @org.jetbrains.annotations.Nullable
        java.lang.String video) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getSelfie() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getVideo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> toMap() {
            return null;
        }
    }
}