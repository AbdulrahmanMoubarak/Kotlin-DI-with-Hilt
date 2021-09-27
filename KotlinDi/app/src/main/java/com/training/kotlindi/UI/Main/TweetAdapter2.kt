package com.training.kotlindi.UI.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.training.kotlindi.R
import com.training.kotlindi.models.FakeTweetModel

class TweetAdapter2() : RecyclerView.Adapter<TweetAdapter2.TweetViewHolder>() {
    private var tweet_list = ArrayList<FakeTweetModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.title.setText(tweet_list.get(position).title)
        holder.user.setText(tweet_list.get(position).userId.toString())
        holder.body.setText(tweet_list.get(position).body)
    }

    override fun getItemCount(): Int {
        return tweet_list.size
        notifyDataSetChanged()
    }

    fun setTweet_List(list: List<FakeTweetModel>){
        tweet_list = list as ArrayList<FakeTweetModel>
    }

    class TweetViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var user: TextView
        var body: TextView

        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.title_textView)
            user = itemView.findViewById(R.id.user_textView2)
            body = itemView.findViewById(R.id.body_textView3)
        }


    }


}