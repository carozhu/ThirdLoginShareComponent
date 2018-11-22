package com.zsyj.habitcheckin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import butterknife.OnClick;

/**
 * Author: carozhu
 * Date  : On 2018/9/2
 * Desc  : 微信QQ分享测试
 */
public class MainActivity extends BaseButterknifeActivity {
    Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        context = this;
    }

    @OnClick(R.id.share)
    public void share() {
        startActivity(new Intent(context, WeixinQQShareSimpleActivity.class));
    }

    @OnClick(R.id.login)
    public void login() {
        startActivity(new Intent(context, WeixinQQLoginSimpleActivity.class));

    }

    @OnClick(R.id.pay)
    public void pay() {
        startActivity(new Intent(context, WeixinPaySimpleActivity.class));
    }


}
