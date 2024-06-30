// Generated by view binder compiler. Do not edit!
package com.dojah_inc.dojah_android_sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.dojah_inc.dojah_android_sdk.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;

public final class WidgetDojahButtonBinding implements ViewBinding {
  @NonNull
  private final MaterialButton rootView;

  private WidgetDojahButtonBinding(@NonNull MaterialButton rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public MaterialButton getRoot() {
    return rootView;
  }

  @NonNull
  public static WidgetDojahButtonBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WidgetDojahButtonBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.widget_dojah_button, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WidgetDojahButtonBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new WidgetDojahButtonBinding((MaterialButton) rootView);
  }
}