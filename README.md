### 实现QQ 微信 第三方登录分享；微信支付宝支付。以满足大部分APP集成登录、分享需求[跳过微信等包名限制] ,达到模块化开发。


### API使用 # ThirdLoginShareComponent
>简单两步，开始使用。（不需要在manifest中添加依赖Activity）

#### STEP 1

```text
在你的工程中引入
implementation 'com.carozhu:ThirdLoginShare-annotation:1.1.0'
implementation 'com.carozhu:ThirdLoginShareComponent:1.1.0'
annotationProcessor 'com.carozhu:ThirdLoginShare-compiler:1.1.0'
```

#### STEP 2
>在你的app module 的 配置 QQ_APPID [如果不配置、会导致complie出错找不到QQ_APPID]
```groovy
android {
  .......
   defaultConfig {
    ......
     manifestPlaceholders = [
          QQ_APPID : "101461768"
     ]
  
   }
}

```

#### 微信支付
``` java
        WeixinPayHeler.getInstance().configContext(context)
                .setWeixinAppID(WEIXIN_APP_ID)
                .createWXAPI()
                .setWeixinShareListener(new WeixinPayListener() {
                    @Override
                    public void uninstallWeixin() {
                        Toast.makeText(context,"uninstallWeixin",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void weixinpaySuccess(BaseResp baseResp) {
                        Toast.makeText(context,"weixinpaySuccess",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void weixinpayError(BaseResp baseResp) {
                        Toast.makeText(context,"weixinpayError: Tyep = "+baseResp.getType() + " code = "+baseResp.errCode,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void weixinpayCancel(BaseResp baseResp) {
                        Toast.makeText(context,"weixinpayCancel",Toast.LENGTH_LONG).show();
                    }
                }).weixinPay("1","1","1","1","1","1");
                
        更多调用支付接口参数说明请阅读官方API文档说明 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2               
```

#### 支付宝支付
```text
TODO
```

#### 微信登录
``` java
     WeixinLoginHelper.getInstance()
                .regToWeixin(context, WEIXIN_APP_ID, WEIXIN_SECRET)
                .regCallBackListener(new WeixinUserInfoListener() {
                    @Override
                    public void notSupportWx() {

                    }

                    @Override
                    public void loginSuccess() {

                    }

                    @Override
                    public void loginCancel() {

                    }

                    @Override
                    public void loginFailed(IOException e) {

                    }

                    @Override
                    public void loginDenied() {

                    }

                    @Override
                    public void getUserInfoSuc(String data) {
                        Toast.makeText(context, "用户信息" + data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getUserInfoError(IOException e) {

                    }
                })
                .accreditSign();
```

#### QQ登录
```java
       QQNotifyMessageManager.getInstance().registNotifyMessage(new QQNotifyMessage() {
            @Override
            public void qqLogining() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录中", Toast.LENGTH_LONG).show();
            }

            @Override
            public void qqLoginFailed() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void unInstallQQ() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "未安装QQ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void qqLoginFailed(UiError uiError) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录失败 ：code " + uiError.errorCode + " msg: " + uiError.errorMessage, Toast.LENGTH_LONG).show();

            }

            @Override
            public void qqLoginOnCancel() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录取消", Toast.LENGTH_LONG).show();

            }

            @Override
            public void getQQUserInfoFailed() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取QQ用户信息失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void getQQUserInfoSuccess(QQLoginInfo qqLoginInfo) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取用户信息成功 ：" + qqLoginInfo.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void getQQUserInfoOnError(UiError uiError) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取用户信息失败OnError ：code " + uiError.errorCode + " msg: " + uiError.errorMessage, Toast.LENGTH_LONG).show();

            }

            @Override
            public void getQQUserInfoOnCancel() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取QQ信息取消", Toast.LENGTH_LONG).show();
            }
        });
        QQLoginActivity.startQQLoginActivity(WeixinQQLoginSimpleActivity.this, "your QQ APPID");
```

#### QQ好友分享
```java
        QQHelper.getInstance()
                .configContext(WeixinQQShareSimpleActivity.this)
                .configQQAPPID(QQ_APP_ID)
                .createTencent()
                .setShareCallbackListener(new QQShareListener() {
                    @Override
                    public void shareComplete(Object o) {

                        Toast.makeText(context,"QQ分享成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareError(UiError e) {
                        Toast.makeText(context,"QQ分享失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context,"QQ分享取消",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallQQClient() {
                        Toast.makeText(context,"未安装QQ客户端",Toast.LENGTH_SHORT).show();
                    }
                }).shareToQQ("分享Title", "分享Content", "http://www.baidu.com", "http://img.72qq.com/file/201701/03/30561e836a.jpg", QQHelper.shareQQFridenType);

```

