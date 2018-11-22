package com.caro.thirdloginshare.QQ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

import static com.caro.thirdloginshare.util.SystemUtil.isHaveApp;

/**
 * Author: carozhu
 * Date  : On 2018/8/3
 * Desc  :
 */
public class QQHelper {
    //QQ包名
    final String QQ_PACKAGENAME = "com.tencent.mobileqq";
    Context context;
    Tencent mTencent = null;
    String qq_appid;
    QQShareListener qqShareListener;
    public static final int shareQQFridenType = 1;
    public static final int shareQQZoneType = 2;

    private static class QQHelperHolder {
        public final static QQHelper INSTANCE = new QQHelper();
    }


    public static QQHelper getInstance(){
        return QQHelperHolder.INSTANCE;
    }

    public QQHelper configContext(Context context) {
        this.context = context;
        return this;
    }

    public QQHelper configQQAPPID(String qq_appid) {
        this.qq_appid = qq_appid;
        return this;
    }

    public QQHelper createTencent() {
        mTencent = Tencent.createInstance(qq_appid, context);
        return this;
    }

    /**
     * 设置Callback
     *
     * @param qqShareListener
     * @return
     */
    public QQHelper setShareCallbackListener(QQShareListener qqShareListener) {
        this.qqShareListener = qqShareListener;
        return this;
    }


    /**
     * @param title
     * @param content
     * @param url
     * @param shareIconUrl
     * @param shareType    1:分享到朋友; 2:分享到空间，
     */
    public void shareToQQ(String title, String content, String url, String shareIconUrl, int shareType) {
        if (!isHaveApp(context, QQ_PACKAGENAME)) {
            qqShareListener.uninstallQQClient();
            return;
        }

        Bundle bundle = new Bundle();
        // 这条分享消息被好友点击后的跳转URL。
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        // 分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_ SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        // 分享的图片URL
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareIconUrl);
        // 分享的消息摘要，最长50个字
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
        // 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        // bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "我在测试_appname");
        // 标识该消息的来源应用，值为应用名称+AppId。
        // bundle.putString(QQShare.SHARE_TO_QQ_KEY_TYPE, "星期几");

        switch (shareType) {
            case shareQQFridenType:
                mTencent.shareToQQ((Activity) context, bundle, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        qqShareListener.shareComplete(o);
                    }

                    @Override
                    public void onError(UiError e) {
                        qqShareListener.shareError(e);
                    }

                    @Override
                    public void onCancel() {
                        qqShareListener.shareCancel();
                    }
                });
                break;
            case shareQQZoneType:
                bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
                //下面这个必须加上  不然无法调动 qq空间
                ArrayList<String> imageUrls = new ArrayList<String>();
                imageUrls.add(shareIconUrl);
                bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
                mTencent.shareToQzone((Activity) context, bundle, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        qqShareListener.shareComplete(o);
                    }

                    @Override
                    public void onError(UiError e) {
                        qqShareListener.shareError(e);
                    }

                    @Override
                    public void onCancel() {
                        qqShareListener.shareCancel();
                    }
                });
                break;
        }

    }


    /**
     * 注销QQ登录
     */
    public void logout(){
        // TODO:

    }


}
