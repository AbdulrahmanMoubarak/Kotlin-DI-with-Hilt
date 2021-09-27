package com.training.kotlindi.di

import androidx.lifecycle.MutableLiveData
import com.training.kotlindi.data.HttpClient
import com.training.kotlindi.models.FakeTweetModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkViewModelModule {

    @Singleton
    @Provides
    fun provideHttpClient(conn: HttpURLConnection, url: URL): HttpClient{
        return HttpClient(conn, url)
    }



    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }
}