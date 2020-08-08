package com.example.posts.screens.posts.all

import androidx.lifecycle.ViewModel
import com.example.posts.di.ViewModelBuilderModule
import com.example.posts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeListModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilderModule::class])
    internal abstract fun homeListFragment(): HomeListFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeListViewModel::class)
    abstract fun bindViewModel(viewModel: HomeListViewModel): ViewModel
}