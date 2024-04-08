package com.example.pistalibreandroid.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    private val FILE_PREFERENCE = "global_data"

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(FILE_PREFERENCE, Context.MODE_PRIVATE)
    }
}