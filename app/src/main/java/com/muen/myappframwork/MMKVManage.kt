package com.muen.myappframwork

import com.tencent.mmkv.MMKV

object MMKVManage {
    private val mmkv = MMKV.defaultMMKV()

    //常量
    const val SUCCESS_CODE = 200
    const val ERROR_CODE = 404
    const val HTTP_TIME_OUT = 20L
    const val DEBUG=false

    //缓存变量
    private const val KEY_USERNAME = "username"

    /**
     * 设备的激活时间
     */
    var username:String?
        set(value) {
            mmkv.encode(KEY_USERNAME, value)
        }
        get() = mmkv.decodeString(KEY_USERNAME)?:""

}