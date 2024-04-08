package com.example.pistalibreandroid.core.di

import com.example.pistalibreandroid.login.data.network.LoginClient
import com.example.pistalibreandroid.registro.registroClub.data.network.SignUpClubClient
import com.example.pistalibreandroid.registro.registroJugador.data.network.SignUpUserClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://postman-rest-api-learner.glitch.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit):LoginClient{
        return retrofit.create(LoginClient::class.java)
    }

    @Singleton
    @Provides
    fun provideSignUpUserClient(retrofit: Retrofit):SignUpUserClient{
        return retrofit.create(SignUpUserClient::class.java)
    }

    @Singleton
    @Provides
    fun provideSignUpClubClient(retrofit: Retrofit): SignUpClubClient {
        return retrofit.create(SignUpClubClient::class.java)
    }
}

