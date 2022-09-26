package com.example.nasagallery.di

import android.content.Context
import com.example.nasagallery.data.mock.PhotosMockSource
import com.example.nasagallery.data.mock.PhotosMockSourceImpl
import com.example.nasagallery.data.repository.PhotosRepository
import com.example.nasagallery.data.repository.PhotosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindPhotosRepository(
        repositoryImpl: PhotosRepositoryImpl
    ): PhotosRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataProviderModule {

    @Provides
    @Singleton
    fun providePhotosMockSource(@ApplicationContext appContext: Context): PhotosMockSource {
        return PhotosMockSourceImpl(appContext)
    }

}