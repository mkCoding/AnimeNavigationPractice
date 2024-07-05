package com.example.apipractice.di

import com.example.apipractice.data.api.ApiDetails
import com.example.apipractice.data.api.ApiEndpoints
import com.example.apipractice.data.repository.ApiRepository
import com.example.apipractice.data.repository.ApiRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    //Add Providers that will be injected throught the app
    @Provides
    fun providesRetrofit():Retrofit{
        //okHttpClient
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

        //retrofit (base url, client, converterFactory)
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    fun providesApiEndpoints(retrofit: Retrofit):ApiEndpoints{
        return retrofit.create(ApiEndpoints::class.java)
    }

    @Provides
    fun providesRepository(apiEndpoints: ApiEndpoints):ApiRepository{
        return ApiRepositoryImpl(apiEndpoints)
    }




}