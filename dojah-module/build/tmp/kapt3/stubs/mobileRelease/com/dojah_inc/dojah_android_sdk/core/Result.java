package com.dojah_inc.dojah_android_sdk.core;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result;", "R", "", "()V", "Error", "Loading", "Success", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Loading;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Success;", "dojah-module_mobileRelease"})
public abstract class Result<R extends java.lang.Object> {
    
    private Result() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001H\u00c6\u0001\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0013\u0010\u0003\u001a\u00028\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0013"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Success;", "T", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/dojah_inc/dojah_android_sdk/core/Result$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "dojah-module_mobileRelease"})
    public static final class Success<T extends java.lang.Object> extends com.dojah_inc.dojah_android_sdk.core.Result<T> {
        private final T data = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.core.Result.Success<T> copy(T data) {
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
        
        public Success(T data) {
            super();
        }
        
        public final T component1() {
            return null;
        }
        
        public final T getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Loading;", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "", "()V", "dojah-module_mobileRelease"})
    public static final class Loading extends com.dojah_inc.dojah_android_sdk.core.Result {
        @org.jetbrains.annotations.NotNull
        public static final com.dojah_inc.dojah_android_sdk.core.Result.Loading INSTANCE = null;
        
        private Loading() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "", "()V", "ApiError", "NetworkError", "NoDataError", "TimeoutError", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$ApiError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$NetworkError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$NoDataError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$TimeoutError;", "dojah-module_mobileRelease"})
    public static abstract class Error extends com.dojah_inc.dojah_android_sdk.core.Result {
        
        private Error() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$NetworkError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "()V", "dojah-module_mobileRelease"})
        public static final class NetworkError extends com.dojah_inc.dojah_android_sdk.core.Result.Error {
            @org.jetbrains.annotations.NotNull
            public static final com.dojah_inc.dojah_android_sdk.core.Result.Error.NetworkError INSTANCE = null;
            
            private NetworkError() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$TimeoutError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "()V", "dojah-module_mobileRelease"})
        public static final class TimeoutError extends com.dojah_inc.dojah_android_sdk.core.Result.Error {
            @org.jetbrains.annotations.NotNull
            public static final com.dojah_inc.dojah_android_sdk.core.Result.Error.TimeoutError INSTANCE = null;
            
            private TimeoutError() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0012"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$NoDataError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "code", "", "(Ljava/lang/Integer;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "copy", "(Ljava/lang/Integer;)Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$NoDataError;", "equals", "", "other", "", "hashCode", "toString", "", "dojah-module_mobileRelease"})
        public static final class NoDataError extends com.dojah_inc.dojah_android_sdk.core.Result.Error {
            @org.jetbrains.annotations.Nullable
            private final java.lang.Integer code = null;
            
            @org.jetbrains.annotations.NotNull
            public final com.dojah_inc.dojah_android_sdk.core.Result.Error.NoDataError copy(@org.jetbrains.annotations.Nullable
            java.lang.Integer code) {
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
            
            public NoDataError() {
                super();
            }
            
            public NoDataError(@org.jetbrains.annotations.Nullable
            java.lang.Integer code) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer getCode() {
                return null;
            }
        }
        
        /**
         * Error gotten from the response body
         * It sends the entire result unlike other errors so the receiver will know exactly
         * how to handle it
         * It is very similar to Success, but the fact that it is an error completely changes its usage
         */
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u0017\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ2\u0010\u000f\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$ApiError;", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "error", "", "", "code", "", "(Ljava/util/Map;Ljava/lang/Integer;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getError", "()Ljava/util/Map;", "component1", "component2", "copy", "(Ljava/util/Map;Ljava/lang/Integer;)Lcom/dojah_inc/dojah_android_sdk/core/Result$Error$ApiError;", "equals", "", "other", "hashCode", "toString", "", "dojah-module_mobileRelease"})
        public static final class ApiError extends com.dojah_inc.dojah_android_sdk.core.Result.Error {
            @org.jetbrains.annotations.Nullable
            private final java.util.Map<java.lang.Object, java.lang.Object> error = null;
            @org.jetbrains.annotations.Nullable
            private final java.lang.Integer code = null;
            
            /**
             * Error gotten from the response body
             * It sends the entire result unlike other errors so the receiver will know exactly
             * how to handle it
             * It is very similar to Success, but the fact that it is an error completely changes its usage
             */
            @org.jetbrains.annotations.NotNull
            public final com.dojah_inc.dojah_android_sdk.core.Result.Error.ApiError copy(@org.jetbrains.annotations.Nullable
            java.util.Map<java.lang.Object, ? extends java.lang.Object> error, @org.jetbrains.annotations.Nullable
            java.lang.Integer code) {
                return null;
            }
            
            /**
             * Error gotten from the response body
             * It sends the entire result unlike other errors so the receiver will know exactly
             * how to handle it
             * It is very similar to Success, but the fact that it is an error completely changes its usage
             */
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            /**
             * Error gotten from the response body
             * It sends the entire result unlike other errors so the receiver will know exactly
             * how to handle it
             * It is very similar to Success, but the fact that it is an error completely changes its usage
             */
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            /**
             * Error gotten from the response body
             * It sends the entire result unlike other errors so the receiver will know exactly
             * how to handle it
             * It is very similar to Success, but the fact that it is an error completely changes its usage
             */
            @org.jetbrains.annotations.NotNull
            @java.lang.Override
            public java.lang.String toString() {
                return null;
            }
            
            public ApiError(@org.jetbrains.annotations.Nullable
            java.util.Map<java.lang.Object, ? extends java.lang.Object> error, @org.jetbrains.annotations.Nullable
            java.lang.Integer code) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.util.Map<java.lang.Object, java.lang.Object> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.util.Map<java.lang.Object, java.lang.Object> getError() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer getCode() {
                return null;
            }
        }
    }
}