// Generated by view binder compiler. Do not edit!
package com.dojah_inc.dojah_android_sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.dojah_inc.dojah_android_sdk.R;
import com.dojah_inc.dojah_android_sdk.ui.utils.widget.DojahMaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentGovDataBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final DojahMaterialButton btnContinue;

  @NonNull
  public final AppCompatTextView errorTag;

  @NonNull
  public final LinearLayout infoTag;

  @NonNull
  public final TextView infoText;

  @NonNull
  public final TextInputLayout inputVerifyWith;

  @NonNull
  public final TextView policyTv;

  @NonNull
  public final AutoCompleteTextView spinnerTextGid;

  @NonNull
  public final AutoCompleteTextView spinnerVerifyWith;

  @NonNull
  public final TextView textBvn;

  @NonNull
  public final TextInputEditText textEdtBvn;

  @NonNull
  public final TextView textGovId;

  @NonNull
  public final TextInputLayout textInputBvn;

  @NonNull
  public final TextInputLayout textInputGid;

  @NonNull
  public final TextView textVerifyWith;

  private FragmentGovDataBinding(@NonNull NestedScrollView rootView,
      @NonNull DojahMaterialButton btnContinue, @NonNull AppCompatTextView errorTag,
      @NonNull LinearLayout infoTag, @NonNull TextView infoText,
      @NonNull TextInputLayout inputVerifyWith, @NonNull TextView policyTv,
      @NonNull AutoCompleteTextView spinnerTextGid, @NonNull AutoCompleteTextView spinnerVerifyWith,
      @NonNull TextView textBvn, @NonNull TextInputEditText textEdtBvn, @NonNull TextView textGovId,
      @NonNull TextInputLayout textInputBvn, @NonNull TextInputLayout textInputGid,
      @NonNull TextView textVerifyWith) {
    this.rootView = rootView;
    this.btnContinue = btnContinue;
    this.errorTag = errorTag;
    this.infoTag = infoTag;
    this.infoText = infoText;
    this.inputVerifyWith = inputVerifyWith;
    this.policyTv = policyTv;
    this.spinnerTextGid = spinnerTextGid;
    this.spinnerVerifyWith = spinnerVerifyWith;
    this.textBvn = textBvn;
    this.textEdtBvn = textEdtBvn;
    this.textGovId = textGovId;
    this.textInputBvn = textInputBvn;
    this.textInputGid = textInputGid;
    this.textVerifyWith = textVerifyWith;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentGovDataBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentGovDataBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_gov_data, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentGovDataBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_continue;
      DojahMaterialButton btnContinue = ViewBindings.findChildViewById(rootView, id);
      if (btnContinue == null) {
        break missingId;
      }

      id = R.id.error_tag;
      AppCompatTextView errorTag = ViewBindings.findChildViewById(rootView, id);
      if (errorTag == null) {
        break missingId;
      }

      id = R.id.info_tag;
      LinearLayout infoTag = ViewBindings.findChildViewById(rootView, id);
      if (infoTag == null) {
        break missingId;
      }

      id = R.id.info_text;
      TextView infoText = ViewBindings.findChildViewById(rootView, id);
      if (infoText == null) {
        break missingId;
      }

      id = R.id.input_verify_with;
      TextInputLayout inputVerifyWith = ViewBindings.findChildViewById(rootView, id);
      if (inputVerifyWith == null) {
        break missingId;
      }

      id = R.id.policy_tv;
      TextView policyTv = ViewBindings.findChildViewById(rootView, id);
      if (policyTv == null) {
        break missingId;
      }

      id = R.id.spinner_text_gid;
      AutoCompleteTextView spinnerTextGid = ViewBindings.findChildViewById(rootView, id);
      if (spinnerTextGid == null) {
        break missingId;
      }

      id = R.id.spinner_verify_with;
      AutoCompleteTextView spinnerVerifyWith = ViewBindings.findChildViewById(rootView, id);
      if (spinnerVerifyWith == null) {
        break missingId;
      }

      id = R.id.text_bvn;
      TextView textBvn = ViewBindings.findChildViewById(rootView, id);
      if (textBvn == null) {
        break missingId;
      }

      id = R.id.text_edt_bvn;
      TextInputEditText textEdtBvn = ViewBindings.findChildViewById(rootView, id);
      if (textEdtBvn == null) {
        break missingId;
      }

      id = R.id.text_gov_id;
      TextView textGovId = ViewBindings.findChildViewById(rootView, id);
      if (textGovId == null) {
        break missingId;
      }

      id = R.id.text_input_bvn;
      TextInputLayout textInputBvn = ViewBindings.findChildViewById(rootView, id);
      if (textInputBvn == null) {
        break missingId;
      }

      id = R.id.text_input_gid;
      TextInputLayout textInputGid = ViewBindings.findChildViewById(rootView, id);
      if (textInputGid == null) {
        break missingId;
      }

      id = R.id.text_verify_with;
      TextView textVerifyWith = ViewBindings.findChildViewById(rootView, id);
      if (textVerifyWith == null) {
        break missingId;
      }

      return new FragmentGovDataBinding((NestedScrollView) rootView, btnContinue, errorTag, infoTag,
          infoText, inputVerifyWith, policyTv, spinnerTextGid, spinnerVerifyWith, textBvn,
          textEdtBvn, textGovId, textInputBvn, textInputGid, textVerifyWith);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}