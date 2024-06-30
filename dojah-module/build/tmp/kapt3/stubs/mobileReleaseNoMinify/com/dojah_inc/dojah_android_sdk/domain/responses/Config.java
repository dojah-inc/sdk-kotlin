package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\bb\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u001eJ\u000b\u0010g\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010h\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010i\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010j\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010k\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000b\u0010l\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010m\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003\u00a2\u0006\u0002\u0010_J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010q\u001a\u00020\u0013H\u00c6\u0003J\u0010\u0010r\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\t\u0010s\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010u\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000b\u0010v\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010x\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010y\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010z\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010{\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010|\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010}\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010~\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010\u007f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u00b8\u0002\u0010\u0080\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0003\u0010\u0081\u0001J\u0015\u0010\u0082\u0001\u001a\u00020\u00052\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u0084\u0001\u001a\u00020\u0013H\u00d6\u0001J\n\u0010\u0085\u0001\u001a\u00020\u0003H\u00d6\u0001R\u001e\u0010\u0017\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030$8F\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\"\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b4\u0010(\"\u0004\b5\u0010*R\"\u0010\u001d\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b6\u0010(\"\u0004\b7\u0010*R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b8\u0010(\"\u0004\b9\u0010*R\u001e\u0010\u0018\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030$8F\u00a2\u0006\u0006\u001a\u0004\b?\u0010&R\u0019\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050$8F\u00a2\u0006\u0006\u001a\u0004\bA\u0010&R \u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010/\"\u0004\bC\u00101R \u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010/\"\u0004\bE\u00101R \u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010/\"\u0004\bG\u00101R\"\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bH\u0010(\"\u0004\bI\u0010*R\"\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bJ\u0010(\"\u0004\bK\u0010*R\"\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bL\u0010(\"\u0004\bM\u0010*R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bN\u0010(\"\u0004\bO\u0010*R \u0010\u001c\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010/\"\u0004\bQ\u00101R\"\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bR\u0010(\"\u0004\bS\u0010*R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bT\u0010(\"\u0004\bU\u0010*R \u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010/\"\u0004\bW\u00101R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010/\"\u0004\bY\u00101R\"\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bZ\u0010(\"\u0004\b[\u0010*R\u0017\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00030$8F\u00a2\u0006\u0006\u001a\u0004\b]\u0010&R\"\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010b\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\"\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\bc\u0010(\"\u0004\bd\u0010*R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\be\u0010(\"\u0004\bf\u0010*\u00a8\u0006\u0086\u0001"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/Config;", "", "default", "", "passport", "", "dl", "voter", "vnin", "bvn", "selfie", "otp", "national", "nin", "cac", "tin", "verification", "type", "version", "", "instruction", "information", "title", "brightnessThreshold", "glassesCheck", "disposable", "freeProvider", "mode", "phone", "flipCamera", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getBrightnessThreshold", "()I", "setBrightnessThreshold", "(I)V", "businessTypes", "", "getBusinessTypes", "()Ljava/util/List;", "getBvn", "()Ljava/lang/Boolean;", "setBvn", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCac", "setCac", "getDefault", "()Ljava/lang/String;", "setDefault", "(Ljava/lang/String;)V", "getDisposable", "setDisposable", "getDl", "setDl", "getFlipCamera", "setFlipCamera", "getFreeProvider", "setFreeProvider", "getGlassesCheck", "()Z", "setGlassesCheck", "(Z)V", "govIds", "getGovIds", "ids", "getIds", "getInformation", "setInformation", "getInstruction", "setInstruction", "getMode", "setMode", "getNational", "setNational", "getNin", "setNin", "getOtp", "setOtp", "getPassport", "setPassport", "getPhone", "setPhone", "getSelfie", "setSelfie", "getTin", "setTin", "getTitle", "setTitle", "getType", "setType", "getVerification", "setVerification", "verificationMethods", "getVerificationMethods", "getVersion", "()Ljava/lang/Integer;", "setVersion", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getVnin", "setVnin", "getVoter", "setVoter", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/dojah_inc/dojah_android_sdk/domain/responses/Config;", "equals", "other", "hashCode", "toString", "dojah-module_mobileReleaseNoMinify"})
public final class Config {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "passport")
    private java.lang.Boolean passport;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "dl")
    private java.lang.Boolean dl;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "voter")
    private java.lang.Boolean voter;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "vnin")
    private java.lang.Boolean vnin;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "bvn")
    private java.lang.Boolean bvn;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "selfie")
    private java.lang.Boolean selfie;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "otp")
    private java.lang.Boolean otp;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "national")
    private java.lang.Boolean national;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nin")
    private java.lang.Boolean nin;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "cac")
    private java.lang.Boolean cac;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "tin")
    private java.lang.Boolean tin;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "verification")
    private java.lang.Boolean verification;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "type")
    private java.lang.String type;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "version")
    private java.lang.Integer version;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "instruction")
    private java.lang.String instruction;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "information")
    private java.lang.String information;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "title")
    private java.lang.String title;
    @com.google.gson.annotations.SerializedName(value = "brightnessThreshold")
    private int brightnessThreshold;
    @com.google.gson.annotations.SerializedName(value = "glassesCheck")
    private boolean glassesCheck;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "disposable")
    private java.lang.Boolean disposable;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "freeProvider")
    private java.lang.Boolean freeProvider;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "mode")
    private java.lang.String mode;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "phone")
    private java.lang.String phone;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "flipCamera")
    private java.lang.Boolean flipCamera;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Config copy(@org.jetbrains.annotations.Nullable
    java.lang.String p0_772401952, @org.jetbrains.annotations.Nullable
    java.lang.Boolean passport, @org.jetbrains.annotations.Nullable
    java.lang.Boolean dl, @org.jetbrains.annotations.Nullable
    java.lang.Boolean voter, @org.jetbrains.annotations.Nullable
    java.lang.Boolean vnin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean bvn, @org.jetbrains.annotations.Nullable
    java.lang.Boolean selfie, @org.jetbrains.annotations.Nullable
    java.lang.Boolean otp, @org.jetbrains.annotations.Nullable
    java.lang.Boolean national, @org.jetbrains.annotations.Nullable
    java.lang.Boolean nin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean cac, @org.jetbrains.annotations.Nullable
    java.lang.Boolean tin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean verification, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Integer version, @org.jetbrains.annotations.Nullable
    java.lang.String instruction, @org.jetbrains.annotations.Nullable
    java.lang.String information, @org.jetbrains.annotations.Nullable
    java.lang.String title, int brightnessThreshold, boolean glassesCheck, @org.jetbrains.annotations.Nullable
    java.lang.Boolean disposable, @org.jetbrains.annotations.Nullable
    java.lang.Boolean freeProvider, @org.jetbrains.annotations.Nullable
    java.lang.String mode, @org.jetbrains.annotations.Nullable
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    java.lang.Boolean flipCamera) {
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
    
    public Config() {
        super();
    }
    
    public Config(@org.jetbrains.annotations.Nullable
    java.lang.String p0_772401952, @org.jetbrains.annotations.Nullable
    java.lang.Boolean passport, @org.jetbrains.annotations.Nullable
    java.lang.Boolean dl, @org.jetbrains.annotations.Nullable
    java.lang.Boolean voter, @org.jetbrains.annotations.Nullable
    java.lang.Boolean vnin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean bvn, @org.jetbrains.annotations.Nullable
    java.lang.Boolean selfie, @org.jetbrains.annotations.Nullable
    java.lang.Boolean otp, @org.jetbrains.annotations.Nullable
    java.lang.Boolean national, @org.jetbrains.annotations.Nullable
    java.lang.Boolean nin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean cac, @org.jetbrains.annotations.Nullable
    java.lang.Boolean tin, @org.jetbrains.annotations.Nullable
    java.lang.Boolean verification, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Integer version, @org.jetbrains.annotations.Nullable
    java.lang.String instruction, @org.jetbrains.annotations.Nullable
    java.lang.String information, @org.jetbrains.annotations.Nullable
    java.lang.String title, int brightnessThreshold, boolean glassesCheck, @org.jetbrains.annotations.Nullable
    java.lang.Boolean disposable, @org.jetbrains.annotations.Nullable
    java.lang.Boolean freeProvider, @org.jetbrains.annotations.Nullable
    java.lang.String mode, @org.jetbrains.annotations.Nullable
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    java.lang.Boolean flipCamera) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDefault() {
        return null;
    }
    
    public final void setDefault(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getPassport() {
        return null;
    }
    
    public final void setPassport(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getDl() {
        return null;
    }
    
    public final void setDl(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getVoter() {
        return null;
    }
    
    public final void setVoter(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getVnin() {
        return null;
    }
    
    public final void setVnin(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getBvn() {
        return null;
    }
    
    public final void setBvn(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getSelfie() {
        return null;
    }
    
    public final void setSelfie(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getOtp() {
        return null;
    }
    
    public final void setOtp(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getNational() {
        return null;
    }
    
    public final void setNational(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getNin() {
        return null;
    }
    
    public final void setNin(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getCac() {
        return null;
    }
    
    public final void setCac(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getTin() {
        return null;
    }
    
    public final void setTin(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getVerification() {
        return null;
    }
    
    public final void setVerification(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVersion() {
        return null;
    }
    
    public final void setVersion(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getInstruction() {
        return null;
    }
    
    public final void setInstruction(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getInformation() {
        return null;
    }
    
    public final void setInformation(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int component19() {
        return 0;
    }
    
    public final int getBrightnessThreshold() {
        return 0;
    }
    
    public final void setBrightnessThreshold(int p0) {
    }
    
    public final boolean component20() {
        return false;
    }
    
    public final boolean getGlassesCheck() {
        return false;
    }
    
    public final void setGlassesCheck(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getDisposable() {
        return null;
    }
    
    public final void setDisposable(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getFreeProvider() {
        return null;
    }
    
    public final void setFreeProvider(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMode() {
        return null;
    }
    
    public final void setMode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPhone() {
        return null;
    }
    
    public final void setPhone(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getFlipCamera() {
        return null;
    }
    
    public final void setFlipCamera(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Boolean> getIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getGovIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getVerificationMethods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getBusinessTypes() {
        return null;
    }
}