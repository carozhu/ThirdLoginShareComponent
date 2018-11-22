package com.caro.thirdloginshare.weixin;

import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  : Weixin pay callback
 */
public interface WeixinPayListener {
    void uninstallWeixin();
    void weixinpaySuccess(BaseResp baseResp);
    void weixinpayError(BaseResp baseResp);
    void weixinpayCancel(BaseResp baseResp);

}
