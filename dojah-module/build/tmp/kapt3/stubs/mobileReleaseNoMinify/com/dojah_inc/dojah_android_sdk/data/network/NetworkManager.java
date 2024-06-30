package com.dojah_inc.dojah_android_sdk.data.network;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007J\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callbacks", "", "Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager$NetworkChangeCallback;", "connectivityManager", "Landroid/net/ConnectivityManager;", "<set-?>", "", "isConnected", "()Z", "addNetworkChangeCallback", "", "callback", "getDeviceIMEI", "", "removeNetworkCallback", "subscribeToNetworkChanges", "NetworkChangeCallback", "dojah-module_mobileReleaseNoMinify"})
public final class NetworkManager {
    private final android.content.Context context = null;
    private final android.net.ConnectivityManager connectivityManager = null;
    private final java.util.Set<com.dojah_inc.dojah_android_sdk.data.network.NetworkManager.NetworkChangeCallback> callbacks = null;
    private boolean isConnected = true;
    
    public NetworkManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final boolean isConnected() {
        return false;
    }
    
    private final void subscribeToNetworkChanges() {
    }
    
    public final void addNetworkChangeCallback(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.network.NetworkManager.NetworkChangeCallback callback) {
    }
    
    public final void removeNetworkCallback(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.network.NetworkManager.NetworkChangeCallback callback) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceIMEI() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/network/NetworkManager$NetworkChangeCallback;", "", "onNetworkChange", "", "isAvailable", "", "dojah-module_mobileReleaseNoMinify"})
    public static abstract interface NetworkChangeCallback {
        
        public abstract void onNetworkChange(boolean isAvailable);
    }
}