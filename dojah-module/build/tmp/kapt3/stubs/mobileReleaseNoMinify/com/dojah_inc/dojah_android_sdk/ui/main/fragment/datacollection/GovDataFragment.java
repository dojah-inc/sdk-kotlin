package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection;

import java.lang.System;

@android.annotation.SuppressLint(value = {"UnsafeRepeatOnLifecycleDetector"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001a\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010\'\u001a\u00020 *\u00020\u00042\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH\u0002J\u0016\u0010(\u001a\u00020 *\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010*H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006+"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/fragment/datacollection/GovDataFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentGovDataBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentGovDataBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "govViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "getGovViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "govViewModel$delegate", "Lkotlin/Lazy;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "getFilteredMethods", "", "", "verificationMethods", "hideError", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "prefillVerificationMethod", "updateUIwithSelectedGovId", "govId", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "dojah-module_mobileReleaseNoMinify"})
public final class GovDataFragment extends com.dojah_inc.dojah_android_sdk.ui.base.SpinnerFragment {
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy govViewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    private final okhttp3.logging.HttpLoggingInterceptor.Logger logger = null;
    
    public GovDataFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.FragmentGovDataBinding getBinding() {
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
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void hideError() {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void prefillVerificationMethod(com.dojah_inc.dojah_android_sdk.databinding.FragmentGovDataBinding $this$prefillVerificationMethod, java.util.List<java.lang.String> verificationMethods) {
    }
    
    private final java.util.List<java.lang.String> getFilteredMethods(java.util.List<java.lang.String> verificationMethods) {
        return null;
    }
    
    private final void updateUIwithSelectedGovId(com.dojah_inc.dojah_android_sdk.databinding.FragmentGovDataBinding $this$updateUIwithSelectedGovId, com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr govId) {
    }
}