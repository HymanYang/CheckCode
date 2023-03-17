package com.yy.test.checkcodes

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        WhaleHook.hookAllMethods()
    }

}