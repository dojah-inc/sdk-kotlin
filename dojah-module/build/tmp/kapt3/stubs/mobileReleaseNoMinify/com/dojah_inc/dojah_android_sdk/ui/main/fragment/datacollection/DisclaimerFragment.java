package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection;

import java.lang.System;

@android.annotation.SuppressLint(value = {"UnsafeRepeatOnLifecycleDetector"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0016\u0010\u001f\u001a\u00020\u00182\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180!H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/fragment/datacollection/DisclaimerFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/DialogDisclaimerBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/DialogDisclaimerBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "cameraContract", "Landroidx/activity/result/ActivityResultLauncher;", "", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "navigateNextScreen", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "showPermissionError", "onAllowClicked", "Lkotlin/Function0;", "dojah-module_mobileReleaseNoMinify"})
public final class DisclaimerFragment extends com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment {
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> cameraContract;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    
    public DisclaimerFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.DialogDisclaimerBinding getBinding() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel getViewModel() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel getNavViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void navigateNextScreen() {
    }
    
    private final void showPermissionError(kotlin.jvm.functions.Function0<kotlin.Unit> onAllowClicked) {
    }
}