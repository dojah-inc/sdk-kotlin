package com.dojah_inc.dojah_android_sdk.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/CameraUtil;", "", "()V", "Companion", "dojah-module_mobileRelease"})
public final class CameraUtil {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.utils.CameraUtil.Companion Companion = null;
    private static androidx.camera.core.ImageCapture imageCapture;
    private static androidx.camera.video.VideoCapture<androidx.camera.video.Recorder> videoCapture;
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    private static androidx.camera.lifecycle.ProcessCameraProvider cameraProvider;
    private static androidx.camera.video.Recording recordingObj;
    
    public CameraUtil() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJE\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0016\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\r0\u0015J>\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\r0\u0015JE\u0010$\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110%\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\r0\u0015R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/CameraUtil$Companion;", "", "()V", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "recordingObj", "Landroidx/camera/video/Recording;", "videoCapture", "Landroidx/camera/video/VideoCapture;", "Landroidx/camera/video/Recorder;", "closeCamera", "", "recordVideo", "context", "Landroid/content/Context;", "tmpFileNamePrefix", "", "photoExtension", "onSaved", "Lkotlin/Function1;", "Landroid/net/Uri;", "Lkotlin/ParameterName;", "name", "photoFile", "startCamera", "fragment", "Landroidx/fragment/app/Fragment;", "camera", "Landroidx/camera/view/PreviewView;", "isVideo", "", "isFront", "onPreviewUpdate", "Landroidx/camera/view/PreviewView$StreamState;", "takePicture", "Ljava/io/File;", "dojah-module_mobileRelease"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void closeCamera() {
        }
        
        public final void takePicture(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        java.lang.String tmpFileNamePrefix, @org.jetbrains.annotations.NotNull
        java.lang.String photoExtension, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> onSaved) {
        }
        
        public final void recordVideo(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        java.lang.String tmpFileNamePrefix, @org.jetbrains.annotations.NotNull
        java.lang.String photoExtension, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onSaved) {
        }
        
        public final void startCamera(@org.jetbrains.annotations.NotNull
        androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull
        androidx.camera.view.PreviewView camera, boolean isVideo, boolean isFront, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super androidx.camera.view.PreviewView.StreamState, kotlin.Unit> onPreviewUpdate) {
        }
    }
}