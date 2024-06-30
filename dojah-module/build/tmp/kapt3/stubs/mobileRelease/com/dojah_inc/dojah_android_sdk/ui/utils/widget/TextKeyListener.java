package com.dojah_inc.dojah_android_sdk.ui.utils.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0003789B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\tH\u0016J\u000e\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002J(\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016J8\u0010*\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0016J(\u0010-\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016J\u0006\u0010.\u001a\u00020\u001cJ\u001e\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\tJ\u0010\u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u001aH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u000e\u0010\u0012\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener;", "Landroid/text/method/BaseKeyListener;", "Landroid/text/SpanWatcher;", "()V", "ACTIVE", "", "getACTIVE", "()Ljava/lang/Object;", "AUTO_CAP", "", "AUTO_PERIOD", "AUTO_TEXT", "CAPPED", "getCAPPED", "INHIBIT_REPLACEMENT", "getINHIBIT_REPLACEMENT", "LAST_TYPED", "getLAST_TYPED", "SHOW_PASSWORD", "mObserver", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener$SettingsObserver;", "mPrefs", "mPrefsInited", "", "mResolver", "Ljava/lang/ref/WeakReference;", "Landroid/content/ContentResolver;", "clear", "", "e", "Landroid/text/Editable;", "getInputType", "getPrefs", "context", "Landroid/content/Context;", "initPrefs", "onSpanAdded", "s", "Landroid/text/Spannable;", "what", "start", "end", "onSpanChanged", "st", "en", "onSpanRemoved", "release", "shouldCap", "cap", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener$Capitalize;", "cs", "", "off", "updatePrefs", "resolver", "Capitalize", "Replaced", "SettingsObserver", "dojah-module_mobileRelease"})
public final class TextKeyListener extends android.text.method.BaseKeyListener implements android.text.SpanWatcher {
    @org.jetbrains.annotations.NotNull
    public static final com.dojah_inc.dojah_android_sdk.ui.utils.widget.TextKeyListener INSTANCE = null;
    private static int mPrefs = 0;
    private static boolean mPrefsInited = false;
    private static java.lang.ref.WeakReference<android.content.ContentResolver> mResolver;
    private static com.dojah_inc.dojah_android_sdk.ui.utils.widget.TextKeyListener.SettingsObserver mObserver;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.Object ACTIVE = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.Object CAPPED = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.Object INHIBIT_REPLACEMENT = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.Object LAST_TYPED = null;
    public static final int AUTO_CAP = 1;
    public static final int AUTO_TEXT = 2;
    public static final int AUTO_PERIOD = 4;
    public static final int SHOW_PASSWORD = 8;
    
    private TextKeyListener() {
        super();
    }
    
    @java.lang.Override
    public int getInputType() {
        return 0;
    }
    
    @java.lang.Override
    public void onSpanAdded(@org.jetbrains.annotations.NotNull
    android.text.Spannable s, @org.jetbrains.annotations.NotNull
    java.lang.Object what, int start, int end) {
    }
    
    @java.lang.Override
    public void onSpanRemoved(@org.jetbrains.annotations.NotNull
    android.text.Spannable s, @org.jetbrains.annotations.NotNull
    java.lang.Object what, int start, int end) {
    }
    
    @java.lang.Override
    public void onSpanChanged(@org.jetbrains.annotations.NotNull
    android.text.Spannable s, @org.jetbrains.annotations.NotNull
    java.lang.Object what, int start, int end, int st, int en) {
    }
    
    public final void release() {
    }
    
    private final void initPrefs(android.content.Context context) {
    }
    
    private final void updatePrefs(android.content.ContentResolver resolver) {
    }
    
    public final int getPrefs(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getACTIVE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getCAPPED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getINHIBIT_REPLACEMENT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getLAST_TYPED() {
        return null;
    }
    
    /**
     * Returns whether it makes sense to automatically capitalize at the
     * specified position in the specified text, with the specified rules.
     *
     * @param cap the capitalization rules to consider.
     * @param cs the text in which an insertion is being made.
     * @param off the offset into that text where the insertion is being made.
     *
     * @return whether the character being inserted should be capitalized.
     */
    public final boolean shouldCap(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.utils.widget.TextKeyListener.Capitalize cap, @org.jetbrains.annotations.NotNull
    java.lang.CharSequence cs, int off) {
        return false;
    }
    
    /**
     * Clear all the input state (autotext, autocap, multitap, undo)
     * from the specified Editable, going beyond Editable.clear(), which
     * just clears the text but not the input state.
     *
     * @param e the buffer whose text and state are to be cleared.
     */
    public final void clear(@org.jetbrains.annotations.NotNull
    android.text.Editable e) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener$Capitalize;", "", "(Ljava/lang/String;I)V", "NONE", "SENTENCES", "WORDS", "CHARACTERS", "dojah-module_mobileRelease"})
    public static enum Capitalize {
        /*public static final*/ NONE /* = new NONE() */,
        /*public static final*/ SENTENCES /* = new SENTENCES() */,
        /*public static final*/ WORDS /* = new WORDS() */,
        /*public static final*/ CHARACTERS /* = new CHARACTERS() */;
        
        Capitalize() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener$Replaced;", "Landroid/text/NoCopySpan;", "mText", "", "([C)V", "dojah-module_mobileRelease"})
    public static final class Replaced implements android.text.NoCopySpan {
        private final char[] mText = null;
        
        public Replaced(@org.jetbrains.annotations.NotNull
        char[] mText) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/utils/widget/TextKeyListener$SettingsObserver;", "Landroid/database/ContentObserver;", "()V", "onChange", "", "selfChange", "", "dojah-module_mobileRelease"})
    public static final class SettingsObserver extends android.database.ContentObserver {
        
        public SettingsObserver() {
            super(null);
        }
        
        @java.lang.Override
        public void onChange(boolean selfChange) {
        }
    }
}