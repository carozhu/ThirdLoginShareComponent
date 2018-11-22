package com.zsyj.habitcheckin;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.caro.thirdloginshare.weixin.BaseWXPayEntryActivity;
import com.caro.thirdloginshare.weixin.WeixinPayHeler;
import com.caro.thirdloginshare.weixin.WeixinPayListener;
import com.carozhu.WXPayAnnotation;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import butterknife.OnClick;


/**
 * Author: carozhu
 * Date  : On 2018/9/3
 * Desc  :
 */
@WXPayAnnotation(packageName = "com.zsyj.habitcheckin", superClass = BaseWXPayEntryActivity.class)
public class WeixinPaySimpleActivity extends BaseButterknifeActivity {

    Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_weixin_pay;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        context = this;
    }

    @OnClick(R.id.weixin_pay)
    public void weixin_pay(){
//        WeixinPayHeler.getInstance().configContext(context)
//                .setWeixinAppID(WEIXIN_APP_ID)
//                .createWXAPI()
//                .setWeixinShareListener(new WeixinPayListener() {
//                    @Override
//                    public void uninstallWeixin() {
//                        Toast.makeText(context,"uninstallWeixin",Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void weixinpaySuccess(BaseResp baseResp) {
//                        Toast.makeText(context,"weixinpaySuccess",Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void weixinpayError(BaseResp baseResp) {
//                        Toast.makeText(context,"weixinpayError: Tyep = "+baseResp.getType() + " code = "+baseResp.errCode,Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void weixinpayCancel(BaseResp baseResp) {
//                        Toast.makeText(context,"weixinpayCancel",Toast.LENGTH_LONG).show();
//                    }
//                }).weixinPay("1","1","1","1","1","1");
    }
}
