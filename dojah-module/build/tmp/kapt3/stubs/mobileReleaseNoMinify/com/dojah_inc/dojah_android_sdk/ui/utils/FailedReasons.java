package com.dojah_inc.dojah_android_sdk.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b\u00a8\u0006\u001d"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;", "", "code", "", "message", "statusCode", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;I)V", "getCode", "()Ljava/lang/String;", "getMessage", "getStatusCode", "()I", "getGovBizMsg", "idType", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "LOW_BALANCE", "UNKNOWN", "ID_INVALID_NOT_FOUND", "THIRD_PARTY", "INVALID_OTP", "OTP_NOT_SENT", "ID_FAILED_MAX_TIME", "SELFIE_NO_CAPTURE", "VIDEO_NO_CAPTURE", "GOV_ID_CAPTURE", "WIDGET_NOT_AVAILABLE", "GOV_DATA_NOT_AVAILABLE", "Companion", "dojah-module_mobileReleaseNoMinify"})
public enum FailedReasons {
    /*public static final*/ LOW_BALANCE /* = new LOW_BALANCE(null, null, 0) */,
    /*public static final*/ UNKNOWN /* = new UNKNOWN(null, null, 0) */,
    /*public static final*/ ID_INVALID_NOT_FOUND /* = new ID_INVALID_NOT_FOUND(null, null, 0) */,
    /*public static final*/ THIRD_PARTY /* = new THIRD_PARTY(null, null, 0) */,
    /*public static final*/ INVALID_OTP /* = new INVALID_OTP(null, null, 0) */,
    /*public static final*/ OTP_NOT_SENT /* = new OTP_NOT_SENT(null, null, 0) */,
    /*public static final*/ ID_FAILED_MAX_TIME /* = new ID_FAILED_MAX_TIME(null, null, 0) */,
    /*public static final*/ SELFIE_NO_CAPTURE /* = new SELFIE_NO_CAPTURE(null, null, 0) */,
    /*public static final*/ VIDEO_NO_CAPTURE /* = new VIDEO_NO_CAPTURE(null, null, 0) */,
    /*public static final*/ GOV_ID_CAPTURE /* = new GOV_ID_CAPTURE(null, null, 0) */,
    /*public static final*/ WIDGET_NOT_AVAILABLE /* = new WIDGET_NOT_AVAILABLE(null, null, 0) */,
    /*public static final*/ GOV_DATA_NOT_AVAILABLE /* = new GOV_DATA_NOT_AVAILABLE(null, null, 0) */;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String code = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String message = null;
    private final int statusCode = 0;
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons.Companion Companion = null;
    
    FailedReasons(java.lang.String code, java.lang.String message, int statusCode) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final int getStatusCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGovBizMsg(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr idType) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons$Companion;", "", "()V", "getStatusCodeReason", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;", "error", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$ApiError;", "dojah-module_mobileReleaseNoMinify"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons getStatusCodeReason(@org.jetbrains.annotations.NotNull
        com.dojah_inc.dojah_android_sdk.core.Result.Error.ApiError error) {
            return null;
        }
    }
}