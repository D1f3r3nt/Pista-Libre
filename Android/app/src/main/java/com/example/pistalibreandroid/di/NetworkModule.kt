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

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
    
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pista-libre-back.fly.dev")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesDragonBallApi(retrofit: Retrofit): PistaLibreApi {
        return retrofit.create(PistaLibreApi::class.java)
    }
}

