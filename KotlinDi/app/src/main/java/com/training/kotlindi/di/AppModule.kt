package com.training.kotlindi.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.training.kotlindi.UI.Main.MainApplication
import com.training.kotlindi.UI.Main.NetworkViewModel
import com.training.kotlindi.data.HttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication{
        return app as MainApplication
    }
}