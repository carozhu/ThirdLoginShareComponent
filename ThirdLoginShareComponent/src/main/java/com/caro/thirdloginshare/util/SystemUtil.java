package com.caro.thirdloginshare.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Author: carozhu
 * Date  : On 2018/8/2
 * Desc  :
 */
public class SystemUtil {
    /**
     * 是否存在对应包名的APP
     *
     * @param context 上下文
     */
    public static Boolean isHaveApp(Context context, String packageName) {
        try {
            return isAppOk(context, packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断某已安装应用是否完整,也可以判断应用是否存在
     *
     * @param context  上下文
     * @param packName 包名
     * @return
     */
    public static boolean isAppOk(Context context, String packName) {
        boolean appIsOk = false;
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packName, 0);
            if (packageInfo != null) {
                appIsOk = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
             e.printStackTrace();
        }
        return appIsOk;
    }
    /**
     * 返回META-DATA (application)
     *
     * @param application
     * @return
     */
    public static String getMetaDataForApplication(Application application, String key) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo.metaData.getString(key);
    }

    /**
     * 返回META-DATA (activity)
     *
     * @param activity
     * @return
     */

    public static String getMetaDataForActivity(Activity activity, String key) {
        ActivityInfo info = null;
        try {
            info = activity.getPackageManager().getActivityInfo(
                    activity.getComponentName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.metaData.getString(key);
    }

}
