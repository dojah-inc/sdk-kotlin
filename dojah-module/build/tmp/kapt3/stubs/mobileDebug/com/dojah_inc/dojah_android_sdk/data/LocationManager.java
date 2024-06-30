package com.dojah_inc.dojah_android_sdk.data;

import java.lang.System;

@android.annotation.SuppressLint(value = {"MissingPermission"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0019J8\u0010\u001b\u001a\u00020\n2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/data/LocationManager;", "", "context", "Landroid/content/Context;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "(Landroid/content/Context;Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;)V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "hasPermission", "", "getHasPermission", "()Z", "setHasPermission", "(Z)V", "lastLocation", "Lkotlin/Pair;", "", "getLastLocation", "()Lkotlin/Pair;", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "startLocationUpdates", "", "stopLocationUpdates", "withinRange", "selectedLocation", "deviceLocation", "range", "dojah-module_mobileDebug"})
public final class LocationManager {
    private final android.content.Context context = null;
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    private boolean hasPermission = false;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    private com.google.android.gms.location.LocationRequest locationRequest;
    private com.google.android.gms.location.LocationCallback locationCallback;
    
    public LocationManager(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager) {
        super();
    }
    
    public final boolean getHasPermission() {
        return false;
    }
    
    public final void setHasPermission(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.Pair<java.lang.Double, java.lang.Double> getLastLocation() {
        return null;
    }
    
    public final void startLocationUpdates() {
    }
    
    public final void stopLocationUpdates() {
    }
    
    public final boolean withinRange(@org.jetbrains.annotations.NotNull
    kotlin.Pair<java.lang.Double, java.lang.Double> selectedLocation, @org.jetbrains.annotations.NotNull
    kotlin.Pair<java.lang.Double, java.lang.Double> deviceLocation, double range) {
        return false;
    }
}