package com.dojah_inc.dojah_android_sdk.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/UserPicTarget;", "Lcom/bumptech/glide/request/target/ImageViewTarget;", "Landroid/graphics/drawable/Drawable;", "imageView", "Landroid/widget/ImageView;", "tint", "", "errorIconSize", "(Landroid/widget/ImageView;II)V", "onLoadFailed", "", "errorDrawable", "onLoadStarted", "placeholder", "setResource", "resource", "dojah-module_mobileRelease"})
public final class UserPicTarget extends com.bumptech.glide.request.target.ImageViewTarget<android.graphics.drawable.Drawable> {
    private final int tint = 0;
    private final int errorIconSize = 0;
    
    public UserPicTarget(@org.jetbrains.annotations.NotNull
    android.widget.ImageView imageView, @androidx.annotation.ColorInt
    int tint, int errorIconSize) {
        super(null);
    }
    
    @java.lang.Override
    protected void setResource(@org.jetbrains.annotations.Nullable
    android.graphics.drawable.Drawable resource) {
    }
    
    @java.lang.Override
    public void onLoadFailed(@org.jetbrains.annotations.Nullable
    android.graphics.drawable.Drawable errorDrawable) {
    }
    
    @java.lang.Override
    public void onLoadStarted(@org.jetbrains.annotations.Nullable
    android.graphics.drawable.Drawable placeholder) {
    }
}