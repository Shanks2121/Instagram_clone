package com.example.instagram_clone.injector

import android.content.Context
import com.example.instagram_clone.data.ServicesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// 3/20/2022

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContextInstance(@ApplicationContext cxt: Context) = cxt


    @Provides
    @Singleton
    fun provideServicesIm() = ServicesImpl()
}