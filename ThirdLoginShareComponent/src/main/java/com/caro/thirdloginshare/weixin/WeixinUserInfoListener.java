package com.caro.thirdloginshare.weixin;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/4.
 */

public interface WeixinUserInfoListener {
    void notSupportWx();
    void loginSuccess();
    void loginCancel();
    void loginFailed(IOException e);
    void loginDenied();
    void getUserInfoSuc(String data);
    void getUserInfoError(IOException e);


}
