package com.dojah_inc.dojah_android_sdk.ui.utils.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\n"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextInputEditText;", "Lcom/google/android/material/textfield/TextInputEditText;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "setPasswordTransformationMethod", "", "CrossTransformationMethod", "dojah-module_mobileRelease"})
public final class TextInputEditText extends com.google.android.material.textfield.TextInputEditText {
    
    @kotlin.jvm.JvmOverloads
    public TextInputEditText(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public TextInputEditText(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public final void setPasswordTransformationMethod() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003 !\"B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J*\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J6\u0010\u0014\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J(\u0010\u001b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextInputEditText$CrossTransformationMethod;", "Landroid/text/method/PasswordTransformationMethod;", "()V", "DOT", "", "sInstance", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getTransformation", "source", "view", "Landroid/view/View;", "onFocusChanged", "sourceText", "focused", "", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "onTextChanged", "before", "removeVisibleSpans", "sp", "Landroid/text/Spannable;", "PasswordCharSequence", "ViewReference", "Visible", "dojah-module_mobileRelease"})
    public static final class CrossTransformationMethod extends android.text.method.PasswordTransformationMethod {
        @org.jetbrains.annotations.NotNull
        public static final com.dojah_inc.dojah_android_sdk.ui.utils.widget.TextInputEditText.CrossTransformationMethod INSTANCE = null;
        private static android.text.method.PasswordTransformationMethod sInstance;
        private static final char DOT = 'x';
        
        private CrossTransformationMethod() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public java.lang.CharSequence getTransformation(@org.jetbrains.annotations.NotNull
        java.lang.CharSequence source, @org.jetbrains.annotations.Nullable
        android.view.View view) {
            return null;
        }
        
        @java.lang.Override
        public void beforeTextChanged(@org.jetbrains.annotations.Nullable
        java.lang.CharSequence s, int start, int count, int after) {
        }
        
        @java.lang.Override
        public void onTextChanged(@org.jetbrains.annotations.NotNull
        java.lang.CharSequence s, int start, int before, int count) {
        }
        
        @java.lang.Override
        public void afterTextChanged(@org.jetbrains.annotations.Nullable
        android.text.Editable s) {
        }
        
        @java.lang.Override
        public void onFocusChanged(@org.jetbrains.annotations.Nullable
        android.view.View view, @org.jetbrains.annotations.Nullable
        java.lang.CharSequence sourceText, boolean focused, int direction, @org.jetbrains.annotations.Nullable
        android.graphics.Rect previouslyFocusedRect) {
        }
        
        private final void removeVisibleSpans(android.text.Spannable sp) {
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0096\u0002J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0018\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextInputEditText$CrossTransformationMethod$PasswordCharSequence;", "", "Landroid/text/GetChars;", "mSource", "(Ljava/lang/CharSequence;)V", "length", "", "getLength", "()I", "get", "", "i", "getChars", "", "start", "end", "dest", "", "off", "subSequence", "toString", "", "dojah-module_mobileRelease"})
        static final class PasswordCharSequence implements java.lang.CharSequence, android.text.GetChars {
            private final java.lang.CharSequence mSource = null;
            
            public PasswordCharSequence(@org.jetbrains.annotations.NotNull
            java.lang.CharSequence mSource) {
                super();
            }
            
            @java.lang.Override
            public int getLength() {
                return 0;
            }
            
            @java.lang.Override
            public final int length() {
                return 0;
            }
            
            @java.lang.Override
            public final char charAt(int i) {
                return '\u0000';
            }
            
            @java.lang.Override
            public char get(int i) {
                return '\u0000';
            }
            
            @org.jetbrains.annotations.NotNull
            @java.lang.Override
            public java.lang.CharSequence subSequence(int start, int end) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            @java.lang.Override
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override
            public void getChars(int start, int end, @org.jetbrains.annotations.NotNull
            char[] dest, int off) {
            }
        }
        
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextInputEditText$CrossTransformationMethod$Visible;", "Landroid/os/Handler;", "Landroid/text/style/UpdateLayout;", "Ljava/lang/Runnable;", "mText", "Landroid/text/Spannable;", "mTransformer", "Landroid/text/method/PasswordTransformationMethod;", "(Landroid/text/Spannable;Landroid/text/method/PasswordTransformationMethod;)V", "getMTransformer", "()Landroid/text/method/PasswordTransformationMethod;", "run", "", "dojah-module_mobileRelease"})
        static final class Visible extends android.os.Handler implements android.text.style.UpdateLayout, java.lang.Runnable {
            private final android.text.Spannable mText = null;
            @org.jetbrains.annotations.NotNull
            private final android.text.method.PasswordTransformationMethod mTransformer = null;
            
            public Visible(@org.jetbrains.annotations.NotNull
            android.text.Spannable mText, @org.jetbrains.annotations.NotNull
            android.text.method.PasswordTransformationMethod mTransformer) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull
            public final android.text.method.PasswordTransformationMethod getMTransformer() {
                return null;
            }
            
            @java.lang.Override
            public void run() {
            }
        }
        
        /**
         * Used to stash a reference back to the View in the Editable so we
         * can use it to check the settings.
         */
        @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextInputEditText$CrossTransformationMethod$ViewReference;", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "Landroid/text/NoCopySpan;", "v", "(Landroid/view/View;)V", "dojah-module_mobileRelease"})
        static final class ViewReference extends java.lang.ref.WeakReference<android.view.View> implements android.text.NoCopySpan {
            
            public ViewReference(@org.jetbrains.annotations.Nullable
            android.view.View v) {
                super(null);
            }
        }
    }
}