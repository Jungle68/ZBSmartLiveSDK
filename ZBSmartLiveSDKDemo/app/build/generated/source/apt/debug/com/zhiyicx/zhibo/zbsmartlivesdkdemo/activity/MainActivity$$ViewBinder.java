// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492950, "field 'textView'");
    target.textView = finder.castView(view, 2131492950, "field 'textView'");
    view = finder.findRequiredView(source, 2131492952, "field 'button' and method 'onClick'");
    target.button = finder.castView(view, 2131492952, "field 'button'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492953, "field 'button2' and method 'onClick'");
    target.button2 = finder.castView(view, 2131492953, "field 'button2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492954, "field 'button3' and method 'onClick'");
    target.button3 = finder.castView(view, 2131492954, "field 'button3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492951, "field 'VideoPathEdit'");
    target.VideoPathEdit = finder.castView(view, 2131492951, "field 'VideoPathEdit'");
  }

  @Override public void unbind(T target) {
    target.textView = null;
    target.button = null;
    target.button2 = null;
    target.button3 = null;
    target.VideoPathEdit = null;
  }
}
