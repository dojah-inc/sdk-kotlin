package com.dojah_inc.dojah_android_sdk.ui.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\u0016JZ\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!2\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!J&\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00192\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010(J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u001dJ\u001a\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001d2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-J\b\u0010.\u001a\u00020\u0016H\u0016J\u0006\u0010/\u001a\u00020\u0016J\u0010\u00100\u001a\u00020\u00162\b\b\u0002\u00101\u001a\u00020\u001dJ\u0010\u00102\u001a\u00020\u00162\b\b\u0002\u00101\u001a\u00020\u001dR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013\u00a8\u00063"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "Landroidx/fragment/app/Fragment;", "()V", "layoutRes", "", "(I)V", "errorDialog", "Landroidx/appcompat/app/AlertDialog;", "genericDialog", "loadingDialog", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "viewModel$delegate", "dismissLoading", "", "handleError", "error", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "useToast", "", "errorTitle", "", "errorTitleField", "errorField", "onDismiss", "Lkotlin/Function0;", "action", "navigateToErrorPage", "errorObject", "page", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;", "govDataViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "errorMessage", "observeEvents", "eventValue", "argument", "Landroid/os/Bundle;", "onDestroy", "showLoading", "showLongToast", "text", "showShortToast", "dojah-module_mobileReleaseNoMinify"})
public class ErrorFragment extends androidx.fragment.app.Fragment {
    private androidx.appcompat.app.AlertDialog errorDialog;
    private androidx.appcompat.app.AlertDialog loadingDialog;
    private androidx.appcompat.app.AlertDialog genericDialog;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    
    private final com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel getViewModel() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel getNavViewModel() {
        return null;
    }
    
    public ErrorFragment() {
        super();
    }
    
    public ErrorFragment(@androidx.annotation.LayoutRes
    int layoutRes) {
        super();
    }
    
    public final void observeEvents(@org.jetbrains.annotations.NotNull
    java.lang.String eventValue, @org.jetbrains.annotations.Nullable
    android.os.Bundle argument) {
    }
    
    public final void navigateToErrorPage(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.Result.Error errorObject, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel govDataViewModel) {
    }
    
    public final void navigateToErrorPage(@org.jetbrains.annotations.NotNull
    java.lang.String errorMessage) {
    }
    
    /**
     * Used to handle all possible [Result.Error]
     * @param useToast to determine if a toast is displayed or a dialog
     * @param errorTitle the title to use to display the dialog, only used for a dialog
     * @param errorTitleField a json key value which will be used to search through the
     * class passed into [Result.Error.ApiError]. This will determine the title of the dialog
     * It takes precedence over @param errorTitle.
     * @param errorField a json key value which will be used to determine the error message displayed
     */
    public final void handleError(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.Result.Error error, boolean useToast, @org.jetbrains.annotations.NotNull
    java.lang.String errorTitle, @org.jetbrains.annotations.NotNull
    java.lang.String errorTitleField, @org.jetbrains.annotations.NotNull
    java.lang.String errorField, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> action) {
    }
    
    public final void showShortToast(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    public final void showLongToast(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    public final void showLoading() {
    }
    
    public final void dismissLoading() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}