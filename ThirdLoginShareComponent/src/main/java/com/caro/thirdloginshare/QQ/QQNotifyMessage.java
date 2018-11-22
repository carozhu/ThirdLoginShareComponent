package com.caro.thirdloginshare.QQ;

import com.tencent.tauth.UiError;

/**
 * Author: carozhu
 * Date  : On 2018/8/2
 * Desc  : QQ callback message
 */
public interface QQNotifyMessage {

    void qqLogining();

    void qqLoginFailed();

    void unInstallQQ();

    void qqLoginFailed(UiError uiError);

    void qqLoginOnCancel();

    void getQQUserInfoFailed();

    void getQQUserInfoSuccess(QQLoginInfo qqLoginInfo);

    void getQQUserInfoOnError(UiError uiError);

    void getQQUserInfoOnCancel();

}
