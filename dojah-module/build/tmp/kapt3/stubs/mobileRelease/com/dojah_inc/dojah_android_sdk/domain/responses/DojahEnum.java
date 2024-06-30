package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b`\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00fd\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\"J\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\t\u0010F\u001a\u00020\u0003H\u00c6\u0003J\t\u0010G\u001a\u00020\u0003H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\u0003H\u00c6\u0003J\t\u0010T\u001a\u00020\u0003H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010[\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0003H\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\u0003H\u00c6\u0003J\t\u0010_\u001a\u00020\u0003H\u00c6\u0003J\t\u0010`\u001a\u00020\u0003H\u00c6\u0003J\t\u0010a\u001a\u00020\u0003H\u00c6\u0003J\u00bf\u0002\u0010b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010c\u001a\u00020d2\b\u0010e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010f\u001a\u00020gH\u00d6\u0001J\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020\u00030iJ\t\u0010k\u001a\u00020jH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010 \u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010$R\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0016\u0010\u001b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010$R\u0016\u0010\u001c\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0016\u0010\u001d\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010$R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010$R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010$R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010$R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010$R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u0016\u0010\u001e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010$R\u0016\u0010\u001f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010$R\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010$R\u0016\u0010\u0018\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010$R\u0016\u0010\u0019\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0016\u0010\u001a\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010$R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010$R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010$R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010$\u00a8\u0006l"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "", "bvn", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "nin", "vnin", "dl", "passport", "national", "permit", "custom", "voter", "mobile", "ngDli", "ngPass", "ngNat", "ukRp", "ngCustom", "ngVcard", "ngNinSlip", "selfie", "otp", "ghDl", "ghVoter", "tzNin", "ugId", "ugTelco", "keDl", "keId", "keKra", "saDl", "saId", "cac", "tin", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;)V", "getBvn", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "getCac", "getCustom", "getDl", "getGhDl", "getGhVoter", "getKeDl", "getKeId", "getKeKra", "getMobile", "getNational", "getNgCustom", "getNgDli", "getNgNat", "getNgNinSlip", "getNgPass", "getNgVcard", "getNin", "getOtp", "getPassport", "getPermit", "getSaDl", "getSaId", "getSelfie", "getTin", "getTzNin", "getUgId", "getUgTelco", "getUkRp", "getVnin", "getVoter", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "", "toString", "dojah-module_mobileRelease"})
public final class DojahEnum {
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr bvn = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr nin = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr vnin = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr dl = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr passport = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr national = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr permit = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr custom = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr voter = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr mobile = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-DLI")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngDli = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-PASS")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngPass = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-NAT")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNat = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "UK-RP")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ukRp = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-CUSTOM")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngCustom = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-VCARD")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngVcard = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "NG-NIN-SLIP")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNinSlip = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr selfie = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr otp = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "gh-dl")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghDl = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "gh-voter")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghVoter = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "tz-nin")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tzNin = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "ug-id")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugId = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "ug-telco")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugTelco = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "ke-dl")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keDl = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "ke-id")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keId = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "ke-kra")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keKra = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "sa-dl")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saDl = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "sa-id")
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saId = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr cac = null;
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tin = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum copy(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr bvn, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr nin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr vnin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr dl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr passport, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr national, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr permit, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr custom, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr voter, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr mobile, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngDli, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngPass, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNat, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ukRp, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngCustom, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngVcard, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNinSlip, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr selfie, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr otp, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghVoter, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tzNin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugTelco, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keKra, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr cac, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tin) {
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
    
    public DojahEnum(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr bvn, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr nin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr vnin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr dl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr passport, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr national, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr permit, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr custom, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr voter, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr mobile, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngDli, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngPass, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNat, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ukRp, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngCustom, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngVcard, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ngNinSlip, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr selfie, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr otp, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ghVoter, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tzNin, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr ugTelco, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr keKra, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saDl, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr saId, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr cac, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr tin) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getBvn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getVnin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getDl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getPassport() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNational() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getPermit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getCustom() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getVoter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getMobile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgDli() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgPass() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgNat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getUkRp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgCustom() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgVcard() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getNgNinSlip() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getSelfie() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getOtp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getGhDl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getGhVoter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getTzNin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getUgId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getUgTelco() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getKeDl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getKeId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getKeKra() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getSaDl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component29() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getSaId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component30() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getCac() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr component31() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr getTin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> toMap() {
        return null;
    }
}