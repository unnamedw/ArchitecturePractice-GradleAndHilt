package com.doachgosum.listsampleapp.di

import com.doachgosum.listsampleapp.data.repository.PhotoRepositoryImpl
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPhotoRepository(
        analyticsServiceImpl: PhotoRepositoryImpl
    ): PhotoRepository
}