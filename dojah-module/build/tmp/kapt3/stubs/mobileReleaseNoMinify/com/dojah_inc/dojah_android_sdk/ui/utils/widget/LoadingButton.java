package com.dojah_inc.dojah_android_sdk.ui.utils.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u00a2\u0006\u0002\u0010\rJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0011H\u0016J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u0011H\u0016J\u0012\u0010#\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001fH\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0018@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006\'"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/LoadingButton;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defAttrStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleAttr", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/WidgetButtonWithProgressIndicatorBinding;", "value", "", "isButtonEnabled", "()Z", "setButtonEnabled", "(Z)V", "isLoading", "setLoading", "", "text", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "hideProgressDrawable", "", "isEnabled", "setEnabled", "enabled", "setOnClickListener", "l", "Landroid/view/View$OnClickListener;", "showProgressDrawable", "dojah-module_mobileReleaseNoMinify"})
public final class LoadingButton extends android.widget.FrameLayout {
    private final com.dojah_inc.dojah_android_sdk.databinding.WidgetButtonWithProgressIndicatorBinding binding = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String text = "";
    private boolean isButtonEnabled = false;
    private boolean isLoading = false;
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getText() {
        return null;
    }
    
    public final void setText(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    public final boolean isButtonEnabled() {
        return false;
    }
    
    public final void setButtonEnabled(boolean value) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final void setLoading(boolean value) {
    }
    
    public LoadingButton(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public LoadingButton(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet) {
        super(null);
    }
    
    public LoadingButton(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defAttrStyle) {
        super(null);
    }
    
    public LoadingButton(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    @java.lang.Override
    public void setOnClickListener(@org.jetbrains.annotations.Nullable
    android.view.View.OnClickListener l) {
    }
    
    @java.lang.Override
    public boolean isEnabled() {
        return false;
    }
    
    @java.lang.Override
    public void setEnabled(boolean enabled) {
    }
    
    private final void showProgressDrawable() {
    }
    
    private final void hideProgressDrawable() {
    }
}