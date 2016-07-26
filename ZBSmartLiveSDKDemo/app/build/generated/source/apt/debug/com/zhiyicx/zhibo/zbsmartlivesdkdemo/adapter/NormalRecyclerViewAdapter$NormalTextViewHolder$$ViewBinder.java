// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NormalRecyclerViewAdapter$NormalTextViewHolder$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.NormalRecyclerViewAdapter.NormalTextViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492968, "field 'mTextView'");
    target.mTextView = finder.castView(view, 2131492968, "field 'mTextView'");
  }

  @Override public void unbind(T target) {
    target.mTextView = null;
  }
}
