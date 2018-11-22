package com.caro.thirdloginshare.weixin;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.tauth.UiError;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  : Weixin分享callback
 */
public interface WeixinShareListener {
    void shareSuccess(BaseResp baseResp);
    void uninstallWeixin();
    void shareCancel();
    void shareDecline();

}
