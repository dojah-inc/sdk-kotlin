package com.dojah_inc.dojah_android_sdk.data.network.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001a\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001c\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001e\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010 \u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ5\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\"\u001a\u00020\r2\b\b\u0001\u0010#\u001a\u00020\r2\b\b\u0001\u0010$\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J!\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001e\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ5\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010(\u001a\u00020\r2\b\b\u0001\u0010#\u001a\u00020\r2\b\b\u0001\u0010$\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J+\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010*\u001a\u00020+2\b\b\u0001\u0010,\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J!\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020/H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J!\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u000202H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J!\u00104\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u000205H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J!\u00107\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u00108\u001a\u000209H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:J!\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020<H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J!\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020?H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010@J+\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010B\u001a\u00020\r2\b\b\u0001\u0010C\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010DJ!\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020FH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010G\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006H"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahService;", "", "checkUserIp", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "data", "Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doAuth", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doPreAuth", "widgetType", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserIp", "livenessCheck", "request", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logEvent", "Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookUpBvn", "bvn", "lookUpDriverLicence", "licenseNumber", "lookUpNin", "nin", "lookUpPhoneNumber", "phoneNumber", "lookUpTin", "tin", "companyName", "appId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookUpVNin", "lookupCac", "rcNumber", "makeFinalDecision", "verificationId", "", "sessionId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performImageAnalysis", "Lcom/dojah_inc/dojah_android_sdk/domain/request/ImageAnalysisRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/ImageAnalysisRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAddress", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AddressRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBaseAddress", "Lcom/dojah_inc/dojah_android_sdk/domain/request/BaseAddressRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/BaseAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtp", "body", "Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendUserData", "Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadAdditionalFile", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateOtp", "code", "referenceId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyLiveness", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dojah-module_mobileDebug"})
public abstract interface DojahService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/get-data")
    public abstract java.lang.Object getData(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/pre-auth")
    public abstract java.lang.Object doPreAuth(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "widget_id")
    java.lang.String widgetType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/auth")
    public abstract java.lang.Object doAuth(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/v1/ip")
    public abstract java.lang.Object getUserIp(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/checks")
    public abstract java.lang.Object checkUserIp(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.CheckIpRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/events")
    public abstract java.lang.Object logEvent(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.EventRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/bvn")
    public abstract java.lang.Object lookUpBvn(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "bvn")
    java.lang.String bvn, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/nin")
    public abstract java.lang.Object lookUpNin(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "nin")
    java.lang.String nin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/vnin")
    public abstract java.lang.Object lookUpVNin(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "vnin")
    java.lang.String nin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/dl")
    public abstract java.lang.Object lookUpDriverLicence(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "license_number")
    java.lang.String licenseNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/mobile/basic")
    public abstract java.lang.Object lookUpPhoneNumber(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "phone_number")
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/sms/otp")
    public abstract java.lang.Object sendOtp(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.OtpRequest body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/sms/otp/validate")
    public abstract java.lang.Object validateOtp(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "code")
    java.lang.String code, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "reference_id")
    java.lang.String referenceId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/image/analysis")
    public abstract java.lang.Object performImageAnalysis(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.ImageAnalysisRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/image/check")
    public abstract java.lang.Object livenessCheck(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessCheckRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/image/verify")
    public abstract java.lang.Object verifyLiveness(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessVerifyRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/user-data")
    public abstract java.lang.Object sendUserData(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.UserDataRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/files")
    public abstract java.lang.Object uploadAdditionalFile(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.AdditionalDocRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/decision")
    public abstract java.lang.Object makeFinalDecision(@retrofit2.http.Query(value = "verification_id")
    int verificationId, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "session_id")
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/base-address")
    public abstract java.lang.Object sendBaseAddress(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.BaseAddressRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "widget/kyc/address")
    public abstract java.lang.Object sendAddress(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.dojah_inc.dojah_android_sdk.domain.request.AddressRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/cac")
    public abstract java.lang.Object lookupCac(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "rc_number")
    java.lang.String rcNumber, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "company_name")
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "app_id")
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "widget/kyc/tin")
    public abstract java.lang.Object lookUpTin(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "tin")
    java.lang.String tin, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "company_name")
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "app_id")
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
}