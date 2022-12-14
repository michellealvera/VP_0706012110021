// Generated by view binder compiler. Do not edit!
package com.vp.week4retrofit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.vp.week4retrofit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListGenresMovieDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cvListgenre;

  @NonNull
  public final LinearLayout linListgenre;

  @NonNull
  public final TextView tvListgenre;

  private ListGenresMovieDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull CardView cvListgenre, @NonNull LinearLayout linListgenre,
      @NonNull TextView tvListgenre) {
    this.rootView = rootView;
    this.cvListgenre = cvListgenre;
    this.linListgenre = linListgenre;
    this.tvListgenre = tvListgenre;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListGenresMovieDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListGenresMovieDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_genres_movie_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListGenresMovieDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cv_listgenre;
      CardView cvListgenre = ViewBindings.findChildViewById(rootView, id);
      if (cvListgenre == null) {
        break missingId;
      }

      id = R.id.lin_listgenre;
      LinearLayout linListgenre = ViewBindings.findChildViewById(rootView, id);
      if (linListgenre == null) {
        break missingId;
      }

      id = R.id.tv_listgenre;
      TextView tvListgenre = ViewBindings.findChildViewById(rootView, id);
      if (tvListgenre == null) {
        break missingId;
      }

      return new ListGenresMovieDetailBinding((ConstraintLayout) rootView, cvListgenre,
          linListgenre, tvListgenre);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
