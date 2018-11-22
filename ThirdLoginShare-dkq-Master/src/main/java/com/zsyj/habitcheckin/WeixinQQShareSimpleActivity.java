package com.zsyj.habitcheckin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.caro.thirdloginshare.QQ.QQHelper;
import com.caro.thirdloginshare.QQ.QQShareListener;
import com.caro.thirdloginshare.weixin.BaseWXEntryActivity;
import com.caro.thirdloginshare.weixin.WeixinShareHeler;
import com.caro.thirdloginshare.weixin.WeixinShareListener;
import com.carozhu.WXEntryAnnotation;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.tauth.UiError;

import butterknife.OnClick;

import static com.tencent.connect.common.Constants.QQ_APPID;
import static com.zsyj.habitcheckin.Const.WEIXIN_APP_ID;


/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  : 微信QQ分享测试
 */
@WXEntryAnnotation(packageName = "com.zsyj.habitcheckin", superClass = BaseWXEntryActivity.class)
public class WeixinQQShareSimpleActivity extends BaseButterknifeActivity {
    Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_weixin_qq_share;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        context = this;
    }

    @OnClick(R.id.qq_friend_share)
    public void qq_friend_share() {
        QQHelper.getInstance()
                .configContext(WeixinQQShareSimpleActivity.this)
                .configQQAPPID(QQ_APPID)
                .createTencent()
                .setShareCallbackListener(new QQShareListener() {
                    @Override
                    public void shareComplete(Object o) {

                        Toast.makeText(context, "QQ分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareError(UiError e) {
                        Toast.makeText(context, "QQ分享失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context, "QQ分享取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallQQClient() {
                        Toast.makeText(context, "未安装QQ客户端", Toast.LENGTH_SHORT).show();
                    }
                }).shareToQQ("分享Title", "分享Content", "http://www.baidu.com", "http://img.72qq.com/file/201701/03/30561e836a.jpg", QQHelper.shareQQFridenType);
    }

    @OnClick(R.id.qq_zone_share)
    public void qq_zone_share() {
        QQHelper.getInstance()
                .configContext(WeixinQQShareSimpleActivity.this)
                .configQQAPPID(QQ_APPID)
                .createTencent()
                .setShareCallbackListener(new QQShareListener() {
                    @Override
                    public void shareComplete(Object o) {
                        Toast.makeText(context, "QQ分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareError(UiError e) {
                        Toast.makeText(context, "QQ分享失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context, "QQ分享取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallQQClient() {
                        Toast.makeText(context, "未安装QQ客户端", Toast.LENGTH_SHORT).show();
                    }
                }).shareToQQ("分享Title", "分享Content", "http://www.baidu.com", "http://img.72qq.com/file/201701/03/30561e836a.jpg", QQHelper.shareQQZoneType);

    }

    @OnClick(R.id.weixin_friend_share)
    public void weixin_friend_share() {
        //app icon
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        WeixinShareHeler.getInstance().configContext(context).setWeixinAppID(WEIXIN_APP_ID).createWXAPI()
                .setWeixinShareListener(new WeixinShareListener() {
                    @Override
                    public void shareSuccess(BaseResp baseResp) {
                        Toast.makeText(context, "微信分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallWeixin() {
                        Toast.makeText(context, "未安装微信", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context, "微信分享取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareDecline() {
                        Toast.makeText(context, "微信分享拒绝", Toast.LENGTH_SHORT).show();
                    }
                }).shareToweixin(context, 0, "Title", "Contro", "http://vs.zsyj.com.cn/info/recharge/shape.php", thumb);
    }

    @OnClick(R.id.weixin_circle_share)
    public void weixin_circle_share() {
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        WeixinShareHeler.getInstance().configContext(context).setWeixinAppID(WEIXIN_APP_ID).createWXAPI()
                .setWeixinShareListener(new WeixinShareListener() {
                    @Override
                    public void shareSuccess(BaseResp baseResp) {
                        Toast.makeText(context, "微信分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void uninstallWeixin() {
                        Toast.makeText(context, "未安装微信", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareCancel() {
                        Toast.makeText(context, "微信分享取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void shareDecline() {
                        Toast.makeText(context, "微信分享拒绝", Toast.LENGTH_SHORT).show();
                    }
                }).shareToweixin(context, 1, "Title", "Contro", "http://vs.zsyj.com.cn/info/recharge/shape.php", thumb);
    }
}
