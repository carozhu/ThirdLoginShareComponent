package com.caro.thirdloginshare.QQ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.caro.thirdloginshare.util.SystemUtil;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Author: carozhu
 * Date  : On 2018/8/2
 * Desc  : QQ第三方登录
 */
public class QQLoginActivity extends Activity {
    String QQ_APPID = null;
    Tencent mTencent;
    QQLoginInfo qqLoginInfo;

    /**
     * 启动QQ登录授权
     *
     * @param activity
     */
    public static void startQQLoginActivity(Activity activity,String QQ_APPID) {
        Intent intent = new Intent(activity, QQLoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("QQ_APPID", QQ_APPID);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        QQ_APPID = bundle.getString("QQ_APPID");
        //通过以下方法读取manifest中的QQ_APPID获取到对应APPID也不为空，但还会提示错误：出现登录授权失败（错误码：110404）
        //QQ_APPID = SystemUtil.getMetaDataForApplication(getApplication(),"QQ_APPKEY");
        qqLoginInfo = new QQLoginInfo();
        QQNotifyMessageManager.getInstance().qqLogining();
        qqLogin();
    }

    /**
     * 调起qq登录
     */
    private void qqLogin() {
        mTencent = Tencent.createInstance(QQ_APPID, this);
        String qqPackageName = "com.tencent.mobileqq";
        //if (SystemUtil.isHaveApp(QQLoginActivity.this, qqPackageName))
        //if (mTencent.isQQInstalled(getApplicationContext()))
        if (!mTencent.isSessionValid())
        {
            mTencent.login(this, "all", loginUiListener);
        } else {
            QQNotifyMessageManager.getInstance().unInstallQQ();
            finish();
        }
    }

    private IUiListener loginUiListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                //"返回为空", "登录失败"
                QQNotifyMessageManager.getInstance().qqLoginFailed();
                finish();
            }
            JSONObject jsonObject = (JSONObject) response;
            if (null != jsonObject && jsonObject.length() == 0) {
                //"返回为空", "登录失败"
                QQNotifyMessageManager.getInstance().qqLoginFailed();
                finish();
            }
            qqLoginInfo.setLoginJSONResponse(jsonObject);

            //登录成功 -- > 获取用户信息
            String token = jsonObject.optString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.optString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.optString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
                qqLoginInfo.setToken(token);
                qqLoginInfo.setOpenid(openId);

                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
                com.tencent.connect.UserInfo userInfo = new com.tencent.connect.UserInfo(QQLoginActivity.this, mTencent.getQQToken());
                userInfo.getUserInfo(userUiListener);
            }

        }

        @Override
        public void onError(UiError uiError) {
            //登录失败
            QQNotifyMessageManager.getInstance().qqLoginFailed(uiError);
            finish();
        }

        @Override
        public void onCancel() {
            //登录取消
            QQNotifyMessageManager.getInstance().qqLoginOnCancel();
            finish();
        }
    };

    /**
     * {
     * "ret": 0,
     * "msg": "",
     * "is_lost": 0,
     * "nickname": "大国男儿",
     * "gender": "男",
     * "province": "贵州",
     * "city": "贵阳",
     * "year": "1989",
     * "constellation": "",
     * "figureurl": "http://qzapp.qlogo.cn/qzapp/101461768/3FD51CC47C5E3CDE9A1D55510AA93FEB/30",
     * "figureurl_1": "http://qzapp.qlogo.cn/qzapp/101461768/3FD51CC47C5E3CDE9A1D55510AA93FEB/50",
     * "figureurl_2": "http://qzapp.qlogo.cn/qzapp/101461768/3FD51CC47C5E3CDE9A1D55510AA93FEB/100",
     * "figureurl_qq_1": "http://thirdqq.qlogo.cn/qqapp/101461768/3FD51CC47C5E3CDE9A1D55510AA93FEB/40",
     * "figureurl_qq_2": "http://thirdqq.qlogo.cn/qqapp/101461768/3FD51CC47C5E3CDE9A1D55510AA93FEB/100", 取最后一个为头像
     * "is_yellow_vip": "0",
     * "vip": "0",
     * "yellow_vip_level": "0",
     * "level": "0",
     * "is_yellow_year_vip": "0"
     * }
     */
    private IUiListener userUiListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                //返回为空  获取用户信息失败
                QQNotifyMessageManager.getInstance().getQQUserInfoFailed();
                finish();
            }
            JSONObject jsonObject = (JSONObject) response;
            if (null != jsonObject && jsonObject.length() == 0) {
                //返回为空  获取用户信息失败
                QQNotifyMessageManager.getInstance().getQQUserInfoFailed();
                finish();
            }
            qqLoginInfo.setUserInfoJSONResponse(jsonObject);
            //昵称
            String name = jsonObject.optString("nickname");
            //头像
            String iconUrl = jsonObject.optString("figureurl_qq_2");
            //性别 "男" | "女"
            String gender = jsonObject.optString("gender");
            //省份
            String province = jsonObject.optString("province");
            //城市
            String city = jsonObject.optString("city");

            qqLoginInfo.setNickname(name);
            qqLoginInfo.setHeadimgurl(iconUrl);
            qqLoginInfo.setSex(gender);
            qqLoginInfo.setProvince(province);
            qqLoginInfo.setCity(city);
            // 获取QQ用户信息成功
            QQNotifyMessageManager.getInstance().getQQUserInfoSuccess(qqLoginInfo);
            finish();
        }

        @Override
        public void onError(UiError uiError) {
            //请求错误  获取用户信息失败
            QQNotifyMessageManager.getInstance().getQQUserInfoOnError(uiError);
            finish();
        }

        @Override
        public void onCancel() {
            //请求用户信息取消
            QQNotifyMessageManager.getInstance().getQQUserInfoOnCancel();
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == com.tencent.connect.common.Constants.REQUEST_LOGIN || requestCode == com.tencent.connect.common.Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
