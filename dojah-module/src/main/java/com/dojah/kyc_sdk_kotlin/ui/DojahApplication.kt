//package com.dojah_inc.dojah_android_sdk.ui
//
//import android.app.Application
//import dagger.hilt.android.HiltAndroidApp
//
////@HiltAndroidApp
//class DojahApplication : Application() {
////    override fun attachBaseContext(base: Context?) {
////        super.attachBaseContext(base)
////        MultiDex.install(this)
////    }
////    @Inject
////    lateinit var provider: TerminalProvider
//
////    @Inject
////    lateinit var processWatcher: ProcessWatcher
//
////    @Inject
////    lateinit var chatManager: ChatManager
//
////    @Inject
////    lateinit var prefManager: SharedPreferenceManager
//
//    override fun onCreate() {
//        super.onCreate()
////        ActivityLifecycleCallback.register(this)
////        Timber.plant(Timber.DebugTree())
//
////        provider.init()
//        // Fetching Android ID and storing it in SharedPreference
////        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
////        prefManager.setDeviceSerialNumber(pin = deviceID)
//
////        config = config.copy(dumpHeap = false)
////        LeakCanary.showLeakDisplayActivityLauncherIcon(false)
//
////        processWatcher.startWatching(this)
//
////        chatManager.init(this)
//
//    }
//}