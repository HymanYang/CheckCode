## 背景

>小米发布平台通知，APP首次运行，用户同意隐私政策前，存在私自收集/使用”设备IMEI、IMSI、APP_LIST、SSID、WIFI_NAME、MAC、IP 信息”其中行为。
>
>[请您依据工信部信管函〔2020〕164号](http://www.gov.cn/zhengce/zhengceku/2020-08/02/content_5531975.htm)，完成上述问题的自查和修改。

解决该问题，我们需要把App首次启动时所有会调用获取IMEI等违反隐私政策的方法，放在同意隐私权限后再初始化。但是App启动时会默认初始化几十个库&第三方服务，我们需要确定，初始化之前有哪些代码调用了IMEI代码。

通过网上查阅得知，**Xposed** 【大佬[rovo89](https://github.com/rovo89)开发的框架】能够解决当前这种情况。



### 什么是Xposed?

Xposed框架是一款可以在不修改APK的情况下影响程序运行(修改系统)的框架服务，基于它可以制作出许多功能强大的模块，且在功能不冲突的情况下同时运作。Xposed理论上能够hook到系统任意一个Java进程，由于是从底层hook，所以需要root权限，并且每次更新都要重新启动。

----

## 问题

xpose 需要Root权限手机


## Whale
Whale内部Xpose思路,快速多手机支持，代码调用排除
支持Android5.0.0 ~ 12.0.0




## 参考

https://juejin.cn/post/7146038116321591303/

https://github.com/jyy208/whale