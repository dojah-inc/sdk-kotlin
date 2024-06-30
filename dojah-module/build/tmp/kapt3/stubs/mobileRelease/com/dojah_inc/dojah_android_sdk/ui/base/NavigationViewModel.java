package com.dojah_inc.dojah_android_sdk.ui.base;

import java.lang.System;

/**
 * A ViewModel created in the Activity and used in every Fragment that
 * defines a new destination, which will be sent through this ViewModel
 * to the Activity who will then handle the navigation. This ensures all
 * navigations are performed from one point
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001BB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010+\u001a\u00020,J&\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u000f2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\fJ\u001e\u00101\u001a\u00020,2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\fJ&\u00102\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00162\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\fJ\u001a\u00103\u001a\u00020,2\b\b\u0002\u0010.\u001a\u00020\u00162\b\b\u0002\u00104\u001a\u00020\u0017J\u0006\u00105\u001a\u00020,J\u0006\u00106\u001a\u00020,J\u000e\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020\u000fJ\u0006\u00109\u001a\u00020,J\u0018\u0010:\u001a\u00020,2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>J\u0014\u0010?\u001a\u00020,2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u000f0AR*\u0010\u0007\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R0\u0010\u0013\u001a$\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00140\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0015\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00198BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR-\u0010\u001c\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n0\t0\u001d8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010 \u001a\u0004\u0018\u00010\u000f8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u001d8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001fR\u001f\u0010%\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u001d8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u001fR3\u0010\'\u001a$\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00140\t0\u001d8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001fR)\u0010)\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\n0\t0\u001d8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "Landroidx/lifecycle/ViewModel;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "repo", "Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "(Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;)V", "_autoNavigateLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dojah_inc/dojah_android_sdk/core/Event;", "Lkotlin/Pair;", "Landroid/os/Bundle;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel$PopAction;", "_currentStepLiveData", "Lkotlin/collections/ArrayDeque;", "", "_finalDecisionLiveData", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DecisionResponse;", "_navigationLiveData", "Lkotlin/Triple;", "_popBackStackLiveData", "", "", "authDataFromPref", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "getAuthDataFromPref", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "autoNavigateLiveData", "Landroidx/lifecycle/LiveData;", "getAutoNavigateLiveData", "()Landroidx/lifecycle/LiveData;", "currentPage", "getCurrentPage", "()Ljava/lang/String;", "currentStepLiveData", "getCurrentStepLiveData", "finalDecisionLiveData", "getFinalDecisionLiveData", "navigationLiveData", "getNavigationLiveData", "popBackStackLiveData", "getPopBackStackLiveData", "makeFinalDecision", "", "navigate", "destination", "args", "popAction", "navigateNextStep", "navigateOld", "popBackStack", "inclusive", "popDojahBackStack", "popLastDojahRoute", "pushNextDojahRoute", "currentRoute", "resetDecisionLiveData", "resumeOngoingVerification", "activity", "Lcom/dojah_inc/dojah_android_sdk/ui/main/DojahMainActivity;", "emailEntity", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SimpleResponseEntity;", "updateCurrentSteps", "steps", "", "PopAction", "dojah-module_mobileRelease"})
public final class NavigationViewModel extends androidx.lifecycle.ViewModel {
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    private final com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.collections.ArrayDeque<java.lang.String>> _currentStepLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Pair<android.os.Bundle, com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction>>> _autoNavigateLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Triple<java.lang.String, android.os.Bundle, com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction>>> _navigationLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Pair<java.lang.Integer, java.lang.Boolean>>> _popBackStackLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DecisionResponse>> _finalDecisionLiveData = null;
    
    public NavigationViewModel(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCurrentPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.collections.ArrayDeque<java.lang.String>> getCurrentStepLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Pair<android.os.Bundle, com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction>>> getAutoNavigateLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Triple<java.lang.String, android.os.Bundle, com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction>>> getNavigationLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Event<kotlin.Pair<java.lang.Integer, java.lang.Boolean>>> getPopBackStackLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DecisionResponse>> getFinalDecisionLiveData() {
        return null;
    }
    
    public final void resetDecisionLiveData() {
    }
    
    public final void navigateOld(int destination, @org.jetbrains.annotations.Nullable
    android.os.Bundle args, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction popAction) {
    }
    
    public final void navigate(@org.jetbrains.annotations.NotNull
    java.lang.String destination, @org.jetbrains.annotations.Nullable
    android.os.Bundle args, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction popAction) {
    }
    
    public final void navigateNextStep(@org.jetbrains.annotations.Nullable
    android.os.Bundle args, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction popAction) {
    }
    
    public final void pushNextDojahRoute(@org.jetbrains.annotations.NotNull
    java.lang.String currentRoute) {
    }
    
    public final void popLastDojahRoute() {
    }
    
    public final void popDojahBackStack() {
    }
    
    /**
     * Sends a popBackStack message to the activity
     * @param destination is the id of the destination to popUpTo. Pass -1 if the default
     * [androidx.navigation.findNavController]
     */
    public final void popBackStack(int destination, boolean inclusive) {
    }
    
    public final void makeFinalDecision() {
    }
    
    public final void resumeOngoingVerification(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.DojahMainActivity activity, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponseEntity emailEntity) {
    }
    
    public final void updateCurrentSteps(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> steps) {
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse getAuthDataFromPref() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel$PopAction;", "", "popupTo", "", "inclusive", "", "(IZ)V", "getInclusive", "()Z", "getPopupTo", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "dojah-module_mobileRelease"})
    public static final class PopAction {
        private final int popupTo = 0;
        private final boolean inclusive = false;
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel.PopAction copy(@androidx.annotation.IdRes
        int popupTo, boolean inclusive) {
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
        
        public PopAction(@androidx.annotation.IdRes
        int popupTo, boolean inclusive) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPopupTo() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getInclusive() {
            return false;
        }
    }
}