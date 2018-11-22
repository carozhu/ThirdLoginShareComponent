package com.caro.thirdloginshare.weixin;

import java.io.IOException;

/**
 * Created by 陈小康 on 2017/9/3.
 * 数据获取成功回调
 */

public interface DataGetSuccessListener {
    void dataGetSuccess(String data);
    void dataGetIOError(IOException e);
}
