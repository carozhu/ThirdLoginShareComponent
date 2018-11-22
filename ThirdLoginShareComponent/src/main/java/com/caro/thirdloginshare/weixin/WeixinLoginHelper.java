package com.caro.thirdloginshare.weixin;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信登录Helper
 */
public class WeixinLoginHelper {
    private String appid = null;
    private String secret = null;
    private WeixinUserInfoListener mListener;
    private IWXAPI api;
    private boolean blnInstallWx;

    private static class WeixinLoginHelperHolder{
        private static final WeixinLoginHelper INSTANCE = new WeixinLoginHelper();
    }

    public static WeixinLoginHelper getInstance(){
        return WeixinLoginHelperHolder.INSTANCE;
    }

    /**
     * 初始化微信
     * @param context
     * @param mAppid
     * @param mSecret
     * @return
     */
    public WeixinLoginHelper regToWeixin(Context context, String mAppid, String mSecret){
        appid = mAppid;
        secret = mSecret;
        api = WXAPIFactory.createWXAPI(context, appid, true);
        api.registerApp(appid);
        //api.getWXAppSupportAPI(); && api.isWXAppSupportAPI()
        blnInstallWx = api.isWXAppInstalled();

        return this;
    }

    /**
     * return blnInstallWx
     * @param blnInstallWx
     * @return
     */
    public WeixinLoginHelper getWxSupport(boolean blnInstallWx){

        return this;
    }


    /**
     * 注册消息回调
     * @param mListener
     * @return
     */
    public WeixinLoginHelper regCallBackListener(WeixinUserInfoListener mListener){
        this.mListener = mListener;
        return this;
    }

    /**
     * 微信授权登陆
     */
    public WeixinLoginHelper accreditSign() {
        if (mListener == null) {
            throw new RuntimeException("User Info Can not be null!!！，please first regCallBackListener");
        }

        if (!blnInstallWx){
            mListener.notSupportWx();
        }else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
        return this;
    }

    public  IWXAPI getApi() {
        /* if (api == null){
            throw new RuntimeException("please fisrt regToWeixin");
        }*/
        return api;
    }

    public String getAppid() {
        if (appid == null){
            throw new RuntimeException("please fisrt regToWeixin");
        }
        return appid;
    }

    public String getSecret() {
        if (secret == null){
            throw new RuntimeException("please fisrt regToWeixin");
        }
        return secret;
    }

    public WeixinUserInfoListener getmListener() {
        if (mListener == null){
            throw new RuntimeException("please fisrt regToWeixin and regCallBackListener");
        }
        return mListener;
    }
}
