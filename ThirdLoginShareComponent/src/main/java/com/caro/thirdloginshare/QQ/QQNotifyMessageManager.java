package com.caro.thirdloginshare.QQ;

import com.tencent.tauth.UiError;

/**
 * Author: carozhu
 * Date  : On 2018/8/2
 * Desc  : 简单的callback消息通讯管理器
 */
public class QQNotifyMessageManager {
    private static  class NotifyMessageManagerHolder{
        public static  final QQNotifyMessageManager INSTANCE  = new QQNotifyMessageManager();
    }

    public static QQNotifyMessageManager getInstance(){
        return NotifyMessageManagerHolder.INSTANCE;
    }

    private QQNotifyMessage listener;
    public void registNotifyMessage(QQNotifyMessage nm){
        listener = nm;
    }
    public void qqLogining(){
        listener.qqLogining();
    }

    /**
     * 登录返回json为空
     * qq登录失败
     */
    public void qqLoginFailed(){
        listener.qqLoginFailed();
    }

    /**
     * 未安装QQ
     */
    public void unInstallQQ(){
        listener.unInstallQQ();
    }

    /**
     * QQ 登录失败
     * @param uiError 失败原因
     */
    public void qqLoginFailed(UiError uiError){
        listener.qqLoginFailed(uiError);
    }

    /**
     * QQ登录取消
     */
    public void qqLoginOnCancel(){
        listener.qqLoginOnCancel();
    }

    /**
     * 获取用户信息失败
     */
    public void getQQUserInfoFailed(){
        listener.getQQUserInfoFailed();
    }

    /**
     * 获取用户信息成功
     * @param qqLoginInfo
     */
    public void getQQUserInfoSuccess( QQLoginInfo qqLoginInfo){
        listener.getQQUserInfoSuccess(qqLoginInfo);

    }

    /**
     * 获取用户信息Error
     * @param uiError
     */
    public void getQQUserInfoOnError(UiError uiError){
        listener.getQQUserInfoOnError(uiError);
    }

    /**
     * 取消获取用户信息
     */
    public void getQQUserInfoOnCancel(){
        listener.getQQUserInfoOnCancel();
    }
}
