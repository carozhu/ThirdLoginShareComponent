package com.caro.thirdloginshare.aliyun;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: carozhu
 * Date  : On 2018/11/6
 * Desc  : 参考来自：https://github.com/Jamling/af-pay/blob/master/library/src/main/java/cn/ieclipse/pay/alipay/OrderInfoUtil2_0.java
 * 商户[公司] PID: 2088521039858812
 * 打卡圈APPID: 2018052160181192
 * 商户pid 和 appid 是禁止出现在我们客户端代码上的 。为了安全
 * 拿到服务端的加签后：final String orderInfo = orderParam + "&" + sign;
 * 或是直接返回一个authInfo回来、方便多段对接
 * 最直接的，服务端返回一个加签的sign https://blog.csdn.net/Fine1938768839/article/details/60766400
 * 然后直接拿到signInfo 去auth2
 */
public class OrderInfoUtil2_0 {
    /**
     * 构造授权参数列表
     * 授权请求参数参考来自 ：https://docs.open.alipay.com/218/105327
     * @param pid 商户签约拿到的pid
     * @param app_id 商户签约拿到的app_id
     * @param target_id  商户标识该次用户授权请求的ID，该值在商户端应保持唯一 : target_id可以是你支付宝的帐户名。可以是验证手机号的手机号。也可以随便填写一个。但最好是手机号之类验证过的
     *                   target_id参数是商户自定义的，不需要获取。该参数类似请求号、时间戳、版本号等用法，因此建议每次请求的时候该值不同。
     * @param rsa2  商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     *
     * @return
     */
    public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();

        //服务接口名称， 固定值
        keyValues.put("apiname", "com.alipay.account.auth");

        //常量值
        keyValues.put("method", "alipay.open.auth.sdk.code.get");

        // 商户签约拿到的app_id，如：2013081700024223
        keyValues.put("app_id", app_id);

        // 商户类型标识， 固定值
        keyValues.put("app_name", "mc");

        // 业务类型， 固定值
        keyValues.put("biz_type", "openservice");

        // 商户签约拿到的pid，如：2088102123816631
        keyValues.put("pid", pid);

        // 产品码， 固定值
        keyValues.put("product_id", "APP_FAST_LOGIN");

        // 授权范围， 固定值
        keyValues.put("scope", "kuaijie");

        // 商户唯一标识，如：kkkkk091125
        keyValues.put("target_id", target_id);

        // 授权类型， 固定值
        keyValues.put("auth_type", "AUTHACCOUNT");

        // 签名类型
        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        return keyValues;
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     *
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        }
        else {
            sb.append(value);
        }
        return sb.toString();
    }
}
