package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.zhiyicx.imsdk.entity.ChatRoomContainer;
import com.zhiyicx.imsdk.entity.Conver;
import com.zhiyicx.imsdk.entity.Message;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter.NormalRecyclerViewAdapter;
import com.zhiyicx.zhibosdk.manage.ZBStreamingClient;
import com.zhiyicx.zhibosdk.manage.listener.OnCloseStatusListener;
import com.zhiyicx.zhibosdk.manage.listener.OnImListener;
import com.zhiyicx.zhibosdk.manage.listener.OnLiveStartPlayListener;
import com.zhiyicx.zhibosdk.model.entity.EndStreamJson;
import com.zhiyicx.zhibosdk.policy.OnNetworkJitterListener;
import com.zhiyicx.zhibosdk.policy.OnReconnetListener;
import com.zhiyicx.zhibosdk.widget.ZBAspectFrameLayout;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartLiveActivity extends Activity {

    @Bind(R.id.cameraPreview_surfaceView)
    GLSurfaceView cameraPreviewSurfaceView;
    @Bind(R.id.cameraPreview_afl)
    ZBAspectFrameLayout cameraPreviewAfl;
    @Bind(R.id.et_tilte)
    EditText etTilte;
    @Bind(R.id.bt_start)
    Button btStart;
    @Bind(R.id.lv_chat)
    RecyclerView lvChat;
    private ZBStreamingClient mZBStreamingClient;
    private NormalRecyclerViewAdapter mAdapter;
    private List<Message> mMessages = new ArrayList<>();//消息队列


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_live);
        ButterKnife.bind(this);
        initStream();
        initListview();
    }

    private void initListview() {
        mAdapter = new NormalRecyclerViewAdapter(this, mMessages);
        lvChat.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        lvChat.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZBStreamingClient.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mZBStreamingClient.onPause();
    }

    private void initStream() {
        try {
            mZBStreamingClient = ZBStreamingClient.getInstance();
            mZBStreamingClient.initConfig(getApplicationContext(), cameraPreviewAfl, cameraPreviewSurfaceView);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "创建失败", Toast.LENGTH_SHORT).show();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Toast.makeText(this, "校验流失败", Toast.LENGTH_SHORT).show();
        }
        initListener();//初始化监听器，可选择
    }

    private void initListener() {
        mZBStreamingClient.setNetworkJitterListener(new OnNetworkJitterListener() {
            /**
             * 当前网络不太稳定
             */
            @Override
            public void onNetworkJitter() {
                Toast.makeText(StartLiveActivity.this, "网络状况不稳定", Toast.LENGTH_SHORT).show();
            }

            /**
             * 当前网络为数据网络
             */
            @Override
            public void onNetInData() {
                Toast.makeText(StartLiveActivity.this, "当我没有在wifi环境下", Toast.LENGTH_SHORT).show();
            }
        });

        mZBStreamingClient.setReconnetListener(new OnReconnetListener() {
            @Override
            public void reconnectStart() {
                System.out.println("reconnectStart.....");
            }

            @Override
            public void reconnectScuccess() {
                System.out.println("reconnectScuccess.....");
            }

            @Override
            public void reConnentFailure() {
                System.out.println("reConnentFailure.....");
            }
        });
        mZBStreamingClient.setOnImListener(new OnImListener() {
            @Override
            public void onBanned(long gag) {

            }

            @Override
            public void onMessageReceived(Message message) {
                System.out.println("message = " + message.toString());
                if (!TextUtils.isEmpty(message.text)) ;
                {
                    mMessages.add(message);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMessageACK(Message message) {

            }

            @Override
            public void onMcReceived(ChatRoomContainer chatRoomContainer) {

            }

            @Override
            public void onConvrEnd(Conver conver) {
                Toast.makeText(StartLiveActivity.this,"直播结束了",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }


    private void startLive() {
        String str_tilte = etTilte.getText().toString().trim();
        String mLocation = "";
        File mCropfile = null;
        mZBStreamingClient.startPlay(str_tilte, mLocation, mCropfile, new OnLiveStartPlayListener() {
            /**
             *开启准备之前
             */
            @Override
            public void onStartPre() {
            }

            /**
             *准备完成
             */
            @Override
            public void onStartReady() {
            }

            /**
             *开启成功
             */
            @Override
            public void onStartSuccess() {
                etTilte.setVisibility(View.GONE);
                btStart.setVisibility(View.GONE);
            }

            /**
             *开始失败
             */
            @Override
            public void onStartFail() {
                System.out.println("onStartFail = ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZBStreamingClient.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeLive();
    }

    private void closeLive() {
        ZBStreamingClient.getInstance().closePlay(new OnCloseStatusListener() {
            /**
             *返回本次直播数据信息（获取赞，金币等）
             */
            @Override
            public void onSuccess(EndStreamJson endStreamJson) {
                System.out.println(" =------close  onSuccess------ ");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onFial(String code, String message) {
                System.out.println(" =------close  code------ " + code);
            }
        });
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        startLive();
    }


}
