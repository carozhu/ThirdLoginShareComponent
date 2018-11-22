package com.caro.thirdloginshare.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public abstract class BaseWXActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        IWXAPI api;
        api =  WeixinLoginHelper.getInstance().getApi();
        if (api == null){
            api = WeixinShareHeler.getInstance().getWeiXinApi();
        }
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        IWXAPI api;
        api =  WeixinLoginHelper.getInstance().getApi();
        if (api == null){
            api = WeixinShareHeler.getInstance().getWeiXinApi();
        }
        api.handleIntent(getIntent(), this);
    }
}
