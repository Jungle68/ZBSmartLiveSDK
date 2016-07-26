// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LiveListActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live.LiveListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492949, "field 'rvLive'");
    target.rvLive = finder.castView(view, 2131492949, "field 'rvLive'");
  }

  @Override public void unbind(T target) {
    target.rvLive = null;
  }
}
