package com.caro.thirdloginshare.aliyun;

import android.app.Activity;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Author: carozhu
 * Date  : On 2018/11/6
 * Desc  : 阿里云授权登录Helper
 * 商户[公司] PID: 2088521039858812
 * 打卡圈APPID: 2018052160181192
 * target_id可以是是你支付宝的帐户名
 * 参考Link  :
 * 支付宝问题社区：https://openclub.alipay.com/
 * 关于Target_id的解释：https://openclub.alipay.com/read.php?tid=5000&fid=59&ant_source=zsearch
 * 关于支付时提交timestamp的格式问题参考：https://openclub.alipay.com/read.php?tid=5000&fid=59&ant_source=zsearch
 * timestamp（发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"）
 */
public class AilpayAuthHelper {
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2017042206891483";
    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "137xxxxx";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "xxxx";

    public void myauthV2(Activity activity){
//        AuthTask authTask = new AuthTask(activity);
//        //authTask.authV2(info,true);
//        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
//        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

    }

}
