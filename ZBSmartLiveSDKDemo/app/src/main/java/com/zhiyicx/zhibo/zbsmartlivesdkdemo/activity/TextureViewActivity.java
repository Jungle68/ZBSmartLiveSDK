package com.zhiyicx.zhibo.zbsmartlivesdkdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibosdk.widget.ZBMediaPlayer;
import com.zhiyicx.zhibosdk.widget.ZBTextureView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextureViewActivity extends AppCompatActivity implements
        ZBMediaPlayer.OnPreparedListener,
        ZBMediaPlayer.OnInfoListener,
        ZBMediaPlayer.OnCompletionListener,
        ZBMediaPlayer.OnVideoSizeChangedListener,
        ZBMediaPlayer.OnErrorListener {
    private boolean mIsActivityPaused = true;
    private Toast mToast = null;
    @Bind(R.id.zb_textureview)
    ZBTextureView zbTextureview;
    @Bind(R.id.loding)
    TextView loding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_view);
        ButterKnife.bind(this);

        zbTextureview.setBufferingIndicator(loding);
        String path=getIntent().getStringExtra(MainActivity.BUNDLE_URL);
        zbTextureview.setVideoPath(path);

        MediaController mediaController = new MediaController(getApplicationContext(), false, isLiveStreaming(path));
        zbTextureview.setMediaController(mediaController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused=false;
        zbTextureview.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zbTextureview.pause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zbTextureview.stopPlayback();
    }

    @Override
    public void onCompletion() {

    }

    @Override
    public boolean onError(int errorCode) {
        return false;
    }

    @Override
    public boolean onInfo(int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared() {

    }

    @Override
    public void onVideoSizeChanged(int width, int height) {

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
                mToast = Toast.makeText(TextureViewActivity.this, tips, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }
}
