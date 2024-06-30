package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001a\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020&2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010-\u001a\u00020\"H\u0002J\u0016\u0010.\u001a\u00020\"2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\"00H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u0012\u001a\u0004\b\u001e\u0010\u001f\u00a8\u00061"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/fragment/datacollection/UploadBackDocFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentUploadDocBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/FragmentUploadDocBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "fileContract", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "govViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "getGovViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "govViewModel$delegate", "Lkotlin/Lazy;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "permissionContract", "readImagePermissionString", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "showFilePicker", "showPermissionError", "onAllowClicked", "Lkotlin/Function0;", "dojah-module_mobileRelease"})
public final class UploadBackDocFragment extends com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment {
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy govViewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String[]> fileContract;
    private java.lang.String readImagePermissionString;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionContract;
    private final okhttp3.logging.HttpLoggingInterceptor.Logger logger = null;
    
    public UploadBackDocFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.FragmentUploadDocBinding getBinding() {
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
    
    private final void showPermissionError(kotlin.jvm.functions.Function0<kotlin.Unit> onAllowClicked) {
    }
    
    private final void showFilePicker() {
    }
}