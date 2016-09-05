# ZBSmartLiveSDK 概述

ZBSmartLiveSDK 是一个适用于 Android 平台快速集成直播功能的 SDK.配合智播云后台可轻松同步迁移用户数据.
同时 ZBSDK 提供高度可定制化和自由调整的 UI.
ZBSmartLiveSDK 的特色是使用 Android Camera 画面捕获并进行 H.264 硬编码,以及支持 Android 麦克风音频采样进行 AAC 编码;同时,还能适应移动网络的多变性,实现智能切换最佳的视频采集,编码配置.
借助 ZBSmartLiveSDK 和智播云后台,用户可以在几小时内集成智播聊天核心功能至已有应用中.

## 功能特性

- [x]  应用权限验证
- [x]  智能账户迁移(登录,注册,自动登录,自动更新授权,更新用户信息)
- [x]  直播功能(摄像头采集,智能编码,上传推流)
- [x]  直播播放(拉留解码,弱网重连,在线聊天,赠送礼物等功能)
- [x]  在线回放
- [x]  多平台分享功能
- [x]  钱包功能(绑定/解绑/查询 提现账户)
- [x]  礼物功能(申请提现,赠送礼物,积分兑换)
- [x]  用户关系管理(关注/取消关注,用户认证等)

## 内容摘要

