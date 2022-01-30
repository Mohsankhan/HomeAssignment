package com.home.assignment.di

import com.home.assignment.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(myApplication: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}
