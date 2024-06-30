package com.dojah_inc.dojah_android_sdk.ui.main.viewmodel;

import java.lang.System;

@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JU\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020!2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010!2\u0006\u0010_\u001a\u00020`2\b\b\u0002\u0010a\u001a\u00020!2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010d\u001a\u0004\u0018\u00010!\u00a2\u0006\u0002\u0010eJ1\u0010f\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020!2\u000e\u0010j\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010kJ\u0016\u0010l\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020!J\u0016\u0010m\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0006\u0010n\u001a\u00020!J/\u0010m\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0006\u0010n\u001a\u00020!2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020%0\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010kJ6\u0010o\u001a\u00020\\2\u0006\u0010p\u001a\u00020h2\u0006\u0010]\u001a\u00020!2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010_\u001a\u00020`2\b\u0010b\u001a\u0004\u0018\u00010!J=\u0010q\u001a\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00190s0\u000b0r2\u0006\u0010t\u001a\u00020!2\u0006\u0010u\u001a\u0002062\u0006\u0010v\u001a\u00020!H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010wJ\n\u0010x\u001a\u0004\u0018\u00010yH\u0002J\u0018\u0010z\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010{2\u0006\u0010|\u001a\u00020hJ\u0010\u0010}\u001a\u0004\u0018\u00010~2\u0006\u0010\u007f\u001a\u00020!J\u0019\u0010\u0080\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010{2\u0006\u0010|\u001a\u00020hJ\u0019\u0010\u0081\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010{2\u0006\u0010|\u001a\u00020hJ\f\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0083\u0001H\u0002J\u001b\u0010\u0084\u0001\u001a\u00020!2\u0007\u0010d\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020!H\u0002J\u001b\u0010\u0087\u0001\u001a\u00020!2\u0007\u0010d\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020!H\u0002J\u0017\u0010\u0088\u0001\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010{2\u0006\u0010|\u001a\u00020hJ \u0010\u0089\u0001\u001a\u0004\u0018\u00010!2\u0007\u0010\u008a\u0001\u001a\u00020h2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010!H\u0002J@\u0010\u008b\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0007\u0010\u008a\u0001\u001a\u00020h2\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{2\u0007\u0010\u008d\u0001\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u008e\u0001JS\u0010\u008f\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0006\u0010|\u001a\u00020h2\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{2\u0007\u0010\u0090\u0001\u001a\u00020!2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u008d\u0001\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0092\u0001JJ\u0010\u0093\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0006\u0010|\u001a\u00020h2\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u008d\u0001\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0094\u0001J\u0019\u0010\u0095\u0001\u001a\u00020\\2\u0007\u0010\u0096\u0001\u001a\u00020h2\u0007\u0010\u0097\u0001\u001a\u00020hJ*\u0010\u0098\u0001\u001a\u00020\\2\u0007\u0010\u0099\u0001\u001a\u00020!2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020%0\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u009a\u0001JM\u0010\u009b\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0006\u0010_\u001a\u00020`2\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\f\b\u0002\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009f\u00012\f\b\u0002\u0010\u00a0\u0001\u001a\u0005\u0018\u00010\u00a1\u0001H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00a2\u0001J?\u0010\u00a3\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0006\u0010|\u001a\u00020h2\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{2\u0007\u0010\u008d\u0001\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u008e\u0001JQ\u0010\u00a4\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0r2\u0006\u0010|\u001a\u00020h2\u000f\b\u0002\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{2\u0006\u0010t\u001a\u00020!2\u0006\u0010v\u001a\u00020!2\u0007\u0010\u008d\u0001\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00a5\u0001J\u0017\u0010\u00a6\u0001\u001a\u00020\\2\u0006\u0010p\u001a\u00020h2\u0006\u0010]\u001a\u00020!J)\u0010\u00a7\u0001\u001a\u00020\\2\u0006\u0010]\u001a\u00020!2\u000b\b\u0002\u0010\u00a8\u0001\u001a\u0004\u0018\u00010!2\u000b\b\u0002\u0010\u0099\u0001\u001a\u0004\u0018\u00010!J\u0012\u0010\u00a9\u0001\u001a\u00020\\2\t\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u001bJ\u0012\u0010\u00ab\u0001\u001a\u00020\\2\t\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u001bJ\u0007\u0010\u00ac\u0001\u001a\u00020\\J\u0007\u0010\u00ad\u0001\u001a\u00020\\J\u0007\u0010\u00ae\u0001\u001a\u00020\\J\u0007\u0010\u00af\u0001\u001a\u00020\\J\u0007\u0010\u00b0\u0001\u001a\u00020\\J\u0007\u0010\u00b1\u0001\u001a\u00020\\J\u0012\u0010\u00b2\u0001\u001a\u00020\\2\t\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u001bJ\u0012\u0010\u00b3\u0001\u001a\u00020\\2\t\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u001bJ\u0012\u0010\u00b4\u0001\u001a\u00020\\2\t\u0010\u00b5\u0001\u001a\u0004\u0018\u00010!J\u0018\u0010\u00b6\u0001\u001a\u00020\\2\u0006\u0010p\u001a\u00020h2\u0007\u0010\u00b7\u0001\u001a\u00020!Jx\u0010\u00b8\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u000b0r2\u0007\u0010\u008a\u0001\u001a\u00020h2\u0007\u0010\u00b9\u0001\u001a\u00020!2\u0007\u0010\u0099\u0001\u001a\u00020!2\t\b\u0002\u0010\u00ba\u0001\u001a\u00020\u00152\t\b\u0002\u0010\u00bb\u0001\u001a\u00020\u00152\u0012\b\u0002\u0010\u00bc\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\\0\u00bd\u00012\u0012\b\u0002\u0010\u00be\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\\0\u00bd\u0001H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00bf\u0001J7\u0010\u00c0\u0001\u001a\u00020\\2\u0006\u0010|\u001a\u00020h2\u0007\u0010\u00b9\u0001\u001a\u00020!2\u0007\u0010\u0099\u0001\u001a\u00020!2\t\b\u0002\u0010\u00ba\u0001\u001a\u00020\u00152\t\b\u0002\u0010\u00bb\u0001\u001a\u00020\u0015J\u0007\u0010\u00c1\u0001\u001a\u00020\\J!\u0010\u00c2\u0001\u001a\u00020\\2\u0006\u0010|\u001a\u00020h2\u0007\u0010\u00c3\u0001\u001a\u00020!2\u0007\u0010\u00c4\u0001\u001a\u00020!J*\u0010\u00c5\u0001\u001a\u00020\\2\u0007\u0010\u008a\u0001\u001a\u00020h2\u0007\u0010\u0090\u0001\u001a\u00020!2\u000f\b\u0002\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020!0{J\u0018\u0010\u00c6\u0001\u001a\u00020\\2\u0006\u0010p\u001a\u00020h2\u0007\u0010\u00c7\u0001\u001a\u00020!J-\u0010\u00c8\u0001\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0007\u0010\u00c9\u0001\u001a\u00020!2\u0007\u0010\u0099\u0001\u001a\u00020!2\n\u0010\u00ca\u0001\u001a\u0005\u0018\u00010\u00cb\u0001J\"\u0010\u00cc\u0001\u001a\u00020\\2\u0007\u0010\u00c9\u0001\u001a\u00020!2\u0007\u0010\u0099\u0001\u001a\u00020!2\u0007\u0010\u00cd\u0001\u001a\u00020!J-\u0010\u00ce\u0001\u001a\u00020\\2\u0006\u0010g\u001a\u00020h2\u0007\u0010\u00c9\u0001\u001a\u00020!2\u0007\u0010\u0099\u0001\u001a\u00020!2\n\u0010\u00ca\u0001\u001a\u0005\u0018\u00010\u00cb\u0001J\u001f\u0010\u00cf\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u000b0rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00d0\u0001R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R4\u0010\r\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t \u000f*\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e0\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010*0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0,8F\u00a2\u0006\u0006\u001a\u0004\b-\u0010.R!\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\b0\u0010.R#\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000e0,8F\u00a2\u0006\u0006\u001a\u0004\b2\u0010.R!\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\b4\u0010.R\u0014\u00105\u001a\u0002068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u00109\u001a\u00020:8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b;\u0010<R!\u0010=\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\b>\u0010.R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00150,8F\u00a2\u0006\u0006\u001a\u0004\b?\u0010.R\u001f\u0010@\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bA\u0010.R\u000e\u0010B\u001a\u00020CX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0,8F\u00a2\u0006\u0006\u001a\u0004\bE\u0010.R\u0019\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0,8F\u00a2\u0006\u0006\u001a\u0004\bG\u0010.R\u001f\u0010H\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bI\u0010.R\u0011\u0010J\u001a\u00020!8F\u00a2\u0006\u0006\u001a\u0004\bK\u0010LR!\u0010M\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bN\u0010.R\u001f\u0010O\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bP\u0010.R!\u0010Q\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bR\u0010.R\u001f\u0010S\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bT\u0010.R!\u0010U\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bV\u0010.R\u0019\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'0,8F\u00a2\u0006\u0006\u001a\u0004\bX\u0010.R\u001f\u0010Y\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010*0\u000b0,8F\u00a2\u0006\u0006\u001a\u0004\bZ\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00d1\u0001"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "Landroidx/lifecycle/ViewModel;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "repo", "Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "(Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;)V", "_analysisRetryCountLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "_collectEmailLiveData", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SimpleResponse;", "_docAnalysisRetryCountLiveData", "Lkotlin/Pair;", "kotlin.jvm.PlatformType", "_docImageAnalysisLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DocImageAnalysisResponse;", "_imageAnalysisLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/ImageAnalysisResponse;", "_isResentOtpLiveData", "", "_livenessCheckLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/LivenessCheckResponse;", "_lookUpLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdEntityInterface;", "_selectedBizIdDataLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "_selectedGovIdDataLiveData", "_sendOtpLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SendOtpResponse;", "_submitBizLiveData", "_submitGovLiveData", "", "_submitLivenessLiveData", "_submitSignatureLiveData", "_validateOtpLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/ValidateOtpResponse;", "_verificationTypeLiveData", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/VerificationType;", "_verifyCheckRetryCountLiveData", "_verifyLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/LivenessVerifyResponse;", "analysisRetryCountLiveData", "Landroidx/lifecycle/LiveData;", "getAnalysisRetryCountLiveData", "()Landroidx/lifecycle/LiveData;", "collectEmailLiveData", "getCollectEmailLiveData", "docAnalysisRetryCountLiveData", "getDocAnalysisRetryCountLiveData", "docImageAnalysisLiveData", "getDocImageAnalysisLiveData", "dojahEnum", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "getDojahEnum", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "dojahPricing", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing;", "getDojahPricing", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahPricing;", "imageAnalysisLiveData", "getImageAnalysisLiveData", "isResentOtpLiveData", "livenessCheckLiveData", "getLivenessCheckLiveData", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "selectedBizDataLiveData", "getSelectedBizDataLiveData", "selectedGovDataLiveData", "getSelectedGovDataLiveData", "sendOtpLiveData", "getSendOtpLiveData", "signatureInfo", "getSignatureInfo", "()Ljava/lang/String;", "submitBizLiveData", "getSubmitBizLiveData", "submitGovLiveData", "getSubmitGovLiveData", "submitLivenessLiveData", "getSubmitLivenessLiveData", "submitSignatureLiveData", "getSubmitSignatureLiveData", "validateOtpLiveData", "getValidateOtpLiveData", "verificationTypeLiveData", "getVerificationTypeLiveData", "verifyLiveData", "getVerifyLiveData", "checkLiveness", "", "image", "image2", "page", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;", "param", "selfieType", "continueVerification", "docType", "(Ljava/lang/String;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "collectEmail", "viewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "email", "it", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/core/Result;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectEmailWithoutOtp", "collectPhoneNumber", "phone", "doCheckForDocId", "mainVm", "doGovIdLookUp", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdInterface;", "selectedIdEnum", "dojahConstants", "userId", "(Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAuthDataFromPref", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "getBusinessTypes", "", "verificationVm", "getCurrentPage", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/Step;", "currentPage", "getDocIDTypes", "getGovIdTypes", "getPreAuthDataFromPref", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/PreAuthResponse;", "getServerEnumOfDocType", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/GovDocType;", "selectedCountryCode", "getServerEnumValueOfDocType", "getVerifyMethods", "getlookUpPhoneNumberForGovData", "verifyVm", "logDataCompletedOrModeSelected", "services", "stepNumber", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logGovDataCollected", "userInputId", "lookUpEntity", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/util/List;Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdEntityInterface;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logGovImageCollected", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/util/List;Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdEntityInterface;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logIdOptionEvents", "navGraphVm", "activityVm", "logInvalidOtpFailedEvent", "currentRoute", "(Ljava/lang/String;Lcom/dojah_inc/dojah_android_sdk/core/Result;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logStepEvent", "event", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/EventTypes;", "failedReasons", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;", "error", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "(Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;Lcom/dojah_inc/dojah_android_sdk/ui/utils/EventTypes;Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logVerifyMethodSelected", "logVerifyTypeSelected", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performDocImageAnalysis", "performImageAnalysis", "imageType", "prefillBizId", "id", "prefillGovIdentity", "resetCollectedEmailLiveData", "resetDocImageAnalysisLiveData", "resetDocTypeLiveData", "resetImageAnalysisLiveData", "resetSubmitGovLiveData", "resetValidateOtpLiveData", "selectBizIdentity", "selectGovIdentity", "selectVerificationType", "type", "sendAdditionalDoc", "fileBase64", "sendOtp", "destination", "resent", "isEmail", "onSuccess", "Lkotlin/Function0;", "onError", "(Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtpSync", "startLoadingImageAnalysis", "submitBusinessData", "number", "companyName", "submitGovDataForm", "submitSignature", "name", "validateEmailOtp", "code", "sentOtpEntity", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SendOtpEntity;", "validateGovDataPhoneOtp", "referenceId", "validatePhoneOtp", "verifyLiveness", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dojah-module_mobileDebug"})
public final class GovDataViewModel extends androidx.lifecycle.ViewModel {
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    private final com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo = null;
    private final okhttp3.logging.HttpLoggingInterceptor.Logger logger = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType> _verificationTypeLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> _selectedGovIdDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> _selectedBizIdDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<java.lang.String>> _submitGovLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.domain.responses.GovIdEntityInterface> _lookUpLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpResponse>> _sendOtpLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse>> _validateOtpLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _collectEmailLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isResentOtpLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ImageAnalysisResponse>> _imageAnalysisLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DocImageAnalysisResponse>> _docImageAnalysisLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessCheckResponse>> _livenessCheckLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessVerifyResponse>> _verifyLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _submitLivenessLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _analysisRetryCountLiveData = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Integer, java.lang.Integer>> _docAnalysisRetryCountLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _submitBizLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _submitSignatureLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _verifyCheckRetryCountLiveData = null;
    
    public GovDataViewModel(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType> getVerificationTypeLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getSelectedGovDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getSelectedBizDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<java.lang.String>> getSubmitGovLiveData() {
        return null;
    }
    
    public final void resetSubmitGovLiveData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpResponse>> getSendOtpLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse>> getValidateOtpLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getCollectEmailLiveData() {
        return null;
    }
    
    public final void resetValidateOtpLiveData() {
    }
    
    public final void resetCollectedEmailLiveData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isResentOtpLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ImageAnalysisResponse>> getImageAnalysisLiveData() {
        return null;
    }
    
    public final void resetImageAnalysisLiveData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.DocImageAnalysisResponse>> getDocImageAnalysisLiveData() {
        return null;
    }
    
    public final void resetDocImageAnalysisLiveData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessCheckResponse>> getLivenessCheckLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessVerifyResponse>> getVerifyLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getSubmitLivenessLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getAnalysisRetryCountLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Pair<java.lang.Integer, java.lang.Integer>> getDocAnalysisRetryCountLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getSubmitBizLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getSubmitSignatureLiveData() {
        return null;
    }
    
    public final void resetDocTypeLiveData() {
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum getDojahEnum() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.responses.DojahPricing getDojahPricing() {
        return null;
    }
    
    public final void selectVerificationType(@org.jetbrains.annotations.Nullable
    java.lang.String type) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getGovIdTypes(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getVerifyMethods(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getDocIDTypes(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getBusinessTypes(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm) {
        return null;
    }
    
    public final void prefillGovIdentity(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr id) {
    }
    
    public final void prefillBizId(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr id) {
    }
    
    public final void selectGovIdentity(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr id) {
    }
    
    public final void selectBizIdentity(@org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr id) {
    }
    
    /**
     * do chain request to conclude gov data submission
     */
    public final void submitGovDataForm(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verifyVm, @org.jetbrains.annotations.NotNull
    java.lang.String userInputId, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> services) {
    }
    
    private final java.lang.Object logDataCompletedOrModeSelected(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verifyVm, java.util.List<java.lang.String> services, int stepNumber, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    private final java.lang.Object logVerifyTypeSelected(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, java.util.List<java.lang.String> services, java.lang.String selectedIdEnum, java.lang.String userId, int stepNumber, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    private final java.lang.Object doGovIdLookUp(java.lang.String selectedIdEnum, com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum dojahConstants, java.lang.String userId, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<? extends com.dojah_inc.dojah_android_sdk.domain.responses.GovIdInterface<? extends com.dojah_inc.dojah_android_sdk.domain.responses.GovIdEntityInterface>>>> continuation) {
        return null;
    }
    
    private final java.lang.Object logVerifyMethodSelected(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, java.util.List<java.lang.String> services, int stepNumber, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    private final java.lang.Object logGovImageCollected(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, java.util.List<java.lang.String> services, com.dojah_inc.dojah_android_sdk.domain.responses.GovIdEntityInterface lookUpEntity, int stepNumber, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    private final java.lang.Object logGovDataCollected(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, java.util.List<java.lang.String> services, java.lang.String userInputId, com.dojah_inc.dojah_android_sdk.domain.responses.GovIdEntityInterface lookUpEntity, int stepNumber, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    private final java.lang.String getlookUpPhoneNumberForGovData(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verifyVm, java.lang.String phone) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verifyVm, @org.jetbrains.annotations.NotNull
    java.lang.String destination, @org.jetbrains.annotations.NotNull
    java.lang.String currentRoute, boolean resent, boolean isEmail, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onError, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpResponse>>> continuation) {
        return null;
    }
    
    public final void sendOtpSync(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, @org.jetbrains.annotations.NotNull
    java.lang.String destination, @org.jetbrains.annotations.NotNull
    java.lang.String currentRoute, boolean resent, boolean isEmail) {
    }
    
    public final void validateGovDataPhoneOtp(@org.jetbrains.annotations.NotNull
    java.lang.String code, @org.jetbrains.annotations.NotNull
    java.lang.String currentRoute, @org.jetbrains.annotations.NotNull
    java.lang.String referenceId) {
    }
    
    public final void validateEmailOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, @org.jetbrains.annotations.NotNull
    java.lang.String code, @org.jetbrains.annotations.NotNull
    java.lang.String currentRoute, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpEntity sentOtpEntity) {
    }
    
    public final void collectEmailWithoutOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, @org.jetbrains.annotations.NotNull
    java.lang.String email) {
    }
    
    public final void validatePhoneOtp(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, @org.jetbrains.annotations.NotNull
    java.lang.String code, @org.jetbrains.annotations.NotNull
    java.lang.String currentRoute, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.domain.responses.SendOtpEntity sentOtpEntity) {
    }
    
    public final void collectPhoneNumber(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, @org.jetbrains.annotations.NotNull
    java.lang.String phone) {
    }
    
    private final java.lang.Object collectEmail(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, java.lang.String email, com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse> it, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object collectPhoneNumber(com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel viewModel, java.lang.String phone, com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse> it, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object logInvalidOtpFailedEvent(java.lang.String currentRoute, com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.ValidateOtpResponse> it, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void startLoadingImageAnalysis() {
    }
    
    public final void performImageAnalysis(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.Nullable
    java.lang.String imageType, @org.jetbrains.annotations.Nullable
    java.lang.String currentRoute) {
    }
    
    public final void performDocImageAnalysis(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel mainVm, @org.jetbrains.annotations.NotNull
    java.lang.String image) {
    }
    
    public final void doCheckForDocId(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel mainVm, @org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.Nullable
    java.lang.String image2, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, @org.jetbrains.annotations.Nullable
    java.lang.String selfieType) {
    }
    
    public final void checkLiveness(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.Nullable
    java.lang.String image2, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, @org.jetbrains.annotations.NotNull
    java.lang.String param, @org.jetbrains.annotations.Nullable
    java.lang.String selfieType, @org.jetbrains.annotations.Nullable
    java.lang.Boolean continueVerification, @org.jetbrains.annotations.Nullable
    java.lang.String docType) {
    }
    
    private final java.lang.Object verifyLiveness(kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.LivenessVerifyResponse>>> continuation) {
        return null;
    }
    
    public final void sendAdditionalDoc(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel mainVm, @org.jetbrains.annotations.NotNull
    java.lang.String fileBase64) {
    }
    
    public final void submitBusinessData(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel verificationVm, @org.jetbrains.annotations.NotNull
    java.lang.String number, @org.jetbrains.annotations.NotNull
    java.lang.String companyName) {
    }
    
    public final void submitSignature(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel mainVm, @org.jetbrains.annotations.NotNull
    java.lang.String name) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSignatureInfo() {
        return null;
    }
    
    public final void logIdOptionEvents(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel navGraphVm, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel activityVm) {
    }
    
    private final java.lang.String getServerEnumOfDocType(com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType docType, java.lang.String selectedCountryCode) {
        return null;
    }
    
    private final java.lang.String getServerEnumValueOfDocType(com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType docType, java.lang.String selectedCountryCode) {
        return null;
    }
    
    private final java.lang.Object logStepEvent(com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, com.dojah_inc.dojah_android_sdk.ui.utils.EventTypes event, com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons failedReasons, com.dojah_inc.dojah_android_sdk.core.Result.Error error, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Step getCurrentPage(@org.jetbrains.annotations.NotNull
    java.lang.String currentPage) {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse getAuthDataFromPref() {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse getPreAuthDataFromPref() {
        return null;
    }
}