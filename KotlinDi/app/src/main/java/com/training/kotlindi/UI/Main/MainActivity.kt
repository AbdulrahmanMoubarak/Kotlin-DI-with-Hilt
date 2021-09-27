package com.training.kotlindi.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.kotlindi.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: NetworkViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.getTweets()

        val adapter = TweetAdapter2()
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setAdapter(adapter)
        }

        vm.liveTweets.observe(this, {
            Log.d("View List", "onCreate: $it")
            adapter.setTweet_List(it)
            //recreate()
        })


    }
}