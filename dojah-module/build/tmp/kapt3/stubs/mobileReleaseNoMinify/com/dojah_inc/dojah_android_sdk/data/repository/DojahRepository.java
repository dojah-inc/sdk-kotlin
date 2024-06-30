package com.dojah_inc.dojah_android_sdk.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ%\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J%\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00150\u00142\u0006\u0010\u0017\u001a\u00020\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ%\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00150\u00142\u0006\u0010\"\u001a\u00020#H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J%\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00150\u00142\u0006\u0010\'\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J*\u0010*\u001a\n\u0012\u0004\u0012\u0002H+\u0018\u00010\f\"\u0004\b\u0000\u0010+2\u0006\u0010,\u001a\u00020(2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H+0.J\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00150\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J%\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00150\u00142\u0006\u00104\u001a\u000205H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J%\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u00150\u00142\u0006\u00109\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J%\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0\u00150\u00142\u0006\u0010<\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J%\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\u00150\u00142\u0006\u0010?\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J%\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00150\u00142\u0006\u0010B\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J5\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00150\u00142\u0006\u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020(2\u0006\u0010G\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010HJ5\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00150\u00142\u0006\u0010J\u001a\u00020(2\u0006\u0010F\u001a\u00020(2\u0006\u0010G\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010HJ-\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0\u00150\u00142\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010PJ%\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020R0\u00150\u00142\u0006\u0010S\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J-\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0\u00150\u00142\u0006\u0010S\u001a\u00020(2\u0006\u0010V\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010WJ\u0014\u0010X\u001a\u00020\u001f2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020!0\fJ%\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00150\u00142\u0006\u0010[\u001a\u00020\\H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010]J5\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00150\u00142\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020`2\u0006\u0010b\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010cJ%\u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020e0\u00150\u00142\u0006\u0010\u0017\u001a\u00020fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010gJ%\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00150\u00142\u0006\u0010\u0017\u001a\u00020iH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010jJ%\u0010k\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00150\u00142\u0006\u0010\u0017\u001a\u00020lH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010mJ-\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0\u00150\u00142\u0006\u0010p\u001a\u00020(2\u0006\u0010q\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010WJ%\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0\u00150\u00142\u0006\u0010\u0017\u001a\u00020tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010uR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006v"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "Lcom/dojah_inc/dojah_android_sdk/data/repository/base/BaseRepository;", "networkManager", "Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;", "gson", "Lcom/google/gson/Gson;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "service", "Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahService;", "(Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;Lcom/google/gson/Gson;Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;Lcom/dojah_inc/dojah_android_sdk/data/network/service/DojahService;)V", "dojahPricing", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Success;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing;", "getDojahPricing", "()Lcom/dojah_inc/dojah_android_sdk/core/Result$Success;", "getDojahEnum", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "getGetDojahEnum", "checkLiveness", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/LivenessCheckResponse;", "request", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkUserIp", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/CheckIpResponse;", "Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/CheckIpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllAuthData", "", "doAuth", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "authRequest", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AuthRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doPreAuth", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/PreAuthResponse;", "widgetId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalResponse", "T", "key", "responseClass", "Ljava/lang/Class;", "getUserIp", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/GetIpResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logEvent", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SimpleResponse;", "data", "Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookUpBvn", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/BvnLookUpResponse;", "bvn", "lookUpDriverLicense", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DriverLicenceResponse;", "licenseNumber", "lookUpNin", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/NinLookUpResponse;", "nin", "lookUpVnin", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/VninLookUpResponse;", "vNin", "lookupCac", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/BizLookupResponse;", "rcNumber", "companyName", "appId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookupTin", "tin", "makeFinalDecision", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DecisionResponse;", "verificationId", "", "sessionId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performDocImageAnalysis", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DocImageAnalysisResponse;", "image", "performImageAnalysis", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/ImageAnalysisResponse;", "imageType", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveAuthDetailsToPrefs", "result", "sendAddress", "match", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBaseAddress", "selectedAddressLatitude", "", "selectedAddressLongitude", "addressName", "(DDLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtp", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SendOtpResponse;", "Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/OtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendUserData", "Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/UserDataRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadAdditionalFile", "Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/AdditionalDocRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateOtp", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/ValidateOtpResponse;", "code", "referenceId", "verifyLiveness", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/LivenessVerifyResponse;", "Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;", "(Lcom/dojah_inc/dojah_android_sdk/domain/request/LivenessVerifyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dojah-module_mobileReleaseNoMinify"})
public final class DojahRepository extends com.dojah_inc.dojah_android_sdk.data.repository.base.BaseRepository {
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    private final com.dojah_inc.dojah_android_sdk.data.network.service.DojahService service = null;
    
    public DojahRepository(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.network.NetworkManager networkManager, @org.jetbrains.annotations.NotNull
    com.google.gson.Gson gson, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.network.service.DojahService service) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.core.Result.Success<com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum> getGetDojahEnum() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.core.Result.Success<com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing> getDojahPricing() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doPreAuth(@org.jetbrains.annotations.NotNull
    java.lang.String widgetId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doAuth(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.AuthRequest authRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse>>> continuation) {
        return null;
    }
    
    public final void saveAuthDetailsToPrefs(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.Result.Success<com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse> result) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserIp(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.GetIpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkUserIp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.CheckIpRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.CheckIpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object logEvent(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.EventRequest data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookUpBvn(@org.jetbrains.annotations.NotNull
    java.lang.String bvn, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.BvnLookUpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookUpNin(@org.jetbrains.annotations.NotNull
    java.lang.String nin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.NinLookUpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookUpVnin(@org.jetbrains.annotations.NotNull
    java.lang.String vNin, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.VninLookUpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookUpDriverLicense(@org.jetbrains.annotations.NotNull
    java.lang.String licenseNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DriverLicenceResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.OtpRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object validateOtp(@org.jetbrains.annotations.NotNull
    java.lang.String code, @org.jetbrains.annotations.NotNull
    java.lang.String referenceId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object performImageAnalysis(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    java.lang.String imageType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ImageAnalysisResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object performDocImageAnalysis(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DocImageAnalysisResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkLiveness(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessCheckRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessCheckResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object verifyLiveness(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.LivenessVerifyRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessVerifyResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendUserData(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.UserDataRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object uploadAdditionalFile(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.request.AdditionalDocRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object makeFinalDecision(int verificationId, @org.jetbrains.annotations.NotNull
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DecisionResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendBaseAddress(double selectedAddressLatitude, double selectedAddressLongitude, @org.jetbrains.annotations.NotNull
    java.lang.String addressName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendAddress(boolean match, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookupCac(@org.jetbrains.annotations.NotNull
    java.lang.String rcNumber, @org.jetbrains.annotations.NotNull
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.BizLookupResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object lookupTin(@org.jetbrains.annotations.NotNull
    java.lang.String tin, @org.jetbrains.annotations.NotNull
    java.lang.String companyName, @org.jetbrains.annotations.NotNull
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.BizLookupResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final <T extends java.lang.Object>com.dojah_inc.dojah_android_sdk.core.Result.Success<T> getLocalResponse(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.Class<T> responseClass) {
        return null;
    }
    
    public final void deleteAllAuthData() {
    }
}