package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010&\u001a\u00020\u001c2\u0006\u0010\'\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010(\u001a\u00020\u001cH\u0002J \u0010)\u001a\u00020\u001c*\u00020\u00042\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u001a\u0010.\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u0001000/*\u00020\u0004H\u0002J<\u00101\u001a\u00020\u001c*\u00020\u00042\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u00102\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u0001002\b\b\u0002\u00104\u001a\u000205H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019\u00a8\u00066"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/fragment/datacollection/PreviewSelfieFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentPreviewSelfieBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentPreviewSelfieBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "govViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "getGovViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "govViewModel$delegate", "Lkotlin/Lazy;", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "verificationImage", "", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "observeLiveData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "performAnalysis", "displayCapturedView", "verificationType", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/VerificationType;", "uri", "Landroid/net/Uri;", "getBlurView", "Lkotlin/Pair;", "Landroid/graphics/drawable/Drawable;", "showLoadingProgress", "rootView", "windowBackground", "loading", "", "dojah-module_mobileReleaseNoMinify"})
public final class PreviewSelfieFragment extends com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment {
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy govViewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    private java.lang.String verificationImage;
    
    public PreviewSelfieFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.FragmentPreviewSelfieBinding getBinding() {
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
    
    private final void observeLiveData() {
    }
    
    private final void performAnalysis() {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showLoadingProgress(com.dojah_inc.dojah_android_sdk.databinding.FragmentPreviewSelfieBinding $this$showLoadingProgress, com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType verificationType, android.net.Uri uri, android.view.ViewGroup rootView, android.graphics.drawable.Drawable windowBackground, boolean loading) {
    }
    
    private final void displayCapturedView(com.dojah_inc.dojah_android_sdk.databinding.FragmentPreviewSelfieBinding $this$displayCapturedView, com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType verificationType, android.net.Uri uri) {
    }
    
    private final kotlin.Pair<android.view.ViewGroup, android.graphics.drawable.Drawable> getBlurView(com.dojah_inc.dojah_android_sdk.databinding.FragmentPreviewSelfieBinding $this$getBlurView) {
        return null;
    }
}