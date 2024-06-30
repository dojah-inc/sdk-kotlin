package com.dojah_inc.dojah_android_sdk.data.security;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/security/SecurityManager;", "", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "(Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;)V", "decrypt", "", "text", "encrypt", "getKey", "", "salt", "Companion", "dojah-module_mobileReleaseNoMinify"})
public final class SecurityManager {
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    @org.jetbrains.annotations.NotNull
    private static final com.dojah_inc.dojah_android_sdk.data.security.SecurityManager.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    public static final java.lang.String CIPHER = "AES/CBC/PKCS5Padding";
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    public static final java.lang.String INITIALIZATION_VECTOR = "8119745113154120";
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    public static final java.lang.String PLAIN_TEXT = "gsgqGmDGsggGWyyItoKDUKDLOpod";
    @org.jetbrains.annotations.NotNull
    @java.lang.Deprecated
    public static final java.lang.String SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA1";
    @java.lang.Deprecated
    public static final int PASSWORD_ITERATIONS = 30;
    @java.lang.Deprecated
    public static final int KEY_SIZE = 128;
    
    public SecurityManager(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String encrypt(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String decrypt(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
        return null;
    }
    
    private final byte[] getKey(java.lang.String text, java.lang.String salt) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/security/SecurityManager$Companion;", "", "()V", "CIPHER", "", "INITIALIZATION_VECTOR", "KEY_SIZE", "", "PASSWORD_ITERATIONS", "PLAIN_TEXT", "SECRET_KEY_INSTANCE", "dojah-module_mobileReleaseNoMinify"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}