package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibosdk.widget.ZBMediaPlayer;
import com.zhiyicx.zhibosdk.widget.ZBVideoView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoViewPlayerActivity extends AppCompatActivity implements
        ZBMediaPlayer.OnPreparedListener,
        ZBMediaPlayer.OnInfoListener,
        ZBMediaPlayer.OnCompletionListener,
        ZBMediaPlayer.OnVideoSizeChangedListener,
        ZBMediaPlayer.OnErrorListener {

    private boolean mIsActivityPaused = true;
    private Toast mToast = null;
    @Bind(R.id.zb_videoview)
    ZBVideoView zbVideoview;
    @Bind(R.id.loding)
    TextView loding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_player);
        ButterKnife.bind(this);
        String path = getIntent().getStringExtra(MainActivity.BUNDLE_URL);

        MediaController mediaController = new MediaController(this, false, false);

        /**
         * 分享被点击
         */
        mediaController.setOnShareClickListener(new MediaController.OnShareClickListener() {
            @Override
            public void onShareClick(View v) {
                showToastTips("onShareClick");
            }
        });
        /**
         * 控制条显示ideoview.start();
         */
        mediaController.setOnShownListener(new MediaController.OnShownListener() {
            @Override
            public void onShown() {
                showToastTips("onShown");
            }
        });
        /**
         * 控制条隐藏
         */
        mediaController.setOnHiddenListener(new MediaController.OnHiddenListener() {
            @Override
            public void onHidden() {
                showToastTips("onHidden");
            }
        });
        /**
         * 播放和暂停
         */
        mediaController.setOnPlayClickListener(new MediaController.OnPlayClickListener() {
            @Override
            public void onPlayClick(View v) {
                showToastTips("onPlayClick");
            }
        });

        zbVideoview.setBufferingIndicator(loding);
        zbVideoview.setMediaController(mediaController);
        zbVideoview.setVideoPath(path);
        zbVideoview.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;

    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
        zbVideoview.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zbVideoview.stopPlayback();

    }

    @Override
    public void onCompletion() {
        showToastTips("onCompletion");
    }

    @Override
    public boolean onError(int errorCode) {

        showToastTips("err=" + errorCode);
        return false;
    }

    @Override
    public boolean onInfo(int what, int extra) {
        showToastTips("onInfo---what=" + what + "----extra=" + extra);

        return false;
    }

    @Override
    public void onPrepared() {
        showToastTips("onPrepared");
    }

    @Override
    public void onVideoSizeChanged(int width, int height) {
        showToastTips("width=" + width + "----height" + height);
    }

    private boolean isLiveStreaming(String url) {
        if (url.startsWith("rtmp://")
                || (url.startsWith("http://") && url.endsWith(".m3u8"))
                || (url.startsWith("http://") && url.endsWith(".flv"))) {
            return true;
        }
        return false;
    }

    private void showToastTips(final String tips) {
        if (mIsActivityPaused) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(VideoViewPlayerActivity.this, tips, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }
}
