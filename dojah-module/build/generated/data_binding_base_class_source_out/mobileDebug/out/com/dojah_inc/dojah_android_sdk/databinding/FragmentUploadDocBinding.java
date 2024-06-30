// Generated by view binder compiler. Do not edit!
package com.dojah_inc.dojah_android_sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.dojah_inc.dojah_android_sdk.R;
import com.dojah_inc.dojah_android_sdk.ui.utils.widget.LoadingButton;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentUploadDocBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final MaterialButton btnCapture;

  @NonNull
  public final LoadingButton btnUpload;

  @NonNull
  public final ImageView docPreview;

  @NonNull
  public final RelativeLayout layoutUpload;

  @NonNull
  public final AppCompatTextView pdfNameTv;

  @NonNull
  public final TextView textDocument;

  @NonNull
  public final TextView textTitle;

  private FragmentUploadDocBinding(@NonNull NestedScrollView rootView,
      @NonNull MaterialButton btnCapture, @NonNull LoadingButton btnUpload,
      @NonNull ImageView docPreview, @NonNull RelativeLayout layoutUpload,
      @NonNull AppCompatTextView pdfNameTv, @NonNull TextView textDocument,
      @NonNull TextView textTitle) {
    this.rootView = rootView;
    this.btnCapture = btnCapture;
    this.btnUpload = btnUpload;
    this.docPreview = docPreview;
    this.layoutUpload = layoutUpload;
    this.pdfNameTv = pdfNameTv;
    this.textDocument = textDocument;
    this.textTitle = textTitle;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentUploadDocBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentUploadDocBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_upload_doc, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentUploadDocBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_capture;
      MaterialButton btnCapture = ViewBindings.findChildViewById(rootView, id);
      if (btnCapture == null) {
        break missingId;
      }

      id = R.id.btn_upload;
      LoadingButton btnUpload = ViewBindings.findChildViewById(rootView, id);
      if (btnUpload == null) {
        break missingId;
      }

      id = R.id.doc_preview;
      ImageView docPreview = ViewBindings.findChildViewById(rootView, id);
      if (docPreview == null) {
        break missingId;
      }

      id = R.id.layout_upload;
      RelativeLayout layoutUpload = ViewBindings.findChildViewById(rootView, id);
      if (layoutUpload == null) {
        break missingId;
      }

      id = R.id.pdf_name_tv;
      AppCompatTextView pdfNameTv = ViewBindings.findChildViewById(rootView, id);
      if (pdfNameTv == null) {
        break missingId;
      }

      id = R.id.text_document;
      TextView textDocument = ViewBindings.findChildViewById(rootView, id);
      if (textDocument == null) {
        break missingId;
      }

      id = R.id.text_title;
      TextView textTitle = ViewBindings.findChildViewById(rootView, id);
      if (textTitle == null) {
        break missingId;
      }

      return new FragmentUploadDocBinding((NestedScrollView) rootView, btnCapture, btnUpload,
          docPreview, layoutUpload, pdfNameTv, textDocument, textTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
