package com.yy.test.checkcodes

import android.Manifest
import com.lody.whale.xposed.XC_MethodHook
import com.lody.whale.xposed.XposedBridge
import java.util.*

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

        //android.app.Activity.requestPermissions
        val classAct = android.app.Activity::class.java

        val strActMethod = classAct.getDeclaredMethod("requestPermissions",Array<String>::class.java,Int::class.java)
        XposedBridge.hookMethod(strActMethod, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val key = param.args.first() as Array<String>
                if (key[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                    val msg = "start hook requestPermissions"
                    println(msg)
                    Exception(msg).printStackTrace()//print call StackTrace
                }
            }
        })

    }
}