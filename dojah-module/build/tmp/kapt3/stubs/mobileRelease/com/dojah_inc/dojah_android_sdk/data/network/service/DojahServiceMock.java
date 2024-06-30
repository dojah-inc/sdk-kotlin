package com.dojah_inc.dojah_android_sdk.data.network.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001b\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001d\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001f\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010!\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ/\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001f\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ/\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\'\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J\u001f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0014\u001a\u000200H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J!\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0001\u0010\u0014\u001a\u000203H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J!\u00105\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0001\u0010\u0014\u001a\u000206H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107J\u001f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u00109\u001a\u00020:H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J\u001f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0014\u001a\u00020=H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>J\u001f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0014\u001a\u00020@H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ\'\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u001f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0014\u001a\u00020GH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010H\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006I"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahServiceMock;", "Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahService;", "()V", "checkUserIp", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "data", "Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doAuth", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doPreAuth", "widgetType", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserIp", "livenessCheck", "request", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logEvent", "Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookUpBvn", "bvn", "lookUpDriverLicence", "licenseNumber", "lookUpNin", "nin", "lookUpPhoneNumber", "phoneNumber", "lookUpTin", "tin", "companyName", "appId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookUpVNin", "lookupCac", "rcNumber", "makeFinalDecision", "verificationId", "", "sessionId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performImageAnalysis", "Lcom/dojah_inc/dojah_android_sdk/domain/request/ImageAnalysisRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/ImageAnalysisRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAddress", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AddressRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBaseAddress", "Lcom/dojah_inc/dojah_android_sdk/domain/request/BaseAddressRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/BaseAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtp", "body", "Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendUserData", "Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadAdditionalFile", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateOtp", "code", "referenceId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyLiveness", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dojah-module_mobileRelease"})
public final class DojahServiceMock implements com.dojah_inc.dojah_android_sdk.data.network.service.DojahService {
    
    public DojahServiceMock() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getData(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object doPreAuth(@org.jetbrains.annotations.NotNull
    java.lang.String widgetType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object doAuth(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getUserIp(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object checkUserIp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.CheckIpRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object logEvent(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.EventRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpBvn(@org.jetbrains.annotations.NotNull
    java.lang.String bvn, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpNin(@org.jetbrains.annotations.NotNull
    java.lang.String nin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpVNin(@org.jetbrains.annotations.NotNull
    java.lang.String nin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpDriverLicence(@org.jetbrains.annotations.NotNull
    java.lang.String licenseNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpPhoneNumber(@org.jetbrains.annotations.NotNull
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object sendOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.OtpRequest body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object validateOtp(@org.jetbrains.annotations.NotNull
    java.lang.String code, @org.jetbrains.annotations.NotNull
    java.lang.String referenceId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object performImageAnalysis(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.ImageAnalysisRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object livenessCheck(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessCheckRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object verifyLiveness(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessVerifyRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object sendUserData(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.UserDataRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object uploadAdditionalFile(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.AdditionalDocRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object makeFinalDecision(int verificationId, @org.jetbrains.annotations.NotNull
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object sendBaseAddress(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.BaseAddressRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object sendAddress(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.AddressRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookupCac(@org.jetbrains.annotations.NotNull
    java.lang.String rcNumber, @org.jetbrains.annotations.NotNull
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object lookUpTin(@org.jetbrains.annotations.NotNull
    java.lang.String tin, @org.jetbrains.annotations.NotNull
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation) {
        return null;
    }
}