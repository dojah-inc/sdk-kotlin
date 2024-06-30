// Generated by view binder compiler. Do not edit!
package com.dojah_inc.dojah_android_sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.dojah_inc.dojah_android_sdk.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class WidgetSpinnerCountryBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final TextView countryName;

  @NonNull
  public final ImageView imageCountry;

  @NonNull
  public final LinearLayout layoutRoot;

  @NonNull
  public final RelativeLayout layoutSpinner;

  @NonNull
  public final TextView textError;

  private WidgetSpinnerCountryBinding(@NonNull View rootView, @NonNull TextView countryName,
      @NonNull ImageView imageCountry, @NonNull LinearLayout layoutRoot,
      @NonNull RelativeLayout layoutSpinner, @NonNull TextView textError) {
    this.rootView = rootView;
    this.countryName = countryName;
    this.imageCountry = imageCountry;
    this.layoutRoot = layoutRoot;
    this.layoutSpinner = layoutSpinner;
    this.textError = textError;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static WidgetSpinnerCountryBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    if (parent == null) {
      throw new NullPointerException("parent");
    }
    inflater.inflate(R.layout.widget_spinner_country, parent);
    return bind(parent);
  }

  @NonNull
  public static WidgetSpinnerCountryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.country_name;
      TextView countryName = ViewBindings.findChildViewById(rootView, id);
      if (countryName == null) {
        break missingId;
      }

      id = R.id.image_country;
      ImageView imageCountry = ViewBindings.findChildViewById(rootView, id);
      if (imageCountry == null) {
        break missingId;
      }

      id = R.id.layout_root;
      LinearLayout layoutRoot = ViewBindings.findChildViewById(rootView, id);
      if (layoutRoot == null) {
        break missingId;
      }

      id = R.id.layout_spinner;
      RelativeLayout layoutSpinner = ViewBindings.findChildViewById(rootView, id);
      if (layoutSpinner == null) {
        break missingId;
      }

      id = R.id.text_error;
      TextView textError = ViewBindings.findChildViewById(rootView, id);
      if (textError == null) {
        break missingId;
      }

      return new WidgetSpinnerCountryBinding(rootView, countryName, imageCountry, layoutRoot,
          layoutSpinner, textError);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}