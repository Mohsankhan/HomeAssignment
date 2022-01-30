package com.home.assignment.di

import com.home.assignment.MainActivity
import com.home.assignment.di.fragment_module.MainActivityFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {


    @ContributesAndroidInjector(modules = [MainActivityFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
