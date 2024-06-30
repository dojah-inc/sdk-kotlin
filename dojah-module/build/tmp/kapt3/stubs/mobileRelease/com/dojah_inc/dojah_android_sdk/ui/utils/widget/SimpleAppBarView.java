package com.dojah_inc.dojah_android_sdk.ui.utils.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u00a2\u0006\u0002\u0010\rJ\b\u0010(\u001a\u00020)H\u0014J\u000e\u0010*\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020+J\u000e\u0010,\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020+R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u001c@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR(\u0010$\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016R\u000e\u0010\'\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/SimpleAppBarView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defAttrStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleAttr", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "binding", "Lcom/dojah_inc/dojah_android_sdk/databinding/WidgetSimpleAppbarBinding;", "value", "Landroid/graphics/drawable/Drawable;", "leadingDrawable", "getLeadingDrawable", "()Landroid/graphics/drawable/Drawable;", "setLeadingDrawable", "(Landroid/graphics/drawable/Drawable;)V", "leadingResId", "leadingView", "Landroidx/appcompat/widget/AppCompatImageButton;", "getLeadingView", "()Landroidx/appcompat/widget/AppCompatImageButton;", "", "title", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "trailView", "getTrailView", "trailingDrawable", "getTrailingDrawable", "setTrailingDrawable", "trailingResId", "onFinishInflate", "", "setLeadingView", "Landroid/view/View;", "setTrailView", "dojah-module_mobileRelease"})
public final class SimpleAppBarView extends androidx.constraintlayout.widget.ConstraintLayout {
    private final com.dojah_inc.dojah_android_sdk.databinding.WidgetSimpleAppbarBinding binding = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String title = "";
    @org.jetbrains.annotations.Nullable
    private android.graphics.drawable.Drawable leadingDrawable;
    @org.jetbrains.annotations.Nullable
    private android.graphics.drawable.Drawable trailingDrawable;
    @org.jetbrains.annotations.NotNull
    private final androidx.appcompat.widget.AppCompatImageButton leadingView = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.appcompat.widget.AppCompatImageButton trailView = null;
    private int leadingResId = -1;
    private int trailingResId = -1;
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.graphics.drawable.Drawable getLeadingDrawable() {
        return null;
    }
    
    public final void setLeadingDrawable(@org.jetbrains.annotations.Nullable
    android.graphics.drawable.Drawable value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.graphics.drawable.Drawable getTrailingDrawable() {
        return null;
    }
    
    public final void setTrailingDrawable(@org.jetbrains.annotations.Nullable
    android.graphics.drawable.Drawable value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.appcompat.widget.AppCompatImageButton getLeadingView() {
        return null;
    }
    
    public final void setLeadingView(@org.jetbrains.annotations.NotNull
    android.view.View value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.appcompat.widget.AppCompatImageButton getTrailView() {
        return null;
    }
    
    public final void setTrailView(@org.jetbrains.annotations.NotNull
    android.view.View value) {
    }
    
    public SimpleAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public SimpleAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet) {
        super(null);
    }
    
    public SimpleAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defAttrStyle) {
        super(null);
    }
    
    public SimpleAppBarView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    @java.lang.Override
    protected void onFinishInflate() {
    }
}