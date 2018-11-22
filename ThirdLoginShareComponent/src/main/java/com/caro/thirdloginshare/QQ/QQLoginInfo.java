package com.caro.thirdloginshare.QQ;

import android.util.Log;
import org.json.JSONObject;
import java.io.Serializable;

/**
 * Author: carozhu
 * Date  : On 2018/8/3
 * Desc  : QQ登录获取的信息
 */
public class QQLoginInfo implements Serializable {
    private String token="";
    private String openid="";
    private String nickname="";
    private String sex="";
    private String city="";
    private String province="";
    private String country="";
    private String headimgurl="";

    //返回json信息，让用户根据需求获取
    private JSONObject loginJSONResponse;
    private JSONObject userInfoJSONResponse;

    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) { this.sex = sex; }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getLoginJSONResponse() { return loginJSONResponse; }
    public void setLoginJSONResponse(JSONObject loginJSONResponse) { this.loginJSONResponse = loginJSONResponse; }

    public JSONObject getUserInfoJSONResponse() { return userInfoJSONResponse; }
    public void setUserInfoJSONResponse(JSONObject userInfoJSONResponse) { this.userInfoJSONResponse = userInfoJSONResponse; }

    @Override
    public String toString() {
        String str="token=" + token + "openId=" + openid + "nickName=" + nickname + "sex=" + sex + "city=" + city + "province=" + province + "country=" + country + "headimgUrl=" + headimgurl ;
        Log.i("QQLoginInfo",str);
        return str;
    }
}
