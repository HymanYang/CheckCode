package com.yy.test.checkcodes

import com.lody.whale.xposed.XC_MethodHook
import com.lody.whale.xposed.XposedBridge

object WhaleHook {

    fun hookAllMethods(){

        XposedBridge.log("start androidId")
        //android.provider.Settings.Secure#getString
        val clazz = android.provider.Settings.Secure::class.java
        val getString = clazz.getDeclaredMethod(
            "getString", android.content.ContentResolver::class.java, java.lang.String::class.java
        )
        XposedBridge.hookMethod(getString, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val key = param.args.last()
                if (key == android.provider.Settings.Secure.ANDROID_ID) {
                    val msg = "hookAndroidId to 0"
                    println(msg)
                    Exception(msg).printStackTrace()//print call StackTrace
                    param.result = "0"
                }
            }
        })


    }
}