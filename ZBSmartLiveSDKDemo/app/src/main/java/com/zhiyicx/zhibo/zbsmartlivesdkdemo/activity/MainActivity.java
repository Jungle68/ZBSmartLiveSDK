package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live.LiveListActivity;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity.Live.StartLiveActivity;
import com.zhiyicx.zhibosdk.manage.ZBInitConfigManager;
import com.zhiyicx.zhibosdk.manage.ZBStreamingClient;
import com.zhiyicx.zhibosdk.manage.listener.OncheckSteamStatusListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String BUNDLE_URL = "bundle_url";
    private static final String DEFAULT_TEST_URL = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    private static final String KEY_TICJET = "y-4aDnvfhDoxiGtuCdhv7b9w2IU";
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.VideoPathEdit)
    EditText VideoPathEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ZBInitConfigManager.vertifyToken(getApplicationContext(), KEY_TICJET);//ticket票据由直播云服务器提供给第三方服务器;
        VideoPathEdit.setText(DEFAULT_TEST_URL);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onClick(View view) {
        Intent to = null;
        switch (view.getId()) {


            case R.id.button:
                to = new Intent(MainActivity.this, LiveListActivity.class);

                break;
            case R.id.button2:

                checkStartLive();
                return;

            case R.id.button3:
                to = new Intent(MainActivity.this, TextureViewActivity.class);
                break;
        }
        to.putExtra(BUNDLE_URL, VideoPathEdit.getText().toString().trim());
        startActivity(to);
    }

    private void checkStartLive() {
        ZBStreamingClient.checkStrem(new OncheckSteamStatusListener() {
            @Override
            public void onStartCheck() {

            }

            /**
             * ApiIminfo里面有IM消息的房间id（cid）用户[聊天](#聊天)
             */
            @Override
            public void onSuccess() {

                Intent toMedia = new Intent(MainActivity.this, StartLiveActivity.class);

                startActivity(toMedia);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            /**
             * 错误码和错误原因
             */
            @Override
            public void onFial(String code, String message) {

            }

            /**
             *如果被禁播，则返回解禁的时间戳
             */
            @Override
            public void onDisable(String time) {

            }
        });
    }

    public void onClickLocalFile(View v) {
        Intent intent = new Intent(this, VideoFileActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        String videoPath = data.getStringExtra("videoPath");
        VideoPathEdit.setText(videoPath, TextView.BufferType.EDITABLE);
    }
}
