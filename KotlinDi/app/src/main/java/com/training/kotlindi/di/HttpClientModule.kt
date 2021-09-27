package com.training.kotlindi.di

import com.training.kotlindi.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.HttpURLConnection
import java.net.URL

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Provides
    fun provideUrl(url: String): URL {
        return URL(url)
    }

    @Provides
    fun provideConnection(url: URL): HttpURLConnection{
        return url.openConnection() as HttpURLConnection
    }

    @Provides
    fun provideUrlString(): String{
        return Const.TWEET_API_URL
    }
}