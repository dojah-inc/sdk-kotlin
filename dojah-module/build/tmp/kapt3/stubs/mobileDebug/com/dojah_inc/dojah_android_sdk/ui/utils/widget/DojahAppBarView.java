package com.dojah_inc.dojah_android_sdk.ui.utils.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u00a2\u0006\u0002\u0010\rJ\b\u0010&\u001a\u00020\'H\u0014R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R(\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020 @FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u00a8\u0006("}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/DojahAppBarView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defAttrStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleAttr", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "backView", "Landroid/view/View;", "getBackView", "()Landroid/view/View;", "setBackView", "(Landroid/view/View;)V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/WidgetDojahAppbarBinding;", "closeView", "getCloseView", "setCloseView", "value", "", "logoUrl", "getLogoUrl", "()Ljava/lang/String;", "setLogoUrl", "(Ljava/lang/String;)V", "", "showBackButton", "getShowBackButton", "()Z", "setShowBackButton", "(Z)V", "onFinishInflate", "", "dojah-module_mobileDebug"})
public final class DojahAppBarView extends androidx.constraintlayout.widget.ConstraintLayout {
    private final com.dojah_inc.dojah_android_sdk.databinding.WidgetDojahAppbarBinding binding = null;
    private boolean showBackButton = true;
    @org.jetbrains.annotations.NotNull
    private android.view.View backView;
    @org.jetbrains.annotations.NotNull
    private android.view.View closeView;
    @org.jetbrains.annotations.Nullable
    private java.lang.String logoUrl;
    
    public final boolean getShowBackButton() {
        return false;
    }
    
    public final void setShowBackButton(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.view.View getBackView() {
        return null;
    }
    
    public final void setBackView(@org.jetbrains.annotations.NotNull
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.view.View getCloseView() {
        return null;
    }
    
    public final void setCloseView(@org.jetbrains.annotations.NotNull
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLogoUrl() {
        return null;
    }
    
    public final void setLogoUrl(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    public DojahAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public DojahAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet) {
        super(null);
    }
    
    public DojahAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defAttrStyle) {
        super(null);
    }
    
    public DojahAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    @java.lang.Override
    protected void onFinishInflate() {
    }
}