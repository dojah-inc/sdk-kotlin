package com.dojah_inc.dojah_android_sdk.domain.responses;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\'\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0003\b\u00f2\u0001\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00a5\u0005\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010;J\f\u0010\u00bc\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00bd\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00be\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00bf\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c0\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c1\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c3\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c4\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c5\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c6\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c7\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c8\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00c9\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ca\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00cb\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00cc\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00cd\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ce\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00cf\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d0\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d1\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d3\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d4\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d5\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d6\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d7\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d8\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00d9\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00da\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00db\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00dc\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00dd\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00de\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00df\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e0\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e1\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e3\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e4\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e5\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e6\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e7\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e8\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00e9\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ea\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00eb\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ec\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ed\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ee\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ef\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00f0\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00f1\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00f2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00f3\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00aa\u0005\u0010\u00f4\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0017\u0010\u00f5\u0001\u001a\u00030\u00f6\u00012\n\u0010\u00f7\u0001\u001a\u0005\u0018\u00010\u00f8\u0001H\u00d6\u0003J\u000b\u0010\u00f9\u0001\u001a\u00030\u00fa\u0001H\u00d6\u0001J\n\u0010\u00fb\u0001\u001a\u00020\u0003H\u00d6\u0001R \u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R \u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010=\"\u0004\bA\u0010?R \u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010=\"\u0004\bC\u0010?R \u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010=\"\u0004\bE\u0010?R\u0013\u00109\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010=R\u0016\u0010G\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bH\u0010=R \u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010=\"\u0004\bJ\u0010?R\u0016\u0010K\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bL\u0010=R \u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010=\"\u0004\bN\u0010?R \u0010\u0019\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010=\"\u0004\bP\u0010?R \u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010=\"\u0004\bR\u0010?R \u0010\u001a\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010=\"\u0004\bT\u0010?R\u0016\u0010U\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bV\u0010=R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010=\"\u0004\bX\u0010?R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bY\u0010=\"\u0004\bZ\u0010?R \u00107\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010=\"\u0004\b\\\u0010?R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b]\u0010=\"\u0004\b^\u0010?R\u0016\u0010_\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b`\u0010=R\u0016\u0010a\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bb\u0010=R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bc\u0010=\"\u0004\bd\u0010?R\u0016\u0010e\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bf\u0010=R\u0016\u0010g\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bh\u0010=R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bi\u0010=\"\u0004\bj\u0010?R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bk\u0010=\"\u0004\bl\u0010?R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010=\"\u0004\bn\u0010?R \u00106\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bo\u0010=\"\u0004\bp\u0010?R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bq\u0010=\"\u0004\br\u0010?R \u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u0010=\"\u0004\bt\u0010?R \u0010\u001f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u0010=\"\u0004\bv\u0010?R \u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bw\u0010=\"\u0004\bx\u0010?R \u0010\u001c\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u0010=\"\u0004\bz\u0010?R \u0010 \u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b{\u0010=\"\u0004\b|\u0010?R \u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b}\u0010=\"\u0004\b~\u0010?R!\u0010#\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010=\"\u0005\b\u0080\u0001\u0010?R\"\u0010!\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010=\"\u0005\b\u0082\u0001\u0010?R\"\u0010\"\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0001\u0010=\"\u0005\b\u0084\u0001\u0010?R\"\u0010)\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010=\"\u0005\b\u0086\u0001\u0010?R\"\u0010*\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010=\"\u0005\b\u0088\u0001\u0010?R\"\u0010$\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0001\u0010=\"\u0005\b\u008a\u0001\u0010?R\"\u0010%\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010=\"\u0005\b\u008c\u0001\u0010?R\"\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010=\"\u0005\b\u008e\u0001\u0010?R\u0018\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0007\u001a\u0005\b\u0090\u0001\u0010=R\"\u0010&\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010=\"\u0005\b\u0092\u0001\u0010?R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010=\"\u0005\b\u0094\u0001\u0010?R\"\u0010\'\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0001\u0010=\"\u0005\b\u0096\u0001\u0010?R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010=\"\u0005\b\u0098\u0001\u0010?R\"\u0010(\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010=\"\u0005\b\u009a\u0001\u0010?R\"\u0010+\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010=\"\u0005\b\u009c\u0001\u0010?R\"\u00100\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010=\"\u0005\b\u009e\u0001\u0010?R\"\u00101\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010=\"\u0005\b\u00a0\u0001\u0010?R\"\u0010-\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a1\u0001\u0010=\"\u0005\b\u00a2\u0001\u0010?R\"\u0010.\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a3\u0001\u0010=\"\u0005\b\u00a4\u0001\u0010?R\"\u0010,\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a5\u0001\u0010=\"\u0005\b\u00a6\u0001\u0010?R\"\u0010/\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a7\u0001\u0010=\"\u0005\b\u00a8\u0001\u0010?R\"\u00102\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a9\u0001\u0010=\"\u0005\b\u00aa\u0001\u0010?R\"\u00103\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ab\u0001\u0010=\"\u0005\b\u00ac\u0001\u0010?R\"\u00104\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010=\"\u0005\b\u00ae\u0001\u0010?R\"\u00105\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00af\u0001\u0010=\"\u0005\b\u00b0\u0001\u0010?R\"\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b1\u0001\u0010=\"\u0005\b\u00b2\u0001\u0010?R\"\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b3\u0001\u0010=\"\u0005\b\u00b4\u0001\u0010?R\"\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b5\u0001\u0010=\"\u0005\b\u00b6\u0001\u0010?R\"\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b7\u0001\u0010=\"\u0005\b\u00b8\u0001\u0010?R\"\u00108\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b9\u0001\u0010=\"\u0005\b\u00ba\u0001\u0010?R\u0014\u0010:\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00bb\u0001\u0010=\u00a8\u0006\u00fc\u0001"}, d2 = {"Lcom/dojah_inc/dojah_android_sdk/domain/responses/NinLookUpEntity;", "Lcom/dojah_inc/dojah_android_sdk/domain/responses/GovIdEntityInterface;", "nin", "", "firstname", "firstName", "middlename", "middleName", "surname", "lastName", "maidenname", "telephoneno", "phoneNum", "state", "place", "profession", "title", "height", "email", "birthdate", "dateOfBirth", "birthstate", "birthcountry", "centralID", "documentno", "educationallevel", "employmentstatus", "nokFirstname", "nokLastname", "nokMiddlename", "nokAddress1", "nokAddress2", "nokLga", "nokState", "nokTown", "nokPostalcode", "othername", "pfirstname", "photo", "pmiddlename", "psurname", "nspokenlang", "ospokenlang", "religion", "residenceTown", "residenceLga", "residenceState", "residencestatus", "residenceAddressLine1", "residenceAddressLine2", "selfOriginLga", "selfOriginPlace", "selfOriginState", "signature", "nationality", "gender", "trackingId", "customer", "uuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBirthcountry", "()Ljava/lang/String;", "setBirthcountry", "(Ljava/lang/String;)V", "getBirthdate", "setBirthdate", "getBirthstate", "setBirthstate", "getCentralID", "setCentralID", "getCustomer", "customerID", "getCustomerID", "getDateOfBirth", "setDateOfBirth", "dob", "getDob", "getDocumentno", "setDocumentno", "getEducationallevel", "setEducationallevel", "getEmail", "setEmail", "getEmploymentstatus", "setEmploymentstatus", "fName", "getFName", "getFirstName", "setFirstName", "getFirstname", "setFirstname", "getGender", "setGender", "getHeight", "setHeight", "image", "getImage", "lName", "getLName", "getLastName", "setLastName", "licenseNum", "getLicenseNum", "mName", "getMName", "getMaidenname", "setMaidenname", "getMiddleName", "setMiddleName", "getMiddlename", "setMiddlename", "getNationality", "setNationality", "getNin", "setNin", "getNokAddress1", "setNokAddress1", "getNokAddress2", "setNokAddress2", "getNokFirstname", "setNokFirstname", "getNokLastname", "setNokLastname", "getNokLga", "setNokLga", "getNokMiddlename", "setNokMiddlename", "getNokPostalcode", "setNokPostalcode", "getNokState", "setNokState", "getNokTown", "setNokTown", "getNspokenlang", "setNspokenlang", "getOspokenlang", "setOspokenlang", "getOthername", "setOthername", "getPfirstname", "setPfirstname", "getPhoneNum", "setPhoneNum", "phoneNumber", "getPhoneNumber", "getPhoto", "setPhoto", "getPlace", "setPlace", "getPmiddlename", "setPmiddlename", "getProfession", "setProfession", "getPsurname", "setPsurname", "getReligion", "setReligion", "getResidenceAddressLine1", "setResidenceAddressLine1", "getResidenceAddressLine2", "setResidenceAddressLine2", "getResidenceLga", "setResidenceLga", "getResidenceState", "setResidenceState", "getResidenceTown", "setResidenceTown", "getResidencestatus", "setResidencestatus", "getSelfOriginLga", "setSelfOriginLga", "getSelfOriginPlace", "setSelfOriginPlace", "getSelfOriginState", "setSelfOriginState", "getSignature", "setSignature", "getState", "setState", "getSurname", "setSurname", "getTelephoneno", "setTelephoneno", "getTitle", "setTitle", "getTrackingId", "setTrackingId", "getUuid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dojah-module_mobileRelease"})
public final class NinLookUpEntity implements com.dojah_inc.dojah_android_sdk.domain.responses.GovIdEntityInterface {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nin")
    private java.lang.String nin;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "firstname")
    private java.lang.String firstname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "first_name")
    private java.lang.String firstName;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "middlename")
    private java.lang.String middlename;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "middle_name")
    private java.lang.String middleName;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "surname")
    private java.lang.String surname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "last_name")
    private java.lang.String lastName;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "maidenname")
    private java.lang.String maidenname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "telephoneno")
    private java.lang.String telephoneno;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "phone_number")
    private java.lang.String phoneNum;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "state")
    private java.lang.String state;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "place")
    private java.lang.String place;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "profession")
    private java.lang.String profession;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "title")
    private java.lang.String title;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "height")
    private java.lang.String height;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "email")
    private java.lang.String email;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "birthdate")
    private java.lang.String birthdate;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "date_of_birth")
    private java.lang.String dateOfBirth;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "birthstate")
    private java.lang.String birthstate;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "birthcountry")
    private java.lang.String birthcountry;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "centralID")
    private java.lang.String centralID;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "documentno")
    private java.lang.String documentno;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "educationallevel")
    private java.lang.String educationallevel;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "employmentstatus")
    private java.lang.String employmentstatus;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_firstname")
    private java.lang.String nokFirstname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_lastname")
    private java.lang.String nokLastname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_middlename")
    private java.lang.String nokMiddlename;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_address1")
    private java.lang.String nokAddress1;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_address2")
    private java.lang.String nokAddress2;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_lga")
    private java.lang.String nokLga;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_state")
    private java.lang.String nokState;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_town")
    private java.lang.String nokTown;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nok_postalcode")
    private java.lang.String nokPostalcode;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "othername")
    private java.lang.String othername;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "pfirstname")
    private java.lang.String pfirstname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "photo")
    private java.lang.String photo;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "pmiddlename")
    private java.lang.String pmiddlename;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "psurname")
    private java.lang.String psurname;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nspokenlang")
    private java.lang.String nspokenlang;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "ospokenlang")
    private java.lang.String ospokenlang;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "religion")
    private java.lang.String religion;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residence_Town")
    private java.lang.String residenceTown;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residence_lga")
    private java.lang.String residenceLga;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residence_state")
    private java.lang.String residenceState;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residencestatus")
    private java.lang.String residencestatus;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residence_AddressLine1")
    private java.lang.String residenceAddressLine1;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "residence_AddressLine2")
    private java.lang.String residenceAddressLine2;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "self_origin_lga")
    private java.lang.String selfOriginLga;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "self_origin_place")
    private java.lang.String selfOriginPlace;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "self_origin_state")
    private java.lang.String selfOriginState;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "signature")
    private java.lang.String signature;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "nationality")
    private java.lang.String nationality;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "gender")
    private java.lang.String gender;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "trackingId")
    private java.lang.String trackingId;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String customer = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String uuid = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.dojah_inc.dojah_android_sdk.domain.responses.NinLookUpEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.String nin, @org.jetbrains.annotations.Nullable
    java.lang.String firstname, @org.jetbrains.annotations.Nullable
    java.lang.String firstName, @org.jetbrains.annotations.Nullable
    java.lang.String middlename, @org.jetbrains.annotations.Nullable
    java.lang.String middleName, @org.jetbrains.annotations.Nullable
    java.lang.String surname, @org.jetbrains.annotations.Nullable
    java.lang.String lastName, @org.jetbrains.annotations.Nullable
    java.lang.String maidenname, @org.jetbrains.annotations.Nullable
    java.lang.String telephoneno, @org.jetbrains.annotations.Nullable
    java.lang.String phoneNum, @org.jetbrains.annotations.Nullable
    java.lang.String state, @org.jetbrains.annotations.Nullable
    java.lang.String place, @org.jetbrains.annotations.Nullable
    java.lang.String profession, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String height, @org.jetbrains.annotations.Nullable
    java.lang.String email, @org.jetbrains.annotations.Nullable
    java.lang.String birthdate, @org.jetbrains.annotations.Nullable
    java.lang.String dateOfBirth, @org.jetbrains.annotations.Nullable
    java.lang.String birthstate, @org.jetbrains.annotations.Nullable
    java.lang.String birthcountry, @org.jetbrains.annotations.Nullable
    java.lang.String centralID, @org.jetbrains.annotations.Nullable
    java.lang.String documentno, @org.jetbrains.annotations.Nullable
    java.lang.String educationallevel, @org.jetbrains.annotations.Nullable
    java.lang.String employmentstatus, @org.jetbrains.annotations.Nullable
    java.lang.String nokFirstname, @org.jetbrains.annotations.Nullable
    java.lang.String nokLastname, @org.jetbrains.annotations.Nullable
    java.lang.String nokMiddlename, @org.jetbrains.annotations.Nullable
    java.lang.String nokAddress1, @org.jetbrains.annotations.Nullable
    java.lang.String nokAddress2, @org.jetbrains.annotations.Nullable
    java.lang.String nokLga, @org.jetbrains.annotations.Nullable
    java.lang.String nokState, @org.jetbrains.annotations.Nullable
    java.lang.String nokTown, @org.jetbrains.annotations.Nullable
    java.lang.String nokPostalcode, @org.jetbrains.annotations.Nullable
    java.lang.String othername, @org.jetbrains.annotations.Nullable
    java.lang.String pfirstname, @org.jetbrains.annotations.Nullable
    java.lang.String photo, @org.jetbrains.annotations.Nullable
    java.lang.String pmiddlename, @org.jetbrains.annotations.Nullable
    java.lang.String psurname, @org.jetbrains.annotations.Nullable
    java.lang.String nspokenlang, @org.jetbrains.annotations.Nullable
    java.lang.String ospokenlang, @org.jetbrains.annotations.Nullable
    java.lang.String religion, @org.jetbrains.annotations.Nullable
    java.lang.String residenceTown, @org.jetbrains.annotations.Nullable
    java.lang.String residenceLga, @org.jetbrains.annotations.Nullable
    java.lang.String residenceState, @org.jetbrains.annotations.Nullable
    java.lang.String residencestatus, @org.jetbrains.annotations.Nullable
    java.lang.String residenceAddressLine1, @org.jetbrains.annotations.Nullable
    java.lang.String residenceAddressLine2, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginLga, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginPlace, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginState, @org.jetbrains.annotations.Nullable
    java.lang.String signature, @org.jetbrains.annotations.Nullable
    java.lang.String nationality, @org.jetbrains.annotations.Nullable
    java.lang.String gender, @org.jetbrains.annotations.Nullable
    java.lang.String trackingId, @org.jetbrains.annotations.Nullable
    java.lang.String customer, @org.jetbrains.annotations.Nullable
    java.lang.String uuid) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public NinLookUpEntity() {
        super();
    }
    
    public NinLookUpEntity(@org.jetbrains.annotations.Nullable
    java.lang.String nin, @org.jetbrains.annotations.Nullable
    java.lang.String firstname, @org.jetbrains.annotations.Nullable
    java.lang.String firstName, @org.jetbrains.annotations.Nullable
    java.lang.String middlename, @org.jetbrains.annotations.Nullable
    java.lang.String middleName, @org.jetbrains.annotations.Nullable
    java.lang.String surname, @org.jetbrains.annotations.Nullable
    java.lang.String lastName, @org.jetbrains.annotations.Nullable
    java.lang.String maidenname, @org.jetbrains.annotations.Nullable
    java.lang.String telephoneno, @org.jetbrains.annotations.Nullable
    java.lang.String phoneNum, @org.jetbrains.annotations.Nullable
    java.lang.String state, @org.jetbrains.annotations.Nullable
    java.lang.String place, @org.jetbrains.annotations.Nullable
    java.lang.String profession, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String height, @org.jetbrains.annotations.Nullable
    java.lang.String email, @org.jetbrains.annotations.Nullable
    java.lang.String birthdate, @org.jetbrains.annotations.Nullable
    java.lang.String dateOfBirth, @org.jetbrains.annotations.Nullable
    java.lang.String birthstate, @org.jetbrains.annotations.Nullable
    java.lang.String birthcountry, @org.jetbrains.annotations.Nullable
    java.lang.String centralID, @org.jetbrains.annotations.Nullable
    java.lang.String documentno, @org.jetbrains.annotations.Nullable
    java.lang.String educationallevel, @org.jetbrains.annotations.Nullable
    java.lang.String employmentstatus, @org.jetbrains.annotations.Nullable
    java.lang.String nokFirstname, @org.jetbrains.annotations.Nullable
    java.lang.String nokLastname, @org.jetbrains.annotations.Nullable
    java.lang.String nokMiddlename, @org.jetbrains.annotations.Nullable
    java.lang.String nokAddress1, @org.jetbrains.annotations.Nullable
    java.lang.String nokAddress2, @org.jetbrains.annotations.Nullable
    java.lang.String nokLga, @org.jetbrains.annotations.Nullable
    java.lang.String nokState, @org.jetbrains.annotations.Nullable
    java.lang.String nokTown, @org.jetbrains.annotations.Nullable
    java.lang.String nokPostalcode, @org.jetbrains.annotations.Nullable
    java.lang.String othername, @org.jetbrains.annotations.Nullable
    java.lang.String pfirstname, @org.jetbrains.annotations.Nullable
    java.lang.String photo, @org.jetbrains.annotations.Nullable
    java.lang.String pmiddlename, @org.jetbrains.annotations.Nullable
    java.lang.String psurname, @org.jetbrains.annotations.Nullable
    java.lang.String nspokenlang, @org.jetbrains.annotations.Nullable
    java.lang.String ospokenlang, @org.jetbrains.annotations.Nullable
    java.lang.String religion, @org.jetbrains.annotations.Nullable
    java.lang.String residenceTown, @org.jetbrains.annotations.Nullable
    java.lang.String residenceLga, @org.jetbrains.annotations.Nullable
    java.lang.String residenceState, @org.jetbrains.annotations.Nullable
    java.lang.String residencestatus, @org.jetbrains.annotations.Nullable
    java.lang.String residenceAddressLine1, @org.jetbrains.annotations.Nullable
    java.lang.String residenceAddressLine2, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginLga, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginPlace, @org.jetbrains.annotations.Nullable
    java.lang.String selfOriginState, @org.jetbrains.annotations.Nullable
    java.lang.String signature, @org.jetbrains.annotations.Nullable
    java.lang.String nationality, @org.jetbrains.annotations.Nullable
    java.lang.String gender, @org.jetbrains.annotations.Nullable
    java.lang.String trackingId, @org.jetbrains.annotations.Nullable
    java.lang.String customer, @org.jetbrains.annotations.Nullable
    java.lang.String uuid) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNin() {
        return null;
    }
    
    public final void setNin(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFirstname() {
        return null;
    }
    
    public final void setFirstname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMiddlename() {
        return null;
    }
    
    public final void setMiddlename(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMiddleName() {
        return null;
    }
    
    public final void setMiddleName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSurname() {
        return null;
    }
    
    public final void setSurname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final void setLastName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMaidenname() {
        return null;
    }
    
    public final void setMaidenname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTelephoneno() {
        return null;
    }
    
    public final void setTelephoneno(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPhoneNum() {
        return null;
    }
    
    public final void setPhoneNum(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getState() {
        return null;
    }
    
    public final void setState(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPlace() {
        return null;
    }
    
    public final void setPlace(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getProfession() {
        return null;
    }
    
    public final void setProfession(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getHeight() {
        return null;
    }
    
    public final void setHeight(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBirthdate() {
        return null;
    }
    
    public final void setBirthdate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDateOfBirth() {
        return null;
    }
    
    public final void setDateOfBirth(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBirthstate() {
        return null;
    }
    
    public final void setBirthstate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBirthcountry() {
        return null;
    }
    
    public final void setBirthcountry(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCentralID() {
        return null;
    }
    
    public final void setCentralID(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDocumentno() {
        return null;
    }
    
    public final void setDocumentno(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEducationallevel() {
        return null;
    }
    
    public final void setEducationallevel(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEmploymentstatus() {
        return null;
    }
    
    public final void setEmploymentstatus(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokFirstname() {
        return null;
    }
    
    public final void setNokFirstname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokLastname() {
        return null;
    }
    
    public final void setNokLastname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokMiddlename() {
        return null;
    }
    
    public final void setNokMiddlename(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokAddress1() {
        return null;
    }
    
    public final void setNokAddress1(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokAddress2() {
        return null;
    }
    
    public final void setNokAddress2(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokLga() {
        return null;
    }
    
    public final void setNokLga(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component31() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokState() {
        return null;
    }
    
    public final void setNokState(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokTown() {
        return null;
    }
    
    public final void setNokTown(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNokPostalcode() {
        return null;
    }
    
    public final void setNokPostalcode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOthername() {
        return null;
    }
    
    public final void setOthername(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPfirstname() {
        return null;
    }
    
    public final void setPfirstname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPhoto() {
        return null;
    }
    
    public final void setPhoto(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component37() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPmiddlename() {
        return null;
    }
    
    public final void setPmiddlename(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component38() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPsurname() {
        return null;
    }
    
    public final void setPsurname(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component39() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNspokenlang() {
        return null;
    }
    
    public final void setNspokenlang(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOspokenlang() {
        return null;
    }
    
    public final void setOspokenlang(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component41() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReligion() {
        return null;
    }
    
    public final void setReligion(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component42() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidenceTown() {
        return null;
    }
    
    public final void setResidenceTown(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidenceLga() {
        return null;
    }
    
    public final void setResidenceLga(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component44() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidenceState() {
        return null;
    }
    
    public final void setResidenceState(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component45() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidencestatus() {
        return null;
    }
    
    public final void setResidencestatus(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component46() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidenceAddressLine1() {
        return null;
    }
    
    public final void setResidenceAddressLine1(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component47() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getResidenceAddressLine2() {
        return null;
    }
    
    public final void setResidenceAddressLine2(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component48() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSelfOriginLga() {
        return null;
    }
    
    public final void setSelfOriginLga(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component49() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSelfOriginPlace() {
        return null;
    }
    
    public final void setSelfOriginPlace(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component50() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSelfOriginState() {
        return null;
    }
    
    public final void setSelfOriginState(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component51() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSignature() {
        return null;
    }
    
    public final void setSignature(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component52() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNationality() {
        return null;
    }
    
    public final void setNationality(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component53() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getGender() {
        return null;
    }
    
    public final void setGender(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component54() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTrackingId() {
        return null;
    }
    
    public final void setTrackingId(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component55() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCustomer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component56() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUuid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getDob() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getFName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getMName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getLicenseNum() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getLName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getImage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.String getCustomerID() {
        return null;
    }
}