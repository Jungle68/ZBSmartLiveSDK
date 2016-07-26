// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WatchLiveActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live.WatchLiveActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492961, "field 'zvLivePlayPlayer'");
    target.zvLivePlayPlayer = finder.castView(view, 2131492961, "field 'zvLivePlayPlayer'");
    view = finder.findRequiredView(source, 2131492955, "field 'lvChat'");
    target.lvChat = finder.castView(view, 2131492955, "field 'lvChat'");
    view = finder.findRequiredView(source, 2131492963, "field 'etSendMsg'");
    target.etSendMsg = finder.castView(view, 2131492963, "field 'etSendMsg'");
    view = finder.findRequiredView(source, 2131492964, "field 'btSend' and method 'onClick'");
    target.btSend = finder.castView(view, 2131492964, "field 'btSend'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131492962, "field 'llSendMsg'");
    target.llSendMsg = finder.castView(view, 2131492962, "field 'llSendMsg'");
  }

  @Override public void unbind(T target) {
    target.zvLivePlayPlayer = null;
    target.lvChat = null;
    target.etSendMsg = null;
    target.btSend = null;
    target.llSendMsg = null;
  }
}
