package com.example.posts.screens.detail

import androidx.lifecycle.ViewModel
import com.example.posts.di.ViewModelBuilderModule
import com.example.posts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {
    @ContributesAndroidInjector(modules = [ViewModelBuilderModule::class])
    internal abstract fun detailFragment(): DetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindViewModel(viewModel: DetailViewModel): ViewModel
}