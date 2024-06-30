package com.dojah_inc.dojah_android_sdk.ui.dialog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 /2\u00020\u0001:\u0003/01B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J&\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\'2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\f\u0010.\u001a\u00020\u0012*\u00020\u0004H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR7\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000b\u00a8\u00062"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment;", "()V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/DojahCalenderFragmentBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/DojahCalenderFragmentBinding;", "binding$delegate", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/delegates/FragmentViewBindingDelegate;", "lengthOfSelectedMonth", "", "Ljava/lang/Integer;", "onAllow", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "date", "", "getOnAllow", "()Lkotlin/jvm/functions/Function1;", "setOnAllow", "(Lkotlin/jvm/functions/Function1;)V", "onExitClick", "Lkotlin/Function0;", "getOnExitClick", "()Lkotlin/jvm/functions/Function0;", "setOnExitClick", "(Lkotlin/jvm/functions/Function0;)V", "selectedDate", "Ljava/time/LocalDate;", "selectedMonth", "Ljava/time/Month;", "selectedYear", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "updateCalenderLabel", "Companion", "DayViewContainer", "MonthViewContainer", "dojah-module_mobileDebug"})
public final class CalendarDialogFragment extends com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.dialog.CalendarDialogFragment.Companion Companion = null;
    private static com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel mViewModel;
    private final com.dojah_inc.dojah_android_sdk.ui.utils.delegates.FragmentViewBindingDelegate binding$delegate = null;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAllow;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onExitClick;
    private java.time.Month selectedMonth;
    private java.lang.Integer selectedYear;
    private java.time.LocalDate selectedDate;
    private java.lang.Integer lengthOfSelectedMonth;
    
    public CalendarDialogFragment() {
        super(0);
    }
    
    private final com.dojah_inc.dojah_android_sdk.databinding.DojahCalenderFragmentBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnAllow() {
        return null;
    }
    
    public final void setOnAllow(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnExitClick() {
        return null;
    }
    
    public final void setOnExitClick(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void updateCalenderLabel(com.dojah_inc.dojah_android_sdk.databinding.DojahCalenderFragmentBinding $this$updateCalenderLabel) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment$DayViewContainer;", "Lcom/kizitonwose/calendar/view/ViewContainer;", "view", "Landroid/view/View;", "(Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment;Landroid/view/View;)V", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "dojah-module_mobileDebug"})
    public final class DayViewContainer extends com.kizitonwose.calendar.view.ViewContainer {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView textView = null;
        
        public DayViewContainer(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTextView() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment$MonthViewContainer;", "Lcom/kizitonwose/calendar/view/ViewContainer;", "view", "Landroid/view/View;", "(Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment;Landroid/view/View;)V", "titlesContainer", "Landroid/view/ViewGroup;", "getTitlesContainer", "()Landroid/view/ViewGroup;", "dojah-module_mobileDebug"})
    public final class MonthViewContainer extends com.kizitonwose.calendar.view.ViewContainer {
        @org.jetbrains.annotations.NotNull
        private final android.view.ViewGroup titlesContainer = null;
        
        public MonthViewContainer(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.view.ViewGroup getTitlesContainer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment$Companion;", "", "()V", "mViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "getInstance", "Lcom/dojah_inc/dojah_android_sdk/ui/dialog/CalendarDialogFragment;", "viewModel", "dojah-module_mobileDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.dojah_inc.dojah_android_sdk.ui.dialog.CalendarDialogFragment getInstance(@org.jetbrains.annotations.NotNull
        com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel) {
            return null;
        }
    }
}