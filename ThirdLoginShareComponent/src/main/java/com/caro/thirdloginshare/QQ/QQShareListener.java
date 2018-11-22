package com.caro.thirdloginshare.QQ;

import com.tencent.tauth.UiError;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  : QQ分享callback
 */
public interface QQShareListener {
    void shareComplete(Object o);
    void shareError(UiError e);
    void shareCancel();
    void uninstallQQClient();

}
