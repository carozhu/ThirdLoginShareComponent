package com.caro.thirdloginshare.aliyun

import android.text.TextUtils

/**
 * Author: carozhu
 * Date  : On 2018/10/30
 * Desc  :
 */
class AlipayResult(rawResult: Map<String, String>?)  {
    /**
     * @return the resultStatus
     */
    var resultStatus: String? = null
        private set
    /**
     * @return the result
     */
    var result: String? = null
        private set
    /**
     * @return the memo
     */
    var memo: String? = null
        private set

    init {
        if (rawResult != null) {
            for (key in rawResult.keys) {
                if (TextUtils.equals(key, "resultStatus")) {
                    resultStatus = rawResult[key]
                } else if (TextUtils.equals(key, "result")) {
                    result = rawResult[key]
                } else if (TextUtils.equals(key, "memo")) {
                    memo = rawResult[key]
                }
            }
        }

    }

    override fun toString(): String {
        return ("resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}")
    }

}