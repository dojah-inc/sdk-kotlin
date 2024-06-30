package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0011J\u0010\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000b\u00105\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u000b\u00109\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u008e\u0001\u0010?\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010@J\u0013\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010D\u001a\u00020\u0003H\u00d6\u0001J\t\u0010E\u001a\u00020\u0005H\u00d6\u0001R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R \u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010+\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0013\"\u0004\b1\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0013\"\u0004\b3\u0010\u0015\u00a8\u0006F"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleData;", "", "transactionVolume", "", "weeklyTargetCount", "", "transactionValue", "", "weeklyTargetValue", "statusCount", "statusValue", "user", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleUser;", "startDate", "endDate", "createdAt", "lastUpdated", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;IILcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleUser;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/lang/String;", "setCreatedAt", "(Ljava/lang/String;)V", "getEndDate", "setEndDate", "getLastUpdated", "setLastUpdated", "getStartDate", "setStartDate", "getStatusCount", "()I", "setStatusCount", "(I)V", "getStatusValue", "setStatusValue", "getTransactionValue", "()Ljava/lang/Double;", "setTransactionValue", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getTransactionVolume", "()Ljava/lang/Integer;", "setTransactionVolume", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getUser", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleUser;", "setUser", "(Lcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleUser;)V", "getWeeklyTargetCount", "setWeeklyTargetCount", "getWeeklyTargetValue", "setWeeklyTargetValue", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;IILcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleUser;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/dojah_inc/dojah_android_sdk/domain/responses/DataSampleData;", "equals", "", "other", "hashCode", "toString", "dojah-module_mobileDebug"})
public final class DataSampleData {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "transaction_volume")
    private java.lang.Integer transactionVolume;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "weekly_target_count")
    private java.lang.String weeklyTargetCount;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "transaction_value")
    private java.lang.Double transactionValue;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "weekly_target_value")
    private java.lang.String weeklyTargetValue;
    @com.google.gson.annotations.SerializedName(value = "status_count")
    private int statusCount;
    @com.google.gson.annotations.SerializedName(value = "status_value")
    private int statusValue;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "user")
    private com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser user;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "start_date")
    private java.lang.String startDate;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "end_date")
    private java.lang.String endDate;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "created_at")
    private java.lang.String createdAt;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "last_updated")
    private java.lang.String lastUpdated;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleData copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer transactionVolume, @org.jetbrains.annotations.Nullable
    java.lang.String weeklyTargetCount, @org.jetbrains.annotations.Nullable
    java.lang.Double transactionValue, @org.jetbrains.annotations.Nullable
    java.lang.String weeklyTargetValue, int statusCount, int statusValue, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser user, @org.jetbrains.annotations.Nullable
    java.lang.String startDate, @org.jetbrains.annotations.Nullable
    java.lang.String endDate, @org.jetbrains.annotations.Nullable
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable
    java.lang.String lastUpdated) {
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
    
    public DataSampleData() {
        super();
    }
    
    public DataSampleData(@org.jetbrains.annotations.Nullable
    java.lang.Integer transactionVolume, @org.jetbrains.annotations.Nullable
    java.lang.String weeklyTargetCount, @org.jetbrains.annotations.Nullable
    java.lang.Double transactionValue, @org.jetbrains.annotations.Nullable
    java.lang.String weeklyTargetValue, int statusCount, int statusValue, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser user, @org.jetbrains.annotations.Nullable
    java.lang.String startDate, @org.jetbrains.annotations.Nullable
    java.lang.String endDate, @org.jetbrains.annotations.Nullable
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable
    java.lang.String lastUpdated) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTransactionVolume() {
        return null;
    }
    
    public final void setTransactionVolume(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getWeeklyTargetCount() {
        return null;
    }
    
    public final void setWeeklyTargetCount(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getTransactionValue() {
        return null;
    }
    
    public final void setTransactionValue(@org.jetbrains.annotations.Nullable
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getWeeklyTargetValue() {
        return null;
    }
    
    public final void setWeeklyTargetValue(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getStatusCount() {
        return 0;
    }
    
    public final void setStatusCount(int p0) {
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int getStatusValue() {
        return 0;
    }
    
    public final void setStatusValue(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser getUser() {
        return null;
    }
    
    public final void setUser(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.DataSampleUser p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStartDate() {
        return null;
    }
    
    public final void setStartDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEndDate() {
        return null;
    }
    
    public final void setEndDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreatedAt() {
        return null;
    }
    
    public final void setCreatedAt(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLastUpdated() {
        return null;
    }
    
    public final void setLastUpdated(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
}