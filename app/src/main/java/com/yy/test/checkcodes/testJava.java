//package com.yy.test.checkcodes;
//
//import de.robv.android.xposed.XposedHelpers;
//import de.robv.android.xposed.callbacks.XC_LoadPackage;
//
//public class testJava {
//
//    void test(XC_LoadPackage.LoadPackageParam lpparam){
//
//        XposedHelpers.findAndHookMethod("android.app.Fragment",lpparam.classLoader,"",String[].class,int.class, new HookHelper.DumpMethodHook());
////        try {
////            XposedHelpers.findAndHookMethod(
////                    "android.app.Fragment",
////                    lpparam.classLoader,
////                    "requestPermissions",
////                    String[]::class.javaPrimitiveType,
////                    Int::class.javaPrimitiveType,
////                    object : DumpMethodHook() {
////                override fun beforeHookedMethod(param: MethodHookParam?) {
////                    XposedBridge.log(lpparam.packageName + "调用getInstalledPackages获取安装应用列表")
////                }
////            }
////                )
////        } catch (e: Exception) {
////        }
//    }
//}
