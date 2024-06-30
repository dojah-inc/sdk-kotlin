package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005\u00a8\u0006\u0014"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdEntityInterface;", "", "customerID", "", "getCustomerID", "()Ljava/lang/String;", "dob", "getDob", "fName", "getFName", "image", "getImage", "lName", "getLName", "licenseNum", "getLicenseNum", "mName", "getMName", "phoneNumber", "getPhoneNumber", "dojah-module_mobileDebug"})
public abstract interface GovIdEntityInterface {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getDob();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getFName();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getMName();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getLicenseNum();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getLName();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getImage();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getPhoneNumber();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getCustomerID();
}