package com.training.kotlindi.UI.Main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.training.kotlindi.data.HttpClient
import com.training.kotlindi.models.FakeTweetModel
import com.training.kotlindi.util.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel
@Inject
constructor(
    private val httpClient: HttpClient,
    private val dis: CompositeDisposable,
    ): ViewModel() {

    val liveTweets: MutableLiveData<List<FakeTweetModel>> = MutableLiveData()


    fun getTweets(){

       var observer = object : SingleObserver<List<FakeTweetModel>>{
           override fun onSubscribe(d: Disposable) {
               Log.d("SingleO", "onSubscribe: subscribed")
           }

           override fun onSuccess(t: List<FakeTweetModel>) {
               liveTweets.postValue(t)
               Log.d("SingleO", "onSuccess: Succeeded")
           }

           override fun onError(e: Throwable) {
               Log.d("SingleO", "onError: ${e.message}")
           }

       }

        var observable = Single.create<List<FakeTweetModel>> {
            var list = httpClient.MakeServiceCall(Const.TWEET_API_URL)
            it.onSuccess (list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(observer)
    }

    override fun onCleared() {
        super.onCleared()
        dis.clear()
    }

}