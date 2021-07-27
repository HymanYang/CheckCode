package com.yy.test.checkcodes

import android.content.ContentResolver
import android.location.LocationManager
import android.net.wifi.WifiInfo
import android.provider.Settings.Secure
import android.telephony.TelephonyManager
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.net.NetworkInterface

/**
 * @Description: hook插件处理代码
 * @Author: fuSheng
 * @CreateDate: 2021/7/23 17:46
 */
class HookHelper : IXposedHookLoadPackage {

    companion object {
        val WHITE_PACKAGE_NAMES = mutableListOf("com.imaginer.yunji")
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {

        lpparam?.let {
            //包名
            XposedBridge.log(">>packageName>>" + lpparam.packageName)

            if (!(WHITE_PACKAGE_NAMES.contains(lpparam.packageName) &&
                        WHITE_PACKAGE_NAMES.contains(lpparam.processName))
            ) return@let


            //固定格式
            XposedHelpers.findAndHookMethod(
                TelephonyManager::class.java.name,  // 需要hook的方法所在类的完整类名
                lpparam.classLoader,  // 类加载器，固定这么写就行了
                "getDeviceId",  // 需要hook的方法名
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getDeviceId()获取了imei")
                    }
                }
            )


            XposedHelpers.findAndHookMethod(
                TelephonyManager::class.java.name,
                lpparam.classLoader,
                "getDeviceId",
                Int::class.javaPrimitiveType,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getDeviceId(int)获取了imei")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                TelephonyManager::class.java.name,
                lpparam.classLoader,
                "getSubscriberId",
                Int::class.javaPrimitiveType,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getSubscriberId获取了imsi")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                TelephonyManager::class.java.name,
                lpparam.classLoader,
                "getImei",
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getImei获取了imei")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                TelephonyManager::class.java.name,
                lpparam.classLoader,
                "getImei",
                Int::class.javaPrimitiveType,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getImei(int)获取了imei")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                WifiInfo::class.java.name,
                lpparam.classLoader,
                "getMacAddress",
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getMacAddress()获取了mac地址")
                    }
                }
            )


            XposedHelpers.findAndHookMethod(
                WifiInfo::class.java.name,
                lpparam.classLoader,
                "getIpAddress",
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getIpAddress()获取了mac地址")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                NetworkInterface::class.java.name,
                lpparam.classLoader,
                "getHardwareAddress",
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getHardwareAddress()获取了mac地址")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                Secure::class.java.name,
                lpparam.classLoader,
                "getString",
                ContentResolver::class.java,
                String::class.java,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log(lpparam.packageName + "调用Settings.Secure.getstring获取了" + param.args[1])
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                LocationManager::class.java.name,
                lpparam.classLoader,
                "getLastKnownLocation",
                String::class.java,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用getLastKnownLocation获取了GPS地址")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                LocationManager::class.java.name,
                lpparam.classLoader,
                "requestLocationUpdates",
                String::class.java,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用requestLocationUpdates获取了GPS地址")
                    }
                }
            )


            XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstalledPackages",
                Int::class.javaPrimitiveType,
                object : DumpMethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        XposedBridge.log(lpparam.packageName + "调用手机安装应用列表获取了imei")
                    }
                }
            )

        }


    }


    internal open class DumpMethodHook() : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            //在这里，我们dump一下调用的方法栈信息
            dump2()
        }

        companion object {

            /**
             * dump模式2：类信通院报告模式
             */
            private fun dump2() {
                XposedBridge.log("Dump Stack: " + "---------------start----------------")
                val ex = Throwable()
                val stackElements = ex.stackTrace
                if (stackElements != null) {
                    for (i in stackElements.indices) {
                        val sb = StringBuilder("[方法栈调用]")
                        sb.append(i)
                        XposedBridge.log(
                            ("[Dump Stack]" + i + ": " + stackElements[i].className
                                    + "----" + stackElements[i].fileName
                                    + "----" + stackElements[i].lineNumber
                                    + "----" + stackElements[i].methodName)
                        )
                    }
                }
                XposedBridge.log("Dump Stack: " + "---------------over----------------")
            }
        }
    }


}