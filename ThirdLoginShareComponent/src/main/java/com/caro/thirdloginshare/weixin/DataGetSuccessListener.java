package com.caro.thirdloginshare.weixin;

import java.io.IOException;

/**
 *
 * 数据获取成功回调
 */

public interface DataGetSuccessListener {
    void dataGetSuccess(String data);
    void dataGetIOError(IOException e);
}
