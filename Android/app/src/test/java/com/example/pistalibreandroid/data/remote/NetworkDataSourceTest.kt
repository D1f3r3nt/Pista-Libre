package com.example.pistalibreandroid.data.remote

import com.example.pistalibreandroid.data.network.NetworkDataSource
import com.example.pistalibreandroid.data.network.apis.PistaLibreApi
import com.example.pistalibreandroid.helpers.MockWebDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkDataSourceTest {
    
    private lateinit var api: PistaLibreApi
    
    @Before
    fun setUp() {
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockWebDispatcher()
        mockWebServer.start()
        
        val loggerInterceptor = HttpLoggingInterceptor().apply { 
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        
        api = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PistaLibreApi::class.java)
    }
    
    @Test
    fun `WHEN getAllClubs THEN succes list`() = runTest {
        // GIVEN
        val remoteDataSource = NetworkDataSource(api)
        
        // WHEN
        val characterList = remoteDataSource.getAllClubs("")
        
        // THEN
        Assert.assertTrue(characterList.isSuccessful)
        characterList.body()?.let { Assert.assertTrue(it.isNotEmpty()) }
    }
}