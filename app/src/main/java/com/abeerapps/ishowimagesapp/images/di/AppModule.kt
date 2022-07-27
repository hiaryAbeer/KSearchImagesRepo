package com.abeerapps.ishowimagesapp.images.di

import com.abeerapps.ishowimagesapp.images.data.AppRepositoryImpl
import com.abeerapps.ishowimagesapp.images.data.network.SearchImagesEndPoint
import com.abeerapps.ishowimagesapp.images.domain.AppRepository
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(OkHttpProfilerInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideSearchImagesEndPoint(retrofit: Retrofit): SearchImagesEndPoint {
        return retrofit.create(SearchImagesEndPoint::class.java)
    }

    @Provides
    fun provideRepositoryImpl(searchImagesEndPoint: SearchImagesEndPoint): AppRepository =
        AppRepositoryImpl(searchImagesEndPoint)
}