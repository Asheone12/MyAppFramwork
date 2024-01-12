package com.muen.myappframwork

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //MMKV
        val mmkvDir = MMKV.initialize(this)
        ARouter.init(this)
        Log.d("mmkv", "mmkvDir=$mmkvDir")
    }
}