package com.home.assignment.di

import com.google.gson.Gson
import com.home.assignment.MyApplication
import com.home.assignment.common.CommonMethods
import com.home.assignment.common.CustomDialogPopup
import com.home.assignment.common.Utils
import com.home.assignment.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])

class AppModule {

    /*@Singleton
    @Provides
    fun provideFireStore(application: MyApplication): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }*/

    @Provides
    fun provideUtils(): Utils {
        return Utils()
    }

   /* @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }*/

    @Provides
    fun provideUserRepository(applicationContext: MyApplication,gson: Gson
    ): ArticleRepository {
        return ArticleRepository(applicationContext,gson)
    }

    @Provides
    @Singleton
    fun provideCommonMethods(
        customDialogPopup: CustomDialogPopup
    ): CommonMethods {
        return CommonMethods(customDialogPopup)
    }

    @Provides
    @Singleton
    fun provideCustomDialogPopup(): CustomDialogPopup {
        return CustomDialogPopup()
    }
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

}
