package com.caro.thirdloginshare.aliyun

import android.app.Activity
import android.util.Log
import com.alipay.sdk.app.AuthTask
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.FlowableTransformer
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers

/**
 * Author: carozhu
 * Date  : On 2018/10/30
 * Desc  : 参考来自@Rxpay
 * 参考   : Rxpay
 * 参考   : 支付宝问题社区：https://openclub.alipay.com/
 * 参考   : https://blog.csdn.net/Fine1938768839/article/details/60766400
 * 参考   : https://openclub.alipay.com/read.php?tid=5000&fid=59&ant_source=zsearch
 * 参考   : https://openclub.alipay.com/read.php?tid=5000&fid=59&ant_source=zsearch
 *
 * func  : 支付宝授权
 */
class RxAlipayAuthHelper (@param:NonNull private val activity: Activity) {

    companion object {
        internal val TAG = "RxAlipayAuthHelper"
    }

    fun requestAlipayAuth(@NonNull authSigninfo : String) : Flowable<AlipayAuthResult>{
        return aliPayAuth(authSigninfo)
    }

    private fun aliPayAuth(orderInfo: String): Flowable<AlipayAuthResult> {
        return Flowable.just(orderInfo).compose(ensureAlipayAuth(orderInfo))
    }

    private fun ensureAlipayAuth( orderInfo: String): FlowableTransformer<Any, AlipayAuthResult> {
        return FlowableTransformer {
            requestImplementationAuthAlipay(orderInfo).map { alipayAuthResult -> alipayAuthResult }
        }
    }

    private fun requestImplementationAuthAlipay(orderInfo: String?): Flowable<AlipayAuthResult> {
        return payAuth(activity, orderInfo!!)
    }


    /**
     * 发起授权
     */
    fun payAuth(activity: Activity, orderInfo: String): Flowable<AlipayAuthResult> {
        return Flowable.create(FlowableOnSubscribe<AuthTask> { e ->
            val authTask = AuthTask(activity)
            e.onNext(authTask)
        }, BackpressureStrategy.ERROR)
                .map { authTask ->
                    createAuthmentStatus(authTask, orderInfo)
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())

    }


    private fun createAuthmentStatus(authTask: AuthTask, orderInfo: String): AlipayAuthResult {
        val result = authTask.authV2(orderInfo, true)
        val authResult = AlipayAuthResult(result)
        val resultStatus = authResult.resultStatus
        return if (resultStatus.equals("9000")) {
            authResult
        } else {
            Log.e("Rxpay", "${authResult.resultStatus},${authResult.result}")
            authResult
        }
    }
}