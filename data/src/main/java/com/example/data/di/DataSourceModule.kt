package com.example.data.di

import com.example.data.datasource.PostsLocalDataSourceImpl
import com.example.data.datasource.PostsRemoteDataSourceImpl
import com.example.domain.datasource.PostsLocalDataSource
import com.example.domain.datasource.PostsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewWithFragmentComponent
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Singleton

@Module(includes = [
    ServiceModule::class,
    PersistenceModule::class,
    MapperModule::class
])
@InstallIn(ApplicationComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun providesPostLocalDataSource(impl: PostsLocalDataSourceImpl): PostsLocalDataSource

    @Binds
    @Singleton
    abstract fun providesPostRemoteDataSource(impl: PostsRemoteDataSourceImpl): PostsRemoteDataSource
}