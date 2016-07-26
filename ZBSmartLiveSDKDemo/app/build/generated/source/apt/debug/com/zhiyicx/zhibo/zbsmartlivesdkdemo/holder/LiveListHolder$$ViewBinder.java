// Generated code from Butter Knife. Do not modify!
package com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LiveListHolder$$ViewBinder<T extends com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.LiveListHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findOptionalView(source, 2131492995, null);
    target.mName = finder.castView(view, 2131492995, "field 'mName'");
    view = finder.findOptionalView(source, 2131492993, null);
    target.mUserCount = finder.castView(view, 2131492993, "field 'mUserCount'");
    view = finder.findOptionalView(source, 2131492994, null);
    target.mTitle = finder.castView(view, 2131492994, "field 'mTitle'");
    view = finder.findOptionalView(source, 2131492999, null);
    target.mLocation = finder.castView(view, 2131492999, "field 'mLocation'");
    view = finder.findOptionalView(source, 2131492990, null);
    target.mCover = finder.castView(view, 2131492990, "field 'mCover'");
    view = finder.findOptionalView(source, 2131492996, null);
    target.mIcon = finder.castView(view, 2131492996, "field 'mIcon'");
    view = finder.findOptionalView(source, 2131492997, null);
    target.mVerified = finder.castView(view, 2131492997, "field 'mVerified'");
    view = finder.findOptionalView(source, 2131492998, null);
    target.mLocationIV = finder.castView(view, 2131492998, "field 'mLocationIV'");
    view = finder.findOptionalView(source, 2131492991, null);
    target.mBlackCoverIV = finder.castView(view, 2131492991, "field 'mBlackCoverIV'");
  }

  @Override public void unbind(T target) {
    target.mName = null;
    target.mUserCount = null;
    target.mTitle = null;
    target.mLocation = null;
    target.mCover = null;
    target.mIcon = null;
    target.mVerified = null;
    target.mLocationIV = null;
    target.mBlackCoverIV = null;
  }
}
