package com.dojah_inc.dojah_android_sdk.ui.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005Jz\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u001a\u0010\u0012\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u00162D\u0010\u0018\u001a@\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0014\u00a2\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000f0\u0019H\u0004J\b\u0010\u001e\u001a\u00020\u000fH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/ErrorFragment;", "()V", "layoutRes", "", "(I)V", "popupWindow", "Landroid/widget/PopupWindow;", "getPopupWindow", "()Landroid/widget/PopupWindow;", "setPopupWindow", "(Landroid/widget/PopupWindow;)V", "spinnerBinding", "Lcom/dojah_inc/dojah_android_sdk/databinding/PopupSpinnerBinding;", "displaySpinnerDropdown", "", "anchor", "Landroid/view/View;", "data", "", "Lkotlin/Pair;", "", "", "hideProgress", "onItemClick", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "position", "selected", "onDestroy", "Companion", "PopupAdapter", "dojah-module_mobileRelease"})
public class RadioSpinnerFragment extends com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.base.RadioSpinnerFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<kotlin.Pair<java.lang.String, java.lang.Boolean>> DIFF_UTIL = null;
    @org.jetbrains.annotations.Nullable
    private android.widget.PopupWindow popupWindow;
    private com.dojah_inc.dojah_android_sdk.databinding.PopupSpinnerBinding spinnerBinding;
    
    public RadioSpinnerFragment() {
        super();
    }
    
    public RadioSpinnerFragment(@androidx.annotation.LayoutRes
    int layoutRes) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    protected final android.widget.PopupWindow getPopupWindow() {
        return null;
    }
    
    protected final void setPopupWindow(@org.jetbrains.annotations.Nullable
    android.widget.PopupWindow p0) {
    }
    
    /**
     * Displays a dropdown window anchored to the provided anchor
     * @param anchor The view the dropdown should be anchored to
     * @param data The list of data to display in the dropdown, the boolean value is used to determine if the item is selected or not
     * @param hideProgress Used to determine if the progress indicator should be displayed or not
     *       true meaning it should be hidden and false the opposite
     *       it works together with the data to determine the progress indicator visibility
     * @param onItemClick The callback to be used when an item is clicked, it passes the index of the clicked item
     */
    protected final void displaySpinnerDropdown(@org.jetbrains.annotations.NotNull
    android.view.View anchor, @org.jetbrains.annotations.Nullable
    java.util.List<kotlin.Pair<java.lang.String, java.lang.Boolean>> data, boolean hideProgress, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.Pair<java.lang.String, java.lang.Boolean>, kotlin.Unit> onItemClick) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\f\u0012\n0\u0005R\u00060\u0000R\u00020\u00060\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u000b2\u000e\u0010\u0011\u001a\n0\u0005R\u00060\u0000R\u00020\u00062\u0006\u0010\u0012\u001a\u00020\nH\u0016J \u0010\u0013\u001a\n0\u0005R\u00060\u0000R\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016R(\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0018"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment$PopupAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lkotlin/Pair;", "", "", "Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment$PopupAdapter$ViewHolder;", "Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment;", "(Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment;)V", "onClick", "Lkotlin/Function1;", "", "", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "setOnClick", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "dojah-module_mobileRelease"})
    public final class PopupAdapter extends androidx.recyclerview.widget.ListAdapter<kotlin.Pair<? extends java.lang.String, ? extends java.lang.Boolean>, com.dojah_inc.dojah_android_sdk.ui.base.RadioSpinnerFragment.PopupAdapter.ViewHolder> {
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
        public com.dojah_inc.dojah_android_sdk.ui.base.RadioSpinnerFragment.PopupAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.dojah_inc.dojah_android_sdk.ui.base.RadioSpinnerFragment.PopupAdapter.ViewHolder holder, int position) {
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment$PopupAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment$PopupAdapter;Landroid/view/View;)V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/ItemSpinnerBinding;", "getBinding", "()Lcom/dojah_inc/dojah_android_sdk/databinding/ItemSpinnerBinding;", "getView", "()Landroid/view/View;", "dojah-module_mobileRelease"})
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/base/RadioSpinnerFragment$Companion;", "", "()V", "DIFF_UTIL", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkotlin/Pair;", "", "", "getDIFF_UTIL", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "dojah-module_mobileRelease"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<kotlin.Pair<java.lang.String, java.lang.Boolean>> getDIFF_UTIL() {
            return null;
        }
    }
}