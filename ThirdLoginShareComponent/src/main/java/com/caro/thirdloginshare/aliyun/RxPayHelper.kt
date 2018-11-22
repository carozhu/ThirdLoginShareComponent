package com.caro.thirdloginshare.aliyun

import android.app.Activity
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.annotations.NonNull

/**
 * Author: carozhu
 * Date  : On 2018/10/30
 * Desc  : 参考来自@Rxpay
 */
class RxPayHelper (@param:NonNull private val activity: Activity) {

    companion object {
        internal val TAG = "RxPayHelper"
    }


    fun requestAlipay(@NonNull orderInfo: String): Flowable<Boolean> {
        return aliPayment(orderInfo)
    }


    private fun aliPayment(orderInfo: String): Flowable<Boolean> {
        return Flowable.just(orderInfo).compose(ensure(orderInfo))
    }

    private fun ensure( orderInfo: String): FlowableTransformer<Any, Boolean> {
        return FlowableTransformer {
            requestImplementation(orderInfo).map { paymentStatus -> paymentStatus.isStatus }
        }
    }

    private fun requestImplementation(orderInfo: String?): Flowable<AlipaymentStatus> {
        return AlipayHepler.payMoney(activity, orderInfo!!)
    }


}