- [快速集成](#快速集成)
- [初始化SDK](#初始化SDK)
- [用户认证](#用户认证)
- [开启直播](#开启直播)
- [观看直播](#开启直播)
- [聊天](#聊天)
- [智播云通讯文档说明](https://github.com/Jungle68/ZBSmartLiveSDK/wiki/%E6%99%BA%E6%92%AD%E4%BA%91%E9%80%9A%E8%AE%AF%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E)

## SDK说明

SDK 提供了如下类(协议)和方法,点击类目查询详情

> [ZBSmartLiveSDK](http://www.baidu.com) 整个SDK的主入口
> 
> [ZBCloudApiClient]() 智播云通讯管理类
> 
> [ZBStreamingClient]() 视频采集编码的核心
>
> [ZBPlayClient]() 观看直播\或者回放
> 
> [ZBVideoView\ZBTextureView\ZBMediaPlayer]() 播放器核心(播放在线回放视频和直播)
> 
> [ZBInitConfigManager]() 配置信息管理类


## 快速集成
1. 将智播核心jar包导入到项目libs文件夹下,并将.so文件加入到main文件夹下面的jniLibs文件夹下
2. 在app下面的build.gradle 文件中加入第三方依赖包
```groovy
apply plugin: 'com.neenbedankt.android-apt'
buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}
dependencies {
    compile "io.reactivex:rxandroid:1.1.0"
    compile "io.reactivex:rxjava:1.1.0"
    compile "com.squareup.retrofit2:converter-gson:2.0.0-beta4"
    compile "com.squareup.retrofit2:adapter-rxjava:2.0.0-beta4"
    compile "com.squareup.retrofit2:retrofit:2.0.0-beta4"
    compile "com.google.dagger:dagger:2.0.2"
    apt 'com.google.dagger:dagger-compiler:2.0.2'
    provided 'javax.annotation:jsr250-api:1.0'
    compile  "com.android.support:support-annotations:23.0.0"
    compile "org.simple:androideventbus:1.0.5.1"
    compile 'com.qiniu:happy-dns:0.2.+'
    compile "com.squareup.okhttp3:okhttp:3.2.0"
}
```

## 申请Appkey
1.注册智播云账号，在后台申请获取 ZHIBO_APPID,ZHIBO_APPTOKEN
2.在```AndroidManifest.xml```的```application```标签下配置ZHIBO_APPID,ZHIBO_APPTOKEN
以及权限信息
```xml
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.WAKE_LOCK"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.READ_LOGS"/>
    <uses-permission android:name="android.permission.GET_TASKS"/>
    <uses-permission android:name="android.permission.MANAGE_ACCOUNTS"/>
    <uses-permission android:name="android.permission.GET_ACCOUNTS"/>

    <uses-feature android:name="android.hardware.camera.autofocus"/>
    <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true"/>

<!--zhibo-->
<meta-data
    android:name="ZHIBO_APPID"
    android:value="your appid"></meta-data>
<meta-data
    android:name="ZHIBO_APPTOKEN"
    android:value="your token"></meta-data>
```

初始化Appkey
## 初始化SDK
在应用Applicaiton中的onCreate()方法中进行初始化

```java
ZBSmartLiveSDK.init(ApplicationContext);
```


## 用户认证
登录智播云进行获取授权.获取到智播云口令后
Android端需要在<font style="color:red">拿到票据```ticket```后调用以下代码进行用户认证后，才可以使用智播的功能</font>

```java
  ZBInitConfigManager.vertifyToken(context,String ticket);ticket票据由直播云服务器提供给第三方服务器;
 /**
 *如果需要回调使用下面的
 */
 ZBInitConfigManager.vertifyToken(context,String , new OnVertifyTokenCallbackListener() {
            @Override
            public void onSuccess(ZBApiConfig zbApiConfig) {
            }
```
## 开启直播
1.在开启直播前需要对流信息的一个校验,校验成功后才可以开始直播，接口返回都是在UI线程
```java

  ZBStreamingClient.checkStrem(new OncheckSteamStatusListener() {
        @Override
        public void onStartCheck() {
        }
     
        @Override
        public void onSuccess() {
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

```
2.添加播放页核心布局文件
```xml
<com.zhiyicx.zhibosdk.widget.ZBAspectFrameLayout
    android:id="@+id/cameraPreview_afl"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_alignParentTop="true"
    android:layout_centerHorizontal="true">

    <android.opengl.GLSurfaceView
        android:id="@+id/cameraPreview_surfaceView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"/>
</com.zhiyicx.zhibosdk.widget.ZBAspectFrameLayout>
```
3.开启直播初始化准备
<font style='color:red;font-size:20px;'>★</font><font style='color:red;'>注：此方法涉及到ui加载，所以需要在Activity或者Fragment的onCreat（）方法中调用。并在相应的onResume、onPause、onDestroy方法中分别调用```     ZBStreamingClient.onResume();    ZBStreamingClient.onPause();    ZBStreamingClient.onDestroy();```</font><br/>

> 说明
>
>initListener();//初始化监听器，可选择设置

```java
 protected void onCreate(Bundle savedInstanceState) {
    zBasfl=(ZBAspectFrameLayout)findViewById(R.id.cameraPreview_afl);
    gsfv=(GLSurfaceView)findViewById(R.id.cameraPreview_surfaceView);
    try {
        mZBStreamingClient = ZBStreamingClient.getInstance();
        mZBStreamingClient.initConfig(getContext(),zBasfl,gsfv);
        } catch (JSONException e) {
            e.printStackTrace();
            mRootView.setWarnMessage("创建失败");
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            mRootView.setWarnMessage("校验流失败");
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
            UiUtils.makeText("网络状况不稳定~");
        }

        /**
         * 当前网络为数据网络
         */
        @Override
        public void onNetInData() {
            UiUtils.makeText(UiUtils.getString(R.string.str_not_wifi_prompt));
        }
    });

    mZBStreamingClient.setReconnetListener(new OnReconnetListener() {
        @Override
        public void reconnectStart() {
            mRootView.setPlaceHolderVisible(true);
            LogUtils.warnInfo(TAG, "reconnectStart.....");
        }

        @Override
        public void reconnectScuccess() {
            mRootView.setPlaceHolderVisible(false);
            LogUtils.warnInfo(TAG, "enreconnectScuccessd.....");
        }

        @Override
        public void reConnentFailure() {
            mRootView.setPlaceHolderVisible(false);
            LogUtils.warnInfo(TAG, "reConnentFailure.....");
        }
    });
    }
```
4.开启直播
> 参数说明
>
> title:直播标题 （可以为空）
>
> mLocation:直播地址，```'lat纬度,lng经度'```由("纬度+','+经度")合成的字符串 （可以为空）
>
> mCropfile: 直播封面图文件 （可以为空）
```java
mZBStreamingClient.startPlay(title, mLocation, mCropfile,new OnLiveStartPlayListener() {
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
    }
    /**
    *开始失败
    */
    @Override
    public void onStartFail() {
    }
        });
```

5.关闭直播
```java
ZBStreamingClient.getInstance().closePlay(new OnCloseStatusListener() {
    /**
    *返回本次直播数据信息（获取赞，金币等）
    */
    @Override
    public void onSuccess(EndStreamJson endStreamJson) {
    }
    @Override
    public void onError(Throwable throwable) {
    }
    @Override
    public void onFial(String code, String message) {
    }
});
```

## 观看直播
1.xml中添加播放器视图
```java
<com.zhiyicx.zhibosdk.widget.ZBVideoView
    android:id="@+id/zv_live_play_player"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
2.<font style='color:red'>初始化ZBPlayClent,在Activity或者Fragment的onResume、onPause、onDestroy方法中分别调用```     mZBPlayClient.onResume();    mZBPlayClient.onPause();    mZBPlayClient.onDestroy();```</font>
```java
ZBPlayClient mZBPlayClient=ZBPlayClient.getInstance();
```
3.开始播放
直播
> 参数说明
>
> zBVideoView : xml中的播放器视图
> 
> uid : 主播加密后的uid，通过查看直播列表获取
>
> streamId : 流id，通过查看直播列表获取
>
> OnVideoStartPlayListener :开始播放状态监听
>
```java
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
            }
        });
```

回放

> 参数说明
>
> zBVideoView : xml中的播放器视图
>
> vid : 视频id，通过查看回放列表获取

```java
mRootView.getZBplayClient().startVedio(zBVideoView, vid, new OnVideoStartPlayListener() {
    @Override
    public void onSuccess(ApiImInfo apiImInfo) {
    }
    @Override
    public void onFail(String code, String message) {
        mRootView.showWarn();
    }
    @Override
    public void onError(Throwable throwable) {
        mRootView.showMessage(UiUtils.getString("str_net_erro"));
    }
        });
```

## 聊天
### 1. 发送消息

1.1.发送文本消息
text文本内容
```java
   //主播端
   ZBStreamingClient.getInstance().sendTextMsg(String text);
   //观众端
   ZBPlayClient.getInstance().sendTextMsg(String text);
```
1.2.发送自定义[消息]
message类型为com.zhiyicx.imsdk.model.Message,[详情查看]()
```java
   //主播端
   ZBStreamingClient.getInstance().sendMessage(Message message);
   //观众端
   ZBPlayClient.getInstance().sendMessage(Message message);
```
### 2. 接收消息
2.1. 主播端
```java
  //主播端
  ZBStreamingClient.getInstance().setOnImListener(ImListener);
  ZBStreamingClient.getInstance().setOnImStatusListener(this);
  ZBStreamingClient.getInstance().setOnIMMessageTimeOutListener(this);
  //观众端
  ZBPlayClient.getInstance().setOnImListener(ImListener);
  ZBPlayClient.getInstance().setOnImStatusListener(this);
  ZBPlayClient.getInstance().setOnIMMessageTimeOutListener(this);
  
  
```
2.2. ImListener接口说明
```java
public interface OnImListener {
    /**
    *禁言通知：gag代表禁言到时长，0永久，大于0为自动解禁时间
    */
    void onBanned(long gag);
    /**
    * 消息接收到通知
    */
    void onMessageReceived(Message message);
    /**
    * 消息回执通知
    */
    void onMessageACK(Message message);
    /**
    * 聊天室人数查询回执通知
    */
    void onChatRoomDataCountReceived(ChatRoomDataCount chatRoomDataCount);
   /**
    * 直播间结束通知
    */
    void onConvrEnd(int cid);

}
/**
*消息超时
*/
public interface OnIMMessageTimeOutListener {
    void onMessageTimeout(Message message);
}
/**
*IM的状态监听
*/
public interface OnImStatusListener {

    void onConnected();

    void onDisconnect(int code, String reason);
}
```



## [智播云通讯文档说明](https://github.com/Jungle68/ZBSmartLiveSDK/wiki/%E6%99%BA%E6%92%AD%E4%BA%91%E9%80%9A%E8%AE%AF%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E)














