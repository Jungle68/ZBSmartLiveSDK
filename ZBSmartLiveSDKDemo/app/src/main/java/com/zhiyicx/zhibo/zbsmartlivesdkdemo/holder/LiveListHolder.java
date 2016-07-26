package com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity.LiveData;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.utils.GlideCircleBoundTrasform;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.utils.UiUtils;

import butterknife.Bind;

/**
 * Created by zhiyicx on 2016/3/31.
 */
public class LiveListHolder extends BaseHolder<LiveData> {
    @Nullable
    @Bind(R.id.tv_live_item_user_name)
    TextView mName;
    @Nullable
    @Bind(R.id.tv_live_item_user_count)
    TextView mUserCount;
    @Nullable
    @Bind(R.id.tv_live_item_title)
    TextView mTitle;
    @Nullable
    @Bind(R.id.tv_live_item_location)
    TextView mLocation;
    @Nullable
    @Bind(R.id.iv_live_item_cover)
    ImageView mCover;
    @Nullable
    @Bind(R.id.iv_live_item_user_icon)
    ImageView mIcon;
    @Nullable
    @Bind(R.id.iv_live_item_verified)
    ImageView mVerified;
    @Nullable
    @Bind(R.id.iv_live_item_location)
    ImageView mLocationIV;
    @Nullable
    @Bind(R.id.iv_live_item_black_cover)
    ImageView mBlackCoverIV;

    private LiveData mData;

    public LiveListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(LiveData data) {
        this.mData = data;
        mBlackCoverIV.setVisibility(View.GONE);//默认不显示黑色遮罩
//        mName.setText(data.user.uname);
        mTitle.setText(data.getStream().getTitle());
        mLocationIV.setVisibility(TextUtils.isEmpty(data.getStream().getLocation()) ? View.GONE : View.VISIBLE);
        mLocation.setText(data.getStream().getLocation());
        UiUtils.glideDisplayNotPlaceholder(data.getStream().getIcon().getValue0(), mCover);
        UiUtils.glideDisplayWithTrasform(data.getStream().getIcon().getValue0(), mIcon, new GlideCircleBoundTrasform(UiUtils.getContext()));
        if (data.getStream().getIcon().getValue0() == null) showBlackCover();//如果地址位空显示黑色遮罩


    }

    private void showBlackCover() {
        mBlackCoverIV.setVisibility(View.VISIBLE);
    }


}