#### QQ空间分享
```java
        QQHelper.getInstance()
                .configContext(WeixinQQShareSimpleActivity.this)
                .configQQAPPID(QQ_APP_ID)
                .createTencent()
                .setShareCallbackListener(new QQShareListener() {
                    @Override
                    public void shareComplete(Object o) {
                        Toast.makeText(context,"QQ分享成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareError(UiError e) {
                        Toast.makeText(context,"QQ分享失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context,"QQ分享取消",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallQQClient() {
                        Toast.makeText(context,"未安装QQ客户端",Toast.LENGTH_SHORT).show();
                    }
                }).shareToQQ("分享Title", "分享Content", "http://www.baidu.com", "http://img.72qq.com/file/201701/03/30561e836a.jpg", QQHelper.shareQQZoneType);

```

#### 微信好友分享
```java
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        WeixinShareHeler.getInstance().configContext(context).setWeixinAppID(WEIXIN_APP_ID).createWXAPI()
                .setWeixinShareListener(new WeixinShareListener() {
                    @Override
                    public void shareSuccess(BaseResp baseResp) {
                        Toast.makeText(context,"微信分享成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallWeixin() {
                        Toast.makeText(context,"未安装微信",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context,"微信分享取消",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareDecline() {
                        Toast.makeText(context,"微信分享拒绝",Toast.LENGTH_SHORT).show();
                    }
                }).shareToweixin(context,shareWeixinFridenType,"Title","Contro","http://vs.zsyj.com.cn/info/recharge/shape.php",thumb);
```

#### 微信朋友圈分享

```java
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        WeixinShareHeler.getInstance().configContext(context).setWeixinAppID(WEIXIN_APP_ID).createWXAPI()
                .setWeixinShareListener(new WeixinShareListener() {
                    @Override
                    public void shareSuccess(BaseResp baseResp) {
                        Toast.makeText(context,"微信分享成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallWeixin() {
                        Toast.makeText(context,"未安装微信",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context,"微信分享取消",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareDecline() {
                        Toast.makeText(context,"微信分享拒绝",Toast.LENGTH_SHORT).show();
                    }
                }).shareToweixin(context,shareWeixinCircle,"Title","Contro","http://vs.zsyj.com.cn/info/recharge/shape.php",thumb);
```

#### simple-master
>已有simple-master，但过两天开放出来给各位大大调试使用 [目前demo中存在关键的一些公司信息，故稍等去掉后再贡献出来]

### 附录

#### 微信code码
```text
110201     未登陆
110405     登录请求被限制
110404     请求参数缺少appid
110401     请求的应用不存在
110407     应用已经下架
110406     应用没有通过审核     //没有通过审核的应用只能被授权创建应用的QQ账号登录
100044     错误的sign
110500     获取用户授权信息失败
110501     获取应用的授权信息失败
110502     设置用户授权失败
110503     获取token失败
110504     系统内部错误

```

#### 腾讯开放平台接入
[Android_SDK使用说明](http://wiki.open.qq.com/wiki/mobile/Android_SDK%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)

[API调用说明](http://wiki.open.qq.com/wiki/mobile/API%E8%B0%83%E7%94%A8%E8%AF%B4%E6%98%8E)


#### 参考
[Manifest配置动态替换](https://1022-zhang.github.io/manifest-configuration-dynamic-replacement.html)

[Manifest配置动态替换](https://1022-zhang.github.io/manifest-configuration-dynamic-replacement.html)

[Java注解解析-搭建自己的注解处理器（CLASS注解使用篇)](https://juejin.im/post/5b8f53355188255c520cecf1?utm_source=gold_browser_extension)

[APT动态生成代码的实际应用场景](https://www.jianshu.com/p/23c8ab5f2de6)

[JavaPoet的使用指南](https://juejin.im/post/5bc96b63e51d450e4369ba19)


#### 第三方分享推荐
[利用 Android 系统原生 API 实现分享功能 - GitHub](https://github.com/baishixian/Share2)

[利用 Android 系统原生 API 实现分享功能 - 掘金](https://juejin.im/post/5af00b4e51882567183f0b65)

SocialHelper https://github.com/arvinljw/SocialHelper

#### 第三方支付推荐
[RxPay](https://github.com/Cuieney/RxPay)

#### 第三方分享推荐
[SocialHelper](https://github.com/arvinljw/SocialHelper)

#### 如果项目中还有Rxjava版本1的话为了防止代码冲突 请在build.gradle里面添加一下代码

```
 packagingOptions {
        exclude 'META-INF/rxjava.properties'
 }
```

#### QAQ
1：java.lang.ClassNotFoundException: com.tencent.stat.StatConfig
请确保sdk接入正确
若已经可以跳转到拉起权限页面，查看QQ互联是否ok https://connect.qq.com/manage.html#/


#### TODO 
```text
1：支付宝登录
2：微信分享成功与否的回调问题[目前存分享没问题]
```