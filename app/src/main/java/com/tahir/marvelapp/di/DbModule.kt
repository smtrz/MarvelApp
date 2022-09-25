package com.tahir.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.tahir.marvelapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e.
// everywhere in the application)
// contains all the instances with application context.
object DbModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDatabase::class.java, "marvelapp").build()

    @Singleton
    @Provides
    fun provideMarvelDao(db: AppDatabase) = db.marvelAppDao()



}
