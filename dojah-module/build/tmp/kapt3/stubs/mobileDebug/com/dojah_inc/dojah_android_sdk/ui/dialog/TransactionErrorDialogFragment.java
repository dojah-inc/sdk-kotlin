package com.dojah_inc.dojah_android_sdk.ui.dialog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0016J\u001a\u0010\'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015\u00a8\u0006*"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/TransactionErrorDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/DialogTransactionErrorBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/DialogTransactionErrorBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "navViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "getNavViewModel", "()Lcom/dojah_inc/dojah_android_sdk/ui/base/NavigationViewModel;", "navViewModel$delegate", "Lkotlin/Lazy;", "onDismiss", "Lkotlin/Function0;", "", "getOnDismiss", "()Lkotlin/jvm/functions/Function0;", "setOnDismiss", "(Lkotlin/jvm/functions/Function0;)V", "onNegativeButtonClicked", "getOnNegativeButtonClicked", "setOnNegativeButtonClicked", "onPositiveButtonClicked", "getOnPositiveButtonClicked", "setOnPositiveButtonClicked", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "dialog", "Landroid/content/DialogInterface;", "onViewCreated", "view", "Companion", "dojah-module_mobileDebug"})
public final class TransactionErrorDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.dialog.TransactionErrorDialogFragment.Companion Companion = null;
    private static final java.lang.String EXTRA_TITLE = "com.africave.kippapay.TransactionError.TITLE";
    private static final java.lang.String EXTRA_TEXT = "com.africave.kippapay.TransactionError.TEXT";
    private static final java.lang.String EXTRA_STATUS = "com.africave.kippapay.TransactionError.STATUS";
    private static final java.lang.String STATUS_09 = "09";
    private static final java.lang.String STATUS_10 = "10";
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy navViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onPositiveButtonClicked;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onNegativeButtonClicked;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss;
    
    public TransactionErrorDialogFragment() {
        super();
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.DialogTransactionErrorBinding getBinding() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel getNavViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnPositiveButtonClicked() {
        return null;
    }
    
    public final void setOnPositiveButtonClicked(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnNegativeButtonClicked() {
        return null;
    }
    
    public final void setOnNegativeButtonClicked(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnDismiss() {
        return null;
    }
    
    public final void setOnDismiss(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
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
    
    @java.lang.Override
    public void onDismiss(@org.jetbrains.annotations.NotNull
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/TransactionErrorDialogFragment$Companion;", "", "()V", "EXTRA_STATUS", "", "EXTRA_TEXT", "EXTRA_TITLE", "STATUS_09", "STATUS_10", "getInstance", "Lcom/dojah_inc/dojah_android_sdk/ui/dialog/TransactionErrorDialogFragment;", "title", "text", "status", "dojah-module_mobileDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.ui.dialog.TransactionErrorDialogFragment getInstance(@org.jetbrains.annotations.NotNull
        java.lang.String title, @org.jetbrains.annotations.NotNull
        java.lang.String text, @org.jetbrains.annotations.Nullable
        java.lang.String status) {
            return null;
        }
    }
}