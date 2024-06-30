package com.dojah_inc.dojah_android_sdk.ui.utils.widget.otp_view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020\"2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0003J\u000e\u0010&\u001a\u00020\"2\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/otp_view/ItemView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "barActiveColor", "barErrorColor", "barInactiveColor", "barSuccessColor", "boxBackgroundBorderColorActive", "getBoxBackgroundBorderColorActive", "()Ljava/lang/Integer;", "setBoxBackgroundBorderColorActive", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "boxBackgroundColorActive", "boxBackgroundColorError", "boxBackgroundColorInactive", "boxBackgroundColorSuccess", "defaultOTPDrawable", "hideOTP", "", "hideOTPDrawable", "textView", "Landroid/widget/TextView;", "view", "Landroid/view/View;", "generateViews", "", "styles", "Landroid/content/res/TypedArray;", "init", "setText", "value", "", "setViewState", "state", "Companion", "dojah-module_mobileReleaseNoMinify"})
public final class ItemView extends android.widget.FrameLayout {
    private android.widget.TextView textView;
    private android.view.View view;
    private int barActiveColor = 0;
    private int barInactiveColor = 0;
    private int barErrorColor = 0;
    private int barSuccessColor = 0;
    private int boxBackgroundColorActive = 0;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer boxBackgroundBorderColorActive;
    private int boxBackgroundColorInactive = 0;
    private int boxBackgroundColorSuccess = 0;
    private int boxBackgroundColorError = 0;
    private int hideOTPDrawable = 0;
    private int defaultOTPDrawable = 0;
    private boolean hideOTP = false;
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.utils.widget.otp_view.ItemView.Companion Companion = null;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int ERROR = -1;
    public static final int SUCCESS = 2;
    private static final float DEFAULT_BAR_HEIGHT = 2.0F;
    private static final float DEFAULT_OTP_TEXT_SIZE = 24.0F;
    private static final int DEFAULT_BAR_MARGIN = 2;
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getBoxBackgroundBorderColorActive() {
        return null;
    }
    
    public final void setBoxBackgroundBorderColorActive(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    public ItemView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public ItemView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public ItemView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @android.annotation.SuppressLint(value = {"CustomViewStyleable"})
    private final void init(android.util.AttributeSet attrs) {
    }
    
    private final void generateViews(android.content.res.TypedArray styles) {
    }
    
    public final void setText(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    public final void setViewState(int state) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/otp_view/ItemView$Companion;", "", "()V", "ACTIVE", "", "DEFAULT_BAR_HEIGHT", "", "DEFAULT_BAR_MARGIN", "DEFAULT_OTP_TEXT_SIZE", "ERROR", "INACTIVE", "SUCCESS", "dojah-module_mobileReleaseNoMinify"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}