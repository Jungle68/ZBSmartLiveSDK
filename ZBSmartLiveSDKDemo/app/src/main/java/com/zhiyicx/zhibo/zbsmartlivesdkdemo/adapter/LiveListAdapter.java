package com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter;

import android.view.View;

import com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity.LiveData;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.BaseHolder;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.LiveListHolder;

import java.util.List;

/**
 * Created by zhiyicx on 2016/3/31.
 */
public class LiveListAdapter extends MoreAdapter<LiveData> {


    public LiveListAdapter(List<LiveData> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<LiveData> getHolder(View v) {
        return new LiveListHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycle_item_live;
    }


}
