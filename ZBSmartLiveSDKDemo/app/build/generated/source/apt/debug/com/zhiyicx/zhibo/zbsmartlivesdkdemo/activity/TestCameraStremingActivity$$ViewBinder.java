// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TestCameraStremingActivity$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.TestCameraStremingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492947, "field 'cameraPreviewSurfaceView'");
    target.cameraPreviewSurfaceView = finder.castView(view, 2131492947, "field 'cameraPreviewSurfaceView'");
    view = finder.findRequiredView(source, 2131492946, "field 'cameraPreviewAfl'");
    target.cameraPreviewAfl = finder.castView(view, 2131492946, "field 'cameraPreviewAfl'");
    view = finder.findRequiredView(source, 2131492945, "field 'content'");
    target.content = finder.castView(view, 2131492945, "field 'content'");
    view = finder.findRequiredView(source, 2131492948, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.cameraPreviewSurfaceView = null;
    target.cameraPreviewAfl = null;
    target.content = null;
  }
}
