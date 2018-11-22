package com.caro.thirdloginshare.weixin;

import android.content.Context;
import android.graphics.Bitmap;


import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  :
 */
public class WeixinShareHeler {
    private IWXAPI weiXinApi = null;
    public static final int shareWeixinFridenType = 1;
    public static final int shareWeixinCircle = 2;
    private String WEIXIN_APP_ID =  null;
    private WeixinShareListener weixinShareListener;
    private Context context;

    private static class WeixinShareHelerHolder {
        public final static WeixinShareHeler INSTANCE = new WeixinShareHeler();
    }


    public static WeixinShareHeler getInstance(){
        return WeixinShareHelerHolder.INSTANCE;
    }

    public IWXAPI getWeiXinApi() {
        return weiXinApi;
    }

    public WeixinShareHeler configContext(Context context) {
        this.context = context;
        return this;
    }

    public WeixinShareHeler setWeixinAppID(String WEIXIN_APP_ID) {
        this.WEIXIN_APP_ID = WEIXIN_APP_ID;
        return this;
    }

    public WeixinShareHeler createWXAPI() {
        //微信初始化
        weiXinApi = WXAPIFactory.createWXAPI(context, WEIXIN_APP_ID, true);
        weiXinApi.registerApp(WEIXIN_APP_ID);
        return this;
    }

    public WeixinShareHeler setWeixinShareListener(WeixinShareListener weixinShareListener){
        this.weixinShareListener =weixinShareListener;
        return this;
    }

    public WeixinShareListener getWeixinShareListener(){

        return weixinShareListener;
    }

    /**
     * 分享到微信
     *
     * @param flag 0=分享给朋友，1=分享到朋友圈
     */
    public  void shareToweixin(Context context, int flag, String title, String description, String url, Bitmap app_con) {
        if (!weiXinApi.isWXAppInstalled()) {
            weixinShareListener.uninstallWeixin();
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);

        msg.title = title;
        msg.description = description;
        msg.setThumbImage(app_con);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        switch (flag) {
            case shareWeixinFridenType:
                req.scene = WXSceneSession;
                break;
            case shareWeixinCircle:
                req.scene = WXSceneTimeline;
                break;
        }
        weiXinApi.sendReq(req);
    }
}
