package com.dojah_inc.dojah_android_sdk.data.repository.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JE\u0010\u0010\u001a\u0002H\f\"\u0004\b\u0000\u0010\u0011\"\u000e\b\u0001\u0010\f*\b\u0012\u0004\u0012\u0002H\u00110\r2\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013H\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\f0\r\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\f0\u001aH\u0004J\u001f\u0010\u001b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f*\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0004\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u00020\b*\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\nR&\u0010\u000b\u001a\u0004\u0018\u00010\t\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/repository/base/BaseRepository;", "", "networkManager", "Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;", "gson", "Lcom/google/gson/Gson;", "(Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;Lcom/google/gson/Gson;)V", "isSuccess", "", "", "(Ljava/lang/String;)Z", "toJson", "T", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "getToJson", "(Lcom/dojah_inc/dojah_android_sdk/core/Result;)Ljava/lang/String;", "checkNetworkAndStartRequest", "O", "action", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResult", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "successType", "Ljava/lang/Class;", "stringToType", "type", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "Companion", "dojah-module_mobileReleaseNoMinify"})
public class BaseRepository {
    private final com.dojah_inc.dojah_android_sdk.data.network.NetworkManager networkManager = null;
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull
    private static final com.dojah_inc.dojah_android_sdk.data.repository.base.BaseRepository.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    private static final java.lang.String[] successCode = {"success", "00", "ok"};
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    private static final java.lang.String errorCode = "error";
    
    public BaseRepository(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.network.NetworkManager networkManager, @org.jetbrains.annotations.NotNull
    com.google.gson.Gson gson) {
        super();
    }
    
    /**
     * Before sending a request, it checks to ensure internet is available.
     * It also surrounds the action being performed to catch any possible error
     */
    @org.jetbrains.annotations.Nullable
    protected final <O extends java.lang.Object, T extends com.dojah_inc.dojah_android_sdk.core.Result<? extends O>>java.lang.Object checkNetworkAndStartRequest(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> action, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super T> continuation) {
        return null;
    }
    
    private final boolean isSuccess(java.lang.String $this$isSuccess) {
        return false;
    }
    
    /**
     * After a request has been performed, it checks if it is successful or not
     * and returns a [Result], either a [Result.Success] or an [Result.Error].
     * @param titleKey is the key with a possible title error
     * @param messageKey is the key with a possible error message
     */
    @org.jetbrains.annotations.NotNull
    protected final <T extends java.lang.Object>com.dojah_inc.dojah_android_sdk.core.Result<T> getResult(@org.jetbrains.annotations.NotNull
    retrofit2.Response<okhttp3.ResponseBody> $this$getResult, @org.jetbrains.annotations.NotNull
    java.lang.Class<T> successType) {
        return null;
    }
    
    protected final <T extends java.lang.Object>T stringToType(@org.jetbrains.annotations.NotNull
    java.lang.String $this$stringToType, @org.jetbrains.annotations.NotNull
    java.lang.reflect.Type type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    protected final <T extends java.lang.Object>java.lang.String getToJson(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.Result<? extends T> $this$toJson) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u00a8\u0006\f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/repository/base/BaseRepository$Companion;", "", "()V", "errorCode", "", "getErrorCode", "()Ljava/lang/String;", "successCode", "", "getSuccessCode", "()[Ljava/lang/String;", "[Ljava/lang/String;", "dojah-module_mobileReleaseNoMinify"})
    static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String[] getSuccessCode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getErrorCode() {
            return null;
        }
    }
}