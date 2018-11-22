package  com.zsyj.habitcheckin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.caro.thirdloginshare.QQ.QQLoginActivity;
import com.caro.thirdloginshare.QQ.QQLoginInfo;
import com.caro.thirdloginshare.QQ.QQNotifyMessage;
import com.caro.thirdloginshare.QQ.QQNotifyMessageManager;
import com.caro.thirdloginshare.weixin.BaseWXEntryActivity;
import com.carozhu.WXEntryAnnotation;
import com.tencent.tauth.UiError;

import butterknife.OnClick;


@WXEntryAnnotation(packageName = " com.zsyj.habitcheckin", superClass = BaseWXEntryActivity.class)
public class WeixinQQLoginSimpleActivity extends BaseButterknifeActivity {
    Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_weixin_qq_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //隐藏状态栏 全屏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.i("@---@", "init");
        context = this;

    }


    @OnClick(R.id.qq_login)
    public void qq_login() {
        QQNotifyMessageManager.getInstance().registNotifyMessage(new QQNotifyMessage() {
            @Override
            public void qqLogining() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录中", Toast.LENGTH_LONG).show();
            }

            @Override
            public void qqLoginFailed() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void unInstallQQ() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "未安装QQ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void qqLoginFailed(UiError uiError) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录失败 ：code " + uiError.errorCode + " msg: " + uiError.errorMessage, Toast.LENGTH_LONG).show();

            }

            @Override
            public void qqLoginOnCancel() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "QQ登录取消", Toast.LENGTH_LONG).show();

            }

            @Override
            public void getQQUserInfoFailed() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取QQ用户信息失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void getQQUserInfoSuccess(QQLoginInfo qqLoginInfo) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取用户信息成功 ：" + qqLoginInfo.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void getQQUserInfoOnError(UiError uiError) {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取用户信息失败OnError ：code " + uiError.errorCode + " msg: " + uiError.errorMessage, Toast.LENGTH_LONG).show();

            }

            @Override
            public void getQQUserInfoOnCancel() {
                Toast.makeText(WeixinQQLoginSimpleActivity.this, "获取QQ信息取消", Toast.LENGTH_LONG).show();
            }
        });
        QQLoginActivity.startQQLoginActivity(WeixinQQLoginSimpleActivity.this, "1106910365");
    }

    @OnClick(R.id.weixin_login)
    public void weixin_login() {
//        WeixinLoginHelper.getInstance()
//                .regToWeixin(context, WEIXIN_APP_ID, WEIXIN_SECRET)
//                .regCallBackListener(new WeixinUserInfoListener() {
//                    @Override
//                    public void notSupportWx() {
//
//                    }
//
//                    @Override
//                    public void loginSuccess() {
//
//                    }
//
//                    @Override
//                    public void loginCancel() {
//
//                    }
//
//                    @Override
//                    public void loginFailed(IOException e) {
//
//                    }
//
//                    @Override
//                    public void loginDenied() {
//
//                    }
//
//                    @Override
//                    public void getUserInfoSuc(String data) {
//                        Toast.makeText(context, "用户信息" + data, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void getUserInfoError(IOException e) {
//
//                    }
//                })
//                .accreditSign();
    }

}
