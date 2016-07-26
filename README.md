# ZBSmartLiveSDK 概述

ZBSmartLiveSDK 是一个适用于 iOS 平台快速集成直播功能的 SDK.配合智播云后台可轻松同步迁移用户数据.
同时 ZBSDK 提供高度可定制化和自由调整的 UI.
ZBSmartLiveSDK 的特色是使用 IOS Camera 画面捕获并进行 H.264 硬编码,以及支持 IOS 麦克风音频采样进行 AAC 编码;同时,还能适应移动网络的多变性,实现智能切换最佳的视频采集,编码配置.
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
- [用户登录](#用户登录)
- [获取直播列表](#获取直播列表)
- [开启直播功能](#开启直播功能)
- [收发即时通讯文本消息](#收发即时通讯文本消息)
- [基础播放器功能](#基础播放器功能)

## SDK说明

SDK 提供了如下类(协议)和方法,点击类目查询详情

> [ZBSmartLiveSDK]() 整个SDK的主入口
> 
> [ZBUserInfoManager]() 整个SDK的用户信息管理
> 
> [ZBLiveListManager]() 直播列表管理(列表,搜索,过滤等)
> 
> [ZBLoginManager]() 登陆管理类,负责登陆,注销,自动登陆等
> 
> [ZBChatManager]() 聊天管理类,负责收发消息
> 
> [ZBRecordKit]() 视频采集编码的核心
> 
> [ZBPlayKit]() 播放器核心(播放在线回放视频和直播)
> 
> [ZBCoinManager]() 积分,金币,充值等管理

## 快速集成
你可以通过 CocoaPods 自动集成该 SDK 

- 在 PodFile 文件中加入

```shell
pod 'ZBSmartLiveSDK'
```

- 安装 CocoaPods 依赖

```shell
pod install
```

## 初始化SDK

注册智播云后台,申请获取 APPKEY 在应用初始化时添加初始化方法

```shell
-(BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {

      [[ZBSmartLiveSDK sharedSDK] registerWithAppID:您的APPKEY];
      return YES;
  }
```

## 用户登录

登录智播云进行获取授权.获取到智播云口令后
iOS端直接调用登录接口即可,智播云会自动与三方服务器对接实现智能迁移用户数据.

```shell
[[ZBLoginManager sharedManager] loginWithaZBToken:服务器获取到的智播云口令];
```


## 获取直播列表

```shell
[ZBLiveListManager getLiveList sucessCallBack:^(LiveListModel * liveListModel) {
	// liveListModel 是详细的直播列表数据
} failCallBack:^(NSString *err) {
	// err 是获取直播列表时产生的错误
}];
```

## 开启直播功能


## 收发即时通讯文本消息

## 基础播放器功能













