// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TextureViewActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.TextureViewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492957, "field 'zbTextureview'");
    target.zbTextureview = finder.castView(view, 2131492957, "field 'zbTextureview'");
    view = finder.findRequiredView(source, 2131492958, "field 'loding'");
    target.loding = finder.castView(view, 2131492958, "field 'loding'");
  }

  @Override public void unbind(T target) {
    target.zbTextureview = null;
    target.loding = null;
  }
}
