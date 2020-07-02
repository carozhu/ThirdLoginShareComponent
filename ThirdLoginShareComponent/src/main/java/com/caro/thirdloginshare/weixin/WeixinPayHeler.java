package com.caro.thirdloginshare.weixin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  :
 */
public class WeixinPayHeler {
    private IWXAPI weiXinApi = null;
    private String WEIXIN_APP_ID =  null;
    private Context context;
    private WeixinPayListener weixinPayListener;

    private static class WeixinPayHelerHolder {
        public final static WeixinPayHeler INSTANCE = new WeixinPayHeler();
    }


    public static WeixinPayHeler getInstance(){
        return WeixinPayHelerHolder.INSTANCE;
    }

    public IWXAPI getWeiXinApi() {
        if (weiXinApi == null){
            createWXAPI();
        }
        return weiXinApi;
    }

    public WeixinPayHeler configContext(Context context) {
        this.context = context;
        return this;
    }

    public WeixinPayHeler setWeixinAppID(String WEIXIN_APP_ID) {
        this.WEIXIN_APP_ID = WEIXIN_APP_ID;
        return this;
    }

    public WeixinPayHeler createWXAPI() {
        //微信初始化
        weiXinApi = WXAPIFactory.createWXAPI(context, WEIXIN_APP_ID, true);
        weiXinApi.registerApp(WEIXIN_APP_ID);
        return this;
    }

    public WeixinPayHeler setWeixinShareListener(WeixinPayListener weixinPayListener){
        this.weixinPayListener =weixinPayListener;
        return this;
    }

    public WeixinPayListener getWeixinPayListener(){

        return weixinPayListener;
    }

    /**
     * 发起微信支付
     * @param partnerid 微信支付分配的商户号
     * @param prepayid 微信返回的支付交易会话ID
     * @param noncestr
     * @param timestamp
     * @param packageValue default use packageValue = "Sign=WXPay";
     * @param sign 签名
     *
     *
     * 更多调用支付接口参数说明请阅读官方API文档说明
     *
     */
    public void weixinPay(String partnerid,String prepayid,String noncestr,String timestamp,String sign,String packageValue){
        weiXinApi = getWeiXinApi();
        if (!weiXinApi.isWXAppInstalled()) {
            weixinPayListener.uninstallWeixin();
            return;
        }
        //微信官方支付
        PayReq req = new PayReq();
        req.appId = WEIXIN_APP_ID;//微信开放平台审核通过的应用APPID
        req.partnerId = partnerid ;
        req.prepayId = prepayid ;
        req.nonceStr = noncestr;
        req.timeStamp = timestamp ;
        req.packageValue = packageValue;
        req.sign = sign;
        req.extData = "app data";//extData you maybe use  extData = "app data";
        weiXinApi.sendReq(req);

    }
}
