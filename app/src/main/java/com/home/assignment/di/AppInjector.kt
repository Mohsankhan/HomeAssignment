package com.home.assignment.di

import com.home.assignment.MyApplication
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
object AppInjector {
    fun init(app: MyApplication) {
        DaggerAppComponent.builder().application(app)
            .build().inject(app)
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector || activity is HasActivityInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is androidx.fragment.app.FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: androidx.fragment.app.FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }
}
