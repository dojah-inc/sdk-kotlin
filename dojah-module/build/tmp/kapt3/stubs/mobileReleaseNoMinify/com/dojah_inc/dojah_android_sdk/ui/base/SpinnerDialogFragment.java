package com.dojah_inc.dojah_android_sdk.ui.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J<\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u0015H\u0004J\b\u0010\u0016\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "layoutRes", "", "(I)V", "getLayoutRes", "()I", "popupWindow", "Landroid/widget/PopupWindow;", "spinnerBinding", "Lcom/dojah_inc/dojah_android_sdk/databinding/PopupSpinnerBinding;", "displaySpinnerDropdown", "", "anchor", "Landroid/view/View;", "data", "", "", "hideProgress", "", "onItemClick", "Lkotlin/Function1;", "onDestroy", "Companion", "PopupAdapter", "dojah-module_mobileReleaseNoMinify"})
public class SpinnerDialogFragment extends androidx.fragment.app.DialogFragment {
    private final int layoutRes = 0;
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<java.lang.String> DIFF_UTIL = null;
    private android.widget.PopupWindow popupWindow;
    private com.dojah_inc.dojah_android_sdk.databinding.PopupSpinnerBinding spinnerBinding;
    
    public SpinnerDialogFragment(@androidx.annotation.LayoutRes
    int layoutRes) {
        super();
    }
    
    public final int getLayoutRes() {
        return 0;
    }
    
    /**
     * Displays a dropdown window anchored to the provided anchor
     * @param anchor The view the dropdown should be anchored to
     * @param data The list of data to display in the dropdown
     * @param hideProgress Used to determine if the progress indicator should be displayed or not
     *       true meaning it should be hidden and false the opposite
     *       it works together with the data to determine the progress indicator visibility
     * @param onItemClick The callback to be used when an item is clicked, it passes the index of the clicked item
     */
    protected final void displaySpinnerDropdown(@org.jetbrains.annotations.NotNull
    android.view.View anchor, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> data, boolean hideProgress, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onItemClick) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n0\u0003R\u00060\u0000R\u00020\u00040\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0005J \u0010\u000e\u001a\u00020\t2\u000e\u0010\u000f\u001a\n0\u0003R\u00060\u0000R\u00020\u00042\u0006\u0010\u0010\u001a\u00020\bH\u0016J \u0010\u0011\u001a\n0\u0003R\u00060\u0000R\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0016R(\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment$PopupAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "", "Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment$PopupAdapter$ViewHolder;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment;", "(Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment;)V", "onClick", "Lkotlin/Function1;", "", "", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "setOnClick", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "dojah-module_mobileReleaseNoMinify"})
    public final class PopupAdapter extends androidx.recyclerview.widget.ListAdapter<java.lang.String, com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment.PopupAdapter.ViewHolder> {
        @org.jetbrains.annotations.Nullable
        private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClick;
        
        public PopupAdapter() {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnClick() {
            return null;
        }
        
        public final void setOnClick(@org.jetbrains.annotations.Nullable
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment.PopupAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment.PopupAdapter.ViewHolder holder, int position) {
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment$PopupAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment$PopupAdapter;Landroid/view/View;)V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/ItemSpinnerBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/ItemSpinnerBinding;", "getView", "()Landroid/view/View;", "dojah-module_mobileReleaseNoMinify"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull
            private final android.view.View view = null;
            @org.jetbrains.annotations.NotNull
            private final com.dojah_inc.dojah_android_sdk.databinding.ItemSpinnerBinding binding = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull
            android.view.View view) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull
            public final android.view.View getView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.dojah_inc.dojah_android_sdk.databinding.ItemSpinnerBinding getBinding() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/SpinnerDialogFragment$Companion;", "", "()V", "DIFF_UTIL", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "", "getDIFF_UTIL", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "dojah-module_mobileReleaseNoMinify"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<java.lang.String> getDIFF_UTIL() {
            return null;
        }
    }
}