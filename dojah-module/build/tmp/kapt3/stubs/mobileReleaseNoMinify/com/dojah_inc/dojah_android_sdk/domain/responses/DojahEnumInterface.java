package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005\u00a8\u0006 "}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnumInterface;", "", "abbr", "", "getAbbr", "()Ljava/lang/String;", "enum", "getEnum", "id", "getId", "idName", "getIdName", "inputMode", "getInputMode", "inputType", "getInputType", "maxLength", "getMaxLength", "minLength", "getMinLength", "name", "getName", "placeholder", "getPlaceholder", "spanid", "getSpanid", "subtext", "getSubtext", "subtext2", "getSubtext2", "value", "getValue", "dojah-module_mobileReleaseNoMinify"})
public abstract interface DojahEnumInterface {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getId();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getName();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getIdName();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getAbbr();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getSubtext();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getSubtext2();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getPlaceholder();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getEnum();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getSpanid();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getValue();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getInputType();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getInputMode();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getMinLength();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.String getMaxLength();
}