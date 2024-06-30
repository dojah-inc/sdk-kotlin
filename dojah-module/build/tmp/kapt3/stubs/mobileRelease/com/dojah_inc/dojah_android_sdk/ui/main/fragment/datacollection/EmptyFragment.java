package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection;

import java.lang.System;

@android.annotation.SuppressLint(value = {"UnsafeRepeatOnLifecycleDetector"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\"\u0010!\u001a\u00020\u001e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001e0%H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u000e\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006&"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/fragment/datacollection/EmptyFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentEmptyBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentEmptyBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "govViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "getGovViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "govViewModel$delegate", "Lkotlin/Lazy;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "getLogger", "()Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "sendOtp", "phoneNumber", "", "onOtpSent", "Lkotlin/Function0;", "dojah-module_mobileRelease"})
public final class EmptyFragment extends com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment {
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy govViewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final okhttp3.logging.HttpLoggingInterceptor.Logger logger = null;
    
    public EmptyFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.FragmentEmptyBinding getBinding() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel getViewModel() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel getGovViewModel() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel getNavViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final okhttp3.logging.HttpLoggingInterceptor.Logger getLogger() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void sendOtp(java.lang.String phoneNumber, kotlin.jvm.functions.Function0<kotlin.Unit> onOtpSent) {
    }
}