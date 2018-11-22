package com.caro.thirdloginshare.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Author: carozhu
 * Date  : On 2018/9/3
 * Desc  :
 */
public class BaseWXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Caused by: java.lang.IllegalStateException: Only fullscreen opaque activities can request orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        IWXAPI api = WeixinPayHeler.getInstance().getWeiXinApi();
        //api = WXAPIFactory.createWXAPI(this,  weixin_app_id, true);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        IWXAPI api = WeixinPayHeler.getInstance().getWeiXinApi();
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int code = baseResp.errCode;
        //显示充值成功的页面和需要的操作
        switch (code) {
            case 0://支付成功
                WeixinPayHeler.getInstance().getWeixinPayListener().weixinpaySuccess(baseResp);
                break;
            case -1://支付错误
                WeixinPayHeler.getInstance().getWeixinPayListener().weixinpayError(baseResp);
                break;
            case -2://支付取消
                WeixinPayHeler.getInstance().getWeixinPayListener().weixinpayCancel(baseResp);
                break;
            default:
                WeixinPayHeler.getInstance().getWeixinPayListener().weixinpayError(baseResp);
                break;
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
