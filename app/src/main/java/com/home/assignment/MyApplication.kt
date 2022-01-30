package com.home.assignment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.provider.Settings
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.home.assignment.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), LifecycleObserver, HasActivityInjector  {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    var isAppBackgrounded = false
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        isAppBackgrounded = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        // App in foreground
        isAppBackgrounded = false
    }

    @get:SuppressLint("HardwareIds")
    val deviceKey: String
        get() = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}