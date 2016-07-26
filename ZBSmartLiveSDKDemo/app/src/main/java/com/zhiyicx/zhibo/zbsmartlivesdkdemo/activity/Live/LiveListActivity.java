package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity.LiveData;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.LiveListAdapter;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.MoreAdapter;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.SpaceItemGridDecoration;
import com.zhiyicx.zhibosdk.manage.ZBCloudApiClient;
import com.zhiyicx.zhibosdk.manage.listener.ZBCloudApiCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LiveListActivity extends AppCompatActivity {

    @Bind(R.id.rv_live)
    RecyclerView rvLive;
    private LiveListAdapter mAdapter;
    private List<LiveData> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_list);
        ButterKnife.bind(this);

        getLiveList();
    }

    /**
     * 获取直播间列表
     */
    private void getLiveList() {
        Map<String, Object> params = new HashMap<>();
        params.put("order", "airtime");
        params.put("p", 1);
        params.put("limit", 10);
        ZBCloudApiClient.getInstance().sendZBCloudApiRequest("ZBCloud_Get_LiveList", params, new ZBCloudApiCallback() {
            @Override
            public void onResponse(String response) {
                //json格式的字符串,自己根据自己的方案解析,现在只关心返回正确数据
                try {
                    JSONObject js = new JSONObject(response);
                    Gson gson = new Gson();
                    if (js.getString("code").equals("00000")) {
                        mData = gson.fromJson(js.getString("data"), new TypeToken<List<LiveData>>() {
                        }.getType());
                        if (mData == null || mData.size() == 0) {
                            Toast.makeText(LiveListActivity.this, "暂时没有直播！", Toast.LENGTH_LONG).show();
                            return;
                        }


                        mAdapter = new LiveListAdapter(mData);
                        rvLive.setLayoutManager(new GridLayoutManager(LiveListActivity.this, 2));

                        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
                        rvLive.setHasFixedSize(true);
                        rvLive.setItemAnimator(new DefaultItemAnimator());//设置动画
                        rvLive.addItemDecoration(new SpaceItemGridDecoration((int) 20));//设置Item的间隔

                        rvLive.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MoreAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(View view, Object data) {
                                Intent to = new Intent(LiveListActivity.this, WatchLiveActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(WatchLiveActivity.KEY_LIVEDATA, (LiveData) data);
                                to.putExtras(bundle);
                                startActivity(to);

                            }
                        });

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
