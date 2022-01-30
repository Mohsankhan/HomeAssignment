package com.home.assignment.di.fragment_module

import com.home.assignment.home.DetailScreenFragment
import com.home.assignment.home.HomeFragment
import com.home.assignment.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailScreenFragment(): DetailScreenFragment
}
