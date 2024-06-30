package com.dojah_inc.dojah_android_sdk.data.io;

import java.lang.System;

/**
 * Its only Job is to rewrite the files from the Assets to a file directory
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010\u0016\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0011\u0010\u001d\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0011\u0010\u001f\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/FileManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "assetManager", "Landroid/content/res/AssetManager;", "baseDir", "Ljava/io/File;", "<set-?>", "Landroid/net/Uri;", "notificationToneUri", "getNotificationToneUri", "()Landroid/net/Uri;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "directorySize", "", "getDirectorySize", "(Ljava/io/File;)J", "close", "", "copyAssets", "callback", "Lkotlin/Function0;", "copyNotificationTone", "createCameraUri", "Lkotlin/Pair;", "", "rewriteCountries", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rewriteVas", "Companion", "dojah-module_mobileDebug"})
public final class FileManager {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.data.io.FileManager.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ASSET_DIR = "assets";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COUNTRIES_DIR = "countries";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String VAS_DIR = "vas";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String NETWORK_PROVIDER_DIR = "vas/networkprovider";
    private final java.io.File baseDir = null;
    private android.content.res.AssetManager assetManager;
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.Nullable
    private android.net.Uri notificationToneUri;
    
    public FileManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void copyAssets(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    private final java.lang.Object rewriteCountries(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object rewriteVas(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void copyNotificationTone() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri getNotificationToneUri() {
        return null;
    }
    
    private final long getDirectorySize(java.io.File $this$directorySize) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.Pair<android.net.Uri, java.lang.String> createCameraUri() {
        return null;
    }
    
    public final void close() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\n\u0010\b\u001a\u00020\t*\u00020\nJ\n\u0010\u000b\u001a\u00020\t*\u00020\nJ\n\u0010\f\u001a\u00020\t*\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/io/FileManager$Companion;", "", "()V", "ASSET_DIR", "", "COUNTRIES_DIR", "NETWORK_PROVIDER_DIR", "VAS_DIR", "getAssetDirectory", "Ljava/io/File;", "Landroid/content/Context;", "getCacheDirectory", "getSaveDirectory", "dojah-module_mobileDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Returns the storage version of the asset directory
         */
        @org.jetbrains.annotations.NotNull
        public final java.io.File getAssetDirectory(@org.jetbrains.annotations.NotNull
        android.content.Context $this$getAssetDirectory) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.io.File getSaveDirectory(@org.jetbrains.annotations.NotNull
        android.content.Context $this$getSaveDirectory) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.io.File getCacheDirectory(@org.jetbrains.annotations.NotNull
        android.content.Context $this$getCacheDirectory) {
            return null;
        }
    }
}