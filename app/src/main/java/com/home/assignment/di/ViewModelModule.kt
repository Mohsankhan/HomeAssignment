package com.home.assignment.di

import com.home.assignment.viewmodel.ArticleViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindWorkoutUserViewModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AnyViewModelFactory): ViewModelProvider.Factory
}
