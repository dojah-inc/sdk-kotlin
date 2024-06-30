package com.dojah_inc.dojah_android_sdk.ui.main.viewmodel;

import java.lang.System;

@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\'\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u000e2\n\b\u0002\u0010\u007f\u001a\u0004\u0018\u00010\u000e2\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u000eJ4\u0010\u0081\u0001\u001a\u00020\u001e2\u000f\b\u0002\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000e2\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001J;\u0010\u0087\u0001\u001a\u00020\u001e2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\u000b\b\u0002\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u000e2\u000f\b\u0002\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002J\u0016\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u00012\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0002J\u0007\u0010\u0091\u0001\u001a\u00020\u0007J\u0019\u0010\u0092\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00162\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\'\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00122\u0007\u0010\u0097\u0001\u001a\u00020\u0010H\u0002J\u0014\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J:\u0010\u009a\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\f\b\u0002\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u000b\b\u0002\u0010\u009b\u0001\u001a\u0004\u0018\u0001052\f\b\u0002\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001J:\u0010\u009e\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00162\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u001f\b\u0002\u0010\u009f\u0001\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00a0\u0001j\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u00a1\u0001J\u000f\u0010\u00a2\u0001\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0016J\u0012\u0010\u00a3\u0001\u001a\u0004\u0018\u00010(2\u0007\u0010\u00a4\u0001\u001a\u00020\u000eJ\t\u0010\u00a5\u0001\u001a\u0004\u0018\u00010\u000eJ\u0012\u0010\u00a6\u0001\u001a\u00020\u00102\t\u0010\u00a7\u0001\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u00a8\u0001\u001a\u00020\u00102\u0007\u0010\u00a7\u0001\u001a\u00020\u000eJ\u0011\u0010\u00a9\u0001\u001a\u00020\u00102\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001J\u0007\u0010\u00aa\u0001\u001a\u00020}J\u0007\u0010\u00ab\u0001\u001a\u00020}J4\u0010\u00ac\u0001\u001a\u00020}2\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000e2\u000f\b\u0002\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001JP\u0010\u00ad\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b0\u00ae\u00012\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\f\b\u0002\u0010\u00af\u0001\u001a\u0005\u0018\u00010\u00b0\u00012\f\b\u0002\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00b1\u0001J\u0011\u0010\u00b2\u0001\u001a\u00020}2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\u0007\u0010\u00b3\u0001\u001a\u00020}J\u0007\u0010\u00b4\u0001\u001a\u00020}J\u0014\u0010\u00b5\u0001\u001a\u00020}2\t\u0010\u00b6\u0001\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u00b7\u0001\u001a\u00020}2\u0007\u0010\u00b8\u0001\u001a\u00020\u000eJ-\u0010\u00b9\u0001\u001a\u00020}2\b\u0010\u00ba\u0001\u001a\u00030\u00bb\u00012\b\u0010\u00bc\u0001\u001a\u00030\u00bb\u00012\u0007\u0010\u00bd\u0001\u001a\u00020\u000e2\u0007\u0010\u00be\u0001\u001a\u00020\u0010J+\u0010\u00bf\u0001\u001a\u00020}2\u0007\u0010\u00c0\u0001\u001a\u00020\u000e2\u0007\u0010\u00c1\u0001\u001a\u00020\u000e2\u0007\u0010\u00c2\u0001\u001a\u00020\u000e2\u0007\u0010\u00c3\u0001\u001a\u00020\u000eJ%\u0010\u00c4\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00122\u0007\u0010\u0097\u0001\u001a\u00020\u0010J%\u0010\u00c5\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00122\u0007\u0010\u0097\u0001\u001a\u00020\u0010J\u0010\u0010\u00c6\u0001\u001a\u00020}2\u0007\u0010\u00c7\u0001\u001a\u00020\u0017J\u0010\u0010\u00c8\u0001\u001a\u00020}2\u0007\u0010\u0096\u0001\u001a\u00020\u0012J\u0007\u0010\u00c9\u0001\u001a\u00020}R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00190\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u001d\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b\u0018\u00010\u00190\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010\u00100\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010%\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010\u00100\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R0\u0010&\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00160\u00190\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\'\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020(\u0018\u00010\u00160\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001050\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u00106\u001a\u0004\u0018\u00010\f8F\u00a2\u0006\u0006\u001a\u0004\b7\u00108R\u001d\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\b;\u0010<R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000e0:8F\u00a2\u0006\u0006\u001a\u0004\b>\u0010<R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00100:8F\u00a2\u0006\u0006\u001a\u0004\b@\u0010<R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00120:8F\u00a2\u0006\u0006\u001a\u0004\bB\u0010<R\u001d\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bD\u0010<R\u001d\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160:8F\u00a2\u0006\u0006\u001a\u0004\bF\u0010<R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\'\u0010G\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00190:8F\u00a2\u0006\u0006\u001a\u0004\bH\u0010<R\u0019\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0:8F\u00a2\u0006\u0006\u001a\u0004\bJ\u0010<R\u0011\u0010K\u001a\u00020L8F\u00a2\u0006\u0006\u001a\u0004\bM\u0010NR+\u0010O\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b\u0018\u00010\u00190:8F\u00a2\u0006\u0006\u001a\u0004\bP\u0010<R\u0017\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00120:8F\u00a2\u0006\u0006\u001a\u0004\bR\u0010<R\u001d\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bT\u0010<R\u0017\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00100:8F\u00a2\u0006\u0006\u001a\u0004\bU\u0010<R\u0017\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00100:8F\u00a2\u0006\u0006\u001a\u0004\bV\u0010<R\u0013\u0010W\u001a\u0004\u0018\u00010(8F\u00a2\u0006\u0006\u001a\u0004\bX\u0010YR\u000e\u0010Z\u001a\u00020[X\u0082\u0004\u00a2\u0006\u0002\n\u0000R3\u0010\\\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00160\u00190:8F\u00a2\u0006\u0006\u001a\u0004\b]\u0010<R\u000e\u0010^\u001a\u00020_X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010`\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020(\u0018\u00010\u00160:8F\u00a2\u0006\u0006\u001a\u0004\ba\u0010<R\u001d\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bc\u0010<R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bd\u0010eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170:8F\u00a2\u0006\u0006\u001a\u0004\bg\u0010<R\u0019\u0010h\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0:8F\u00a2\u0006\u0006\u001a\u0004\bi\u0010<R\u0017\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00120:8F\u00a2\u0006\u0006\u001a\u0004\bk\u0010<R\u001d\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bm\u0010<R\u001d\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bo\u0010<R\u001d\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000b0:8F\u00a2\u0006\u0006\u001a\u0004\bq\u0010<R\u0011\u0010r\u001a\u00020s\u00a2\u0006\b\n\u0000\u001a\u0004\bt\u0010uR\u0017\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00100:8F\u00a2\u0006\u0006\u001a\u0004\bw\u0010<R\u0017\u0010x\u001a\b\u0012\u0004\u0012\u00020\u000e0:8F\u00a2\u0006\u0006\u001a\u0004\by\u0010<R\u0019\u0010z\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001050:8F\u00a2\u0006\u0006\u001a\u0004\b{\u0010<\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00ca\u0001"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/VerificationViewModel;", "Landroidx/lifecycle/ViewModel;", "prefManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "repo", "Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;", "countryManager", "Lcom/dojah_inc/dojah_android_sdk/data/io/CountryManager;", "(Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;Lcom/dojah_inc/dojah_android_sdk/data/repository/DojahRepository;Lcom/dojah_inc/dojah_android_sdk/data/io/CountryManager;)V", "_authDataLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dojah_inc/dojah_android_sdk/core/Result;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "_authErrLiveData", "", "_authVerificationCompletedLD", "", "_backDocUriLiveData", "Landroid/net/Uri;", "_checkIpDataLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/CheckIpResponse;", "_countryLiveData", "", "Lcom/dojah_inc/dojah_android_sdk/domain/Country;", "_docInfoLiveData", "Lkotlin/Pair;", "Lcom/dojah_inc/dojah_android_sdk/domain/DocumentInfo;", "_docTypeLiveData", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/GovDocType;", "_eventLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/request/EventRequest;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/SimpleResponse;", "_frontDocUriLiveData", "_getIpDataLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/GetIpResponse;", "_isBackDocLiveData", "kotlin.jvm.PlatformType", "_isUploadDocLiveData", "_mailLiveData", "_pages", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/Step;", "_preAuthDataLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/PreAuthResponse;", "_selectedCountryLiveData", "_selectedGovIdDataLiveData", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/EnumAttr;", "_selfiePhotoUriLiveData", "_submitAddressLiveData", "_submitGovLiveData", "_submitUserLiveData", "_timerOtpDoneLiveData", "_timerOtpLiveData", "_verificationTypeLiveData", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/VerificationType;", "authDataFromPref", "getAuthDataFromPref", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/AuthResponse;", "authDataLiveData", "Landroidx/lifecycle/LiveData;", "getAuthDataLiveData", "()Landroidx/lifecycle/LiveData;", "authErrLiveData", "getAuthErrLiveData", "authVerificationCompletedLD", "getAuthVerificationCompletedLD", "backDocUriLiveData", "getBackDocUriLiveData", "checkIpDataLiveData", "getCheckIpDataLiveData", "countryLiveData", "getCountryLiveData", "docInfoLiveData", "getDocInfoLiveData", "docTypeLiveData", "getDocTypeLiveData", "dojahEnum", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "getDojahEnum", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/DojahEnum;", "eventLiveData", "getEventLiveData", "frontDocUriLiveData", "getFrontDocUriLiveData", "getIpDataLiveData", "getGetIpDataLiveData", "isBackDocLiveData", "isUploadDocLiveData", "lastStep", "getLastStep", "()Lcom/dojah_inc/dojah_android_sdk/domain/responses/Step;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "mailLiveData", "getMailLiveData", "otpTimer", "Landroid/os/CountDownTimer;", "pages", "getPages", "preAuthDataLiveData", "getPreAuthDataLiveData", "getPrefManager", "()Lcom/dojah_inc/dojah_android_sdk/data/io/SharedPreferenceManager;", "selectedCountryLiveData", "getSelectedCountryLiveData", "selectedGovDataLiveData", "getSelectedGovDataLiveData", "selfieUriLiveData", "getSelfieUriLiveData", "submitAddressLiveData", "getSubmitAddressLiveData", "submitGovLiveData", "getSubmitGovLiveData", "submitUserLiveData", "getSubmitUserLiveData", "targetDuration", "Ljava/time/Duration;", "getTargetDuration", "()Ljava/time/Duration;", "timerOtpDoneLiveData", "getTimerOtpDoneLiveData", "timerOtpLiveData", "getTimerOtpLiveData", "verificationTypeLiveData", "getVerificationTypeLiveData", "authenticate", "", "widgetId", "referenceId", "email", "buildEventRequest", "services", "eventType", "eventValue", "stepNumber", "", "buildStepEventRequest", "page", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;", "event", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/EventTypes;", "failureCode", "getBodyErrorCode", "", "error", "Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;", "getCountries", "getCountriesFullFromPrefs", "context", "Landroid/content/Context;", "getDocInfo", "uri", "isUpload", "getDojahAppAttribute", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/App;", "getErrorMessage", "verifyType", "govDataViewModel", "Lcom/dojah_inc/dojah_android_sdk/ui/main/viewmodel/GovDataViewModel;", "getFullCountryNames", "originalCountryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getPagesFromPrefs", "getStepWithPageName", "currentPage", "getUserCountryFromPrefs", "isDisposableMail", "searchItem", "isFreeMail", "isLastPage", "loadCountries", "logCountryEvents", "logEvent", "logStepEvent", "Lkotlinx/coroutines/flow/Flow;", "failedReasons", "Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;", "(Lcom/dojah_inc/dojah_android_sdk/ui/utils/KycPages;Lcom/dojah_inc/dojah_android_sdk/ui/utils/EventTypes;Lcom/dojah_inc/dojah_android_sdk/ui/utils/FailedReasons;Lcom/dojah_inc/dojah_android_sdk/core/Result$Error;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readMailList", "resetAuthVerificationCompleted", "resetEventData", "saveBrandColor", "color", "selectDocType", "type", "sendAddress", "selectedAddressLatitude", "", "selectedAddressLongitude", "addressName", "match", "sendUserData", "dob", "firstName", "lastName", "middleName", "setBackDocUri", "setFrontDocUri", "setSelectedCountry", "country", "setSelfieUri", "startTimer", "dojah-module_mobileReleaseNoMinify"})
public final class VerificationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager = null;
    private final com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo = null;
    private final com.dojah_inc.dojah_android_sdk.data.io.CountryManager countryManager = null;
    private final okhttp3.logging.HttpLoggingInterceptor.Logger logger = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country>> _countryLiveData = null;
    private final androidx.lifecycle.MutableLiveData<android.net.Uri> _frontDocUriLiveData = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<com.dojah_inc.dojah_android_sdk.domain.DocumentInfo, com.dojah_inc.dojah_android_sdk.domain.DocumentInfo>> _docInfoLiveData = null;
    private final androidx.lifecycle.MutableLiveData<android.net.Uri> _backDocUriLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isBackDocLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isUploadDocLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType> _docTypeLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType> _verificationTypeLiveData = null;
    private final androidx.lifecycle.MutableLiveData<android.net.Uri> _selfiePhotoUriLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _timerOtpLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _timerOtpDoneLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse>> _preAuthDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse>> _authDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _authVerificationCompletedLD = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.CheckIpResponse>> _checkIpDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.GetIpResponse>> _getIpDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _authErrLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step>> _pages = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<com.dojah_inc.dojah_android_sdk.domain.request.EventRequest, com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> _eventLiveData = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.util.List<java.lang.String>, java.util.List<java.lang.String>>> _mailLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.domain.Country> _selectedCountryLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> _selectedGovIdDataLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<java.lang.String>> _submitGovLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _submitAddressLiveData = null;
    private final androidx.lifecycle.MutableLiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> _submitUserLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final java.time.Duration targetDuration = null;
    private final android.os.CountDownTimer otpTimer = null;
    
    public VerificationViewModel(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager prefManager, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.repository.DojahRepository repo, @org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.data.io.CountryManager countryManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager getPrefManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step>> getPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.PreAuthResponse>> getPreAuthDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse>> getAuthDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getAuthVerificationCompletedLD() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.CheckIpResponse>> getCheckIpDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.GetIpResponse>> getGetIpDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getAuthErrLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.dojah_inc.dojah_android_sdk.domain.Country>> getCountryLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Pair<com.dojah_inc.dojah_android_sdk.domain.request.EventRequest, com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> getEventLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Pair<java.util.List<java.lang.String>, java.util.List<java.lang.String>>> getMailLiveData() {
        return null;
    }
    
    public final void resetEventData() {
    }
    
    public final void resetAuthVerificationCompleted() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<android.net.Uri> getFrontDocUriLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<android.net.Uri> getBackDocUriLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Pair<com.dojah_inc.dojah_android_sdk.domain.DocumentInfo, com.dojah_inc.dojah_android_sdk.domain.DocumentInfo>> getDocInfoLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isBackDocLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isUploadDocLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType> getDocTypeLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType> getVerificationTypeLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<android.net.Uri> getSelfieUriLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getTimerOtpLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getTimerOtpDoneLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.domain.Country> getSelectedCountryLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr> getSelectedGovDataLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<java.lang.String>> getSubmitGovLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getSubmitAddressLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>> getSubmitUserLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.DojahEnum getDojahEnum() {
        return null;
    }
    
    private final void saveBrandColor(java.lang.String color) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.DocumentInfo setFrontDocUri(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.net.Uri uri, boolean isUpload) {
        return null;
    }
    
    public final void setSelfieUri(@org.jetbrains.annotations.NotNull
    android.net.Uri uri) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.DocumentInfo setBackDocUri(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.net.Uri uri, boolean isUpload) {
        return null;
    }
    
    public final void selectDocType(@org.jetbrains.annotations.NotNull
    java.lang.String type) {
    }
    
    public final void loadCountries() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.data.io.CountryManager getCountries() {
        return null;
    }
    
    public final void authenticate(@org.jetbrains.annotations.NotNull
    java.lang.String widgetId, @org.jetbrains.annotations.Nullable
    java.lang.String referenceId, @org.jetbrains.annotations.Nullable
    java.lang.String email) {
    }
    
    public final void sendUserData(@org.jetbrains.annotations.NotNull
    java.lang.String dob, @org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.NotNull
    java.lang.String lastName, @org.jetbrains.annotations.NotNull
    java.lang.String middleName) {
    }
    
    public final void sendAddress(double selectedAddressLatitude, double selectedAddressLongitude, @org.jetbrains.annotations.NotNull
    java.lang.String addressName, boolean match) {
    }
    
    private final java.lang.Object logStepEvent(com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, com.dojah_inc.dojah_android_sdk.ui.utils.EventTypes event, com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons failedReasons, com.dojah_inc.dojah_android_sdk.core.Result.Error error, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.dojah_inc.dojah_android_sdk.core.Result<com.dojah_inc.dojah_android_sdk.domain.responses.SimpleResponse>>> continuation) {
        return null;
    }
    
    public final void logEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventType, @org.jetbrains.annotations.NotNull
    java.lang.String eventValue, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> services, int stepNumber) {
    }
    
    public final void logCountryEvents() {
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.request.EventRequest buildStepEventRequest(com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, com.dojah_inc.dojah_android_sdk.ui.utils.EventTypes event, java.lang.String failureCode, java.util.List<java.lang.String> services) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.request.EventRequest buildEventRequest(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> services, @org.jetbrains.annotations.NotNull
    java.lang.String eventType, @org.jetbrains.annotations.NotNull
    java.lang.String eventValue, int stepNumber) {
        return null;
    }
    
    private final java.lang.Number getBodyErrorCode(com.dojah_inc.dojah_android_sdk.core.Result.Error error) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getErrorMessage(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.core.Result.Error error, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType verifyType, @org.jetbrains.annotations.Nullable
    com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel govDataViewModel) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getFullCountryNames(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.util.ArrayList<java.lang.String> originalCountryList) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.App getDojahAppAttribute(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getCountriesFullFromPrefs(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserCountryFromPrefs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.dojah_inc.dojah_android_sdk.domain.responses.Step> getPagesFromPrefs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Step getLastStep() {
        return null;
    }
    
    public final boolean isLastPage(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.ui.utils.KycPages page) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.AuthResponse getAuthDataFromPref() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.dojah_inc.dojah_android_sdk.domain.responses.Step getStepWithPageName(@org.jetbrains.annotations.NotNull
    java.lang.String currentPage) {
        return null;
    }
    
    private final com.dojah_inc.dojah_android_sdk.domain.DocumentInfo getDocInfo(android.content.Context context, android.net.Uri uri, boolean isUpload) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.Duration getTargetDuration() {
        return null;
    }
    
    public final void startTimer() {
    }
    
    public final void setSelectedCountry(@org.jetbrains.annotations.NotNull
    com.dojah_inc.dojah_android_sdk.domain.Country country) {
    }
    
    public final void readMailList(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final boolean isDisposableMail(@org.jetbrains.annotations.Nullable
    java.lang.String searchItem) {
        return false;
    }
    
    public final boolean isFreeMail(@org.jetbrains.annotations.NotNull
    java.lang.String searchItem) {
        return false;
    }
}