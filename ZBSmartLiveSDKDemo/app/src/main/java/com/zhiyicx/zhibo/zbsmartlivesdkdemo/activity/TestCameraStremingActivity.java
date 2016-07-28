package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibosdk.manage.ZBStreamingClient;
import com.zhiyicx.zhibosdk.widget.ZBAspectFrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestCameraStremingActivity extends AppCompatActivity {


    @Bind(R.id.cameraPreview_surfaceView)
    GLSurfaceView cameraPreviewSurfaceView;
    @Bind(R.id.cameraPreview_afl)
    ZBAspectFrameLayout cameraPreviewAfl;
    @Bind(R.id.content)
    RelativeLayout content;
    private ZBStreamingClient mZBStreamingClient;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerastring);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            try {
                jsonObject = new JSONObject(getIntent().getStringExtra("jsonstring"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        mZBStreamingClient = new ZBStreamingClient(this, cameraPreviewAfl, cameraPreviewSurfaceView);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZBStreamingClient.onDestroy();
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        /**
         * 在mZBStreamingClient.onResume();之后调用
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
//                boolean isSuccess = mZBStreamingClient.startStreaming();
            }
        }).start();

    }
}
