package com.example.pistalibreandroid.di

import com.example.pistalibreandroid.data.network.apis.PistaLibreApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // Proveemos una instancia de OkHttpClient para ser usada por Retrofit
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        // Construimos y retornamos una nueva instancia de OkHttpClient
        return OkHttpClient.Builder()
            .build()
    }

    // Proveemos una instancia de Moshi para manejo de JSON
    @Provides
    fun providesMoshi(): Moshi {
        // Construimos y retornamos una nueva instancia de Moshi, incluyendo un adaptador para Kotlin
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


    // Proveemos una instancia de Retrofit configurada con Moshi y OkHttpClient
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        // Construimos y retornamos una nueva instancia de Retrofit
        return Retrofit.Builder()
            .baseUrl("https://pista-libre-back.fly.dev")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(okHttpClient)
            .build()
    }

    // Proveemos una instancia de la interfaz PistaLibreApi usando Retrofit
    @Provides
    fun providesPistaLibreApi(retrofit: Retrofit): PistaLibreApi {
        // Creamos y retornamos una implementación de la interfaz API
        return retrofit.create(PistaLibreApi::class.java)
    }
}

