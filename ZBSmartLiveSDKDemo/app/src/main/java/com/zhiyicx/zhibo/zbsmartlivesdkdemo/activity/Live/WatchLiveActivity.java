package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhiyicx.imsdk.entity.ChatRoomContainer;
import com.zhiyicx.imsdk.entity.Conver;
import com.zhiyicx.imsdk.entity.Message;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity.LiveData;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.NormalRecyclerViewAdapter;
import com.zhiyicx.zhibosdk.manage.ZBPlayClient;
import com.zhiyicx.zhibosdk.manage.listener.OnImListener;
import com.zhiyicx.zhibosdk.manage.listener.OnVideoStartPlayListener;
import com.zhiyicx.zhibosdk.model.entity.ApiPlay;
import com.zhiyicx.zhibosdk.widget.ZBVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WatchLiveActivity extends AppCompatActivity {
    public static final String KEY_LIVEDATA = "LIVEDATA";
    @Bind(R.id.zv_live_play_player)
    ZBVideoView zvLivePlayPlayer;
    @Bind(R.id.lv_chat)
    RecyclerView lvChat;
    @Bind(R.id.et_send_msg)
    EditText etSendMsg;
    @Bind(R.id.bt_send)
    Button btSend;
    @Bind(R.id.ll_send_msg)
    LinearLayout llSendMsg;
    private ZBPlayClient mZBPlayClient;
    private LiveData mData;

    private NormalRecyclerViewAdapter mAdapter;
    private List<Message> mMessages = new ArrayList<>();//消息队列

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_live);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null)
            mData = getIntent().getExtras().getParcelable(KEY_LIVEDATA);
        initPalyer();
        initListview();
        initImRecivier();

    }

    private void initListview() {
        mAdapter = new NormalRecyclerViewAdapter(this, mMessages);
        lvChat.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        lvChat.setAdapter(mAdapter);
    }

    private void initImRecivier() {
        ZBPlayClient.getInstance().setOnImListener(new OnImListener() {
            @Override
            public void onBanned(long l) {
                Toast.makeText(WatchLiveActivity.this, "您被禁言了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMessageReceived(Message message) {
                mMessages.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onMessageACK(Message message) {

            }

            @Override
            public void onMcReceived(ChatRoomContainer chatRoomContainer) {

            }

            @Override
            public void onConvrEnd(Conver conver) {
                Toast.makeText(WatchLiveActivity.this, "直播结束了", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void initPalyer() {
        if (mData == null) return;
        mZBPlayClient = ZBPlayClient.getInstance();
        mZBPlayClient.startLive(zvLivePlayPlayer, mData.getUser().getUid(), mData.getStream().getId(), new OnVideoStartPlayListener() {
            /**
             *观看直播连接建立成功，回放此回调返回数据为空
             */
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(String code, String message) {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            /**
             *直播已经结束回调，回放此回调无效
             */
            @Override
            public void onLiveEnd(ApiPlay apiPlay, String uid) {
                Toast.makeText(getApplicationContext(), "主播关闭了了直播！", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mZBPlayClient.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mZBPlayClient.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZBPlayClient.onDestroy();
    }

    @OnClick(R.id.bt_send)
    public void onClick() {
        String msg = etSendMsg.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(getApplicationContext(), "请输入内容！", Toast.LENGTH_SHORT).show();
            return;
        }
        ZBPlayClient.getInstance().sendTextMsg(msg);
        Message message = new Message();
        message.text = msg;
        mMessages.add(message);
        mAdapter.notifyDataSetChanged();

    }
}
