// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StartLiveActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live.StartLiveActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492947, "field 'cameraPreviewSurfaceView'");
    target.cameraPreviewSurfaceView = finder.castView(view, 2131492947, "field 'cameraPreviewSurfaceView'");
    view = finder.findRequiredView(source, 2131492946, "field 'cameraPreviewAfl'");
    target.cameraPreviewAfl = finder.castView(view, 2131492946, "field 'cameraPreviewAfl'");
    view = finder.findRequiredView(source, 2131492956, "field 'etTilte'");
    target.etTilte = finder.castView(view, 2131492956, "field 'etTilte'");
    view = finder.findRequiredView(source, 2131492948, "field 'btStart' and method 'onClick'");
    target.btStart = finder.castView(view, 2131492948, "field 'btStart'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131492955, "field 'lvChat'");
    target.lvChat = finder.castView(view, 2131492955, "field 'lvChat'");
  }

  @Override public void unbind(T target) {
    target.cameraPreviewSurfaceView = null;
    target.cameraPreviewAfl = null;
    target.etTilte = null;
    target.btStart = null;
    target.lvChat = null;
  }
}
