package com.dojah_inc.dojah_android_sdk.core;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0004J\r\u0010\f\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0002\u0010\rJ\u000b\u0010\u000e\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/core/Event;", "T", "", "content", "(Ljava/lang/Object;)V", "_atomicHasBeenHandled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/lang/Object;", "hasBeenHandled", "", "getHasBeenHandled", "()Z", "getContentIfNotHandled", "()Ljava/lang/Object;", "peekContent", "dojah-module_mobileDebug"})
public final class Event<T extends java.lang.Object> {
    private final T content = null;
    private java.util.concurrent.atomic.AtomicBoolean _atomicHasBeenHandled;
    private final boolean hasBeenHandled = false;
    
    public Event(T content) {
        super();
    }
    
    public final boolean getHasBeenHandled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final T getContentIfNotHandled() {
        return null;
    }
    
    public final T peekContent() {
        return null;
    }
}