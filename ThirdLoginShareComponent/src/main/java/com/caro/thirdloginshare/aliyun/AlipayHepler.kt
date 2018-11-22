package com.caro.thirdloginshare.aliyun

import android.app.Activity
import android.util.Log
import com.alipay.sdk.app.PayTask
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.schedulers.Schedulers


/**
 * Author: carozhu
 * Date  : On 2018/10/30
 * Desc  : Alipay支付Helper
 */
object AlipayHepler {

    /**
     * 发起支付
     */
    fun payMoney(activity: Activity, orderInfo: String): Flowable<AlipaymentStatus> {
        return Flowable.create(FlowableOnSubscribe<PayTask> { e ->
            val alipay = PayTask(activity)
            e.onNext(alipay)
        }, BackpressureStrategy.ERROR)
                .map { payTask ->
                    createPaymentStatus(payTask, orderInfo)
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())

    }


    private fun createPaymentStatus(payTask: PayTask, orderInfo: String): AlipaymentStatus {
        val result = payTask.payV2(orderInfo, true)
        val payResult = AlipayResult(result)
        val resultStatus = payResult.resultStatus
        return if (resultStatus.equals("9000")) {
            AlipaymentStatus(true)
        } else {
            Log.e("Rxpay", "${payResult.resultStatus},${payResult.result}")
            AlipaymentStatus(false)
        }
    }


}