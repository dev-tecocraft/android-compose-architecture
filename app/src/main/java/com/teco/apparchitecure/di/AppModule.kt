package com.teco.apparchitecure.di

import com.teco.apparchitecure.data.remote.service.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("base_url")
    fun provideTestString() = "https://www.tecocraft.com/eatenglish/"

    @Provides
    fun providesHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()


    @Provides
    fun provideRetrofitClient(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)
}