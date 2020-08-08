package com.example.posts.screens.posts.fav

import androidx.lifecycle.ViewModel
import com.example.posts.di.ViewModelBuilderModule
import com.example.posts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavListModule {
    @ContributesAndroidInjector(modules = [ViewModelBuilderModule::class])
    internal abstract fun favListFragment(): FavListFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavListViewModel::class)
    abstract fun bindViewModel(viewModel: FavListViewModel): ViewModel
}