package com.training.kotlindi.data

import android.util.Log
import com.training.kotlindi.models.FakeTweetModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.ArrayList
import javax.inject.Inject

class HttpClient
@Inject
constructor(var conn: HttpURLConnection, var url: URL){

    fun MakeServiceCall(reqUrl: String): List<FakeTweetModel>{
        var response = ""
        try {
            conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            // read the response
            val inStream: InputStream = BufferedInputStream(conn.inputStream)
            response = convertStreamToString(inStream)
            conn.disconnect()
        } catch (e: MalformedURLException) {
            Log.e("here",
                "MalformedURLException: " + e.message
            )
        } catch (e: ProtocolException) {
            Log.e("here", "ProtocolException: " + e.message)
        } catch (e: IOException) {
            Log.e("here", "IOException: " + e.message)
        } catch (e: Exception) {
            Log.e("here", "Exception: " + e.message)
        }
        return parseJson("{\n\"posts\": $response\n}")
    }

    private fun convertStreamToString(inStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inStream))
        val sb = StringBuilder()

        var line: String?
        try {
            while ((reader.readLine().also { line = it }) != null) {
                sb.append(line).append('\n')
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }

    private fun parseJson(jsonStr: String): List<FakeTweetModel> {
        val tweetList: MutableList<FakeTweetModel> = ArrayList()
        if (jsonStr != null) {
            try {
                val jsonObj = JSONObject(jsonStr)
                Log.d("here", "parseJson: got object")
                val tweets: JSONArray = jsonObj.getJSONArray("posts")
                Log.d("here", "parseJson: got array")
                for (i in 0 until tweets.length()) {
                    val tweet = tweets.getJSONObject(i)
                    tweetList.add(
                        FakeTweetModel(
                            tweet.getInt("userId"),
                            tweet.getString("title"), tweet.getString("body")
                        )
                    )
                }
            } catch (e: JSONException) {
                Log.d(
                    "here",
                    "parseJson: Error parsing this message"
                )
            }
        }
        return tweetList
    }
}