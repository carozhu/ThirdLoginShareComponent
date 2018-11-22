package com.caro.thirdloginshare.weixin;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 登录，分享回调页面
 */
public class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 微信发送请求到第三方应用时，会回调到该方法
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
     */
    @Override
    public void onResp(BaseResp baseResp) {
        /*
       int type =  baseResp.getType();
       switch (type){
           case 1://登录

               break;
           case 2://分享
               switch (baseResp.errCode){
                   case BaseResp.ErrCode.ERR_OK:
                       Log.i("ansen", "微信分享成功.....");
                       break;
                   case BaseResp.ErrCode.ERR_USER_CANCEL://分享取消
                       Log.i("ansen", "微信分享取消.....");
                       break;
                   case BaseResp.ErrCode.ERR_AUTH_DENIED://分享被拒绝
                       Log.i("ansen", "微信分享被拒绝.....");
                       break;
               }

               break;
           case 3://微信支付

               break;
       }*/
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    //微信登录成功
                    case ConstantsAPI.COMMAND_SENDAUTH:
                        WeixinLoginHelper.getInstance().getmListener().loginSuccess();
                        requestWeixinInfo(baseResp);
                        break;
                    //微信分享成功
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        WeixinShareHeler.getInstance().getWeixinShareListener().shareSuccess(baseResp);
                        break;
                    default:
                        break;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                switch (baseResp.getType()) {
                    //微信登录取消
                    case ConstantsAPI.COMMAND_SENDAUTH:
                        WeixinLoginHelper.getInstance().getmListener().loginCancel();
                        break;
                    //微信分享取消
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        WeixinShareHeler.getInstance().getWeixinShareListener().shareCancel();
                        break;
                    default:
                        break;
                }

                break;

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                switch (baseResp.getType()) {
                    //微信登录拒绝
                    case ConstantsAPI.COMMAND_SENDAUTH:
                        WeixinLoginHelper.getInstance().getmListener().loginDenied();
                        break;
                    //微信分享拒绝
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        WeixinShareHeler.getInstance().getWeixinShareListener().shareDecline();
                        break;

                    default:
                        break;
                }
                break;
            default:
                break;

        }


        finish();
    }


    /**
     * 获取token 和 openID
     *
     * @param baseResp
     */
    private void requestWeixinInfo(BaseResp baseResp) {
        String code = ((SendAuth.Resp) baseResp).code;
        //获取access_token
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?")
                .append("appid=")
                .append(WeixinLoginHelper.getInstance().getAppid())
                .append("&secret=")
                .append(WeixinLoginHelper.getInstance().getSecret())
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        //网络同步访问
        HttpUtil.sendGetRequest(sb.toString(), new DataGetSuccessListener() {
            @Override
            public void dataGetSuccess(String data) {
                System.out.println("@ -- 获取token --@" + data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String access_token = jsonObject.optString("access_token");
                    String openid = jsonObject.optString("openid");

                    StringBuffer sb = new StringBuffer();
                    sb.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                            .append(access_token)
                            .append("&openid=")
                            .append(openid);

                    //获取用户信息
                    getWeixinUserinfo(sb.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void dataGetIOError(IOException e) {
                WeixinLoginHelper.getInstance().getmListener().loginFailed(e);
            }
        });

        finish();
    }

    /**
     * 获取微信用户信息
     *
     * @param requestInfo
     */
    private void getWeixinUserinfo(String requestInfo) {
        HttpUtil.sendGetRequest(requestInfo, new DataGetSuccessListener() {
            @Override
            public void dataGetSuccess(final String data) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        WeixinLoginHelper.getInstance().getmListener().getUserInfoSuc(data);

                    }
                });
            }

            @Override
            public void dataGetIOError(IOException e) {
                WeixinLoginHelper.getInstance().getmListener().getUserInfoError(e);
            }
        });
    }
}
