// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VideoViewPlayerActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.VideoViewPlayerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492960, "field 'zbVideoview'");
    target.zbVideoview = finder.castView(view, 2131492960, "field 'zbVideoview'");
    view = finder.findRequiredView(source, 2131492958, "field 'loding'");
    target.loding = finder.castView(view, 2131492958, "field 'loding'");
  }

  @Override public void unbind(T target) {
    target.zbVideoview = null;
    target.loding = null;
  }
}
