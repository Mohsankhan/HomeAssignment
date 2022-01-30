package com.home.assignment.repository
import android.util.Log
import club.aashiyana.app.network.ApiService
import club.home.assignment.network.ApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.home.assignment.MyApplication
import com.home.assignment.callback.FetchLatestArticleCallBack
import com.home.assignment.model.LatestArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository(private val application: MyApplication,private val gson: Gson) {

    fun fetchLatestArticles(fetchLatestArticleCallBack: FetchLatestArticleCallBack) {

        ApiClient.getClient(application)?.create(ApiService::class.java)
            ?.getLatestArticles()!!
            .enqueue(object : Callback<JsonElement> {
                override fun onResponse(
                    call: Call<JsonElement>,
                    response: Response<JsonElement>
                ) {
                    response?.let {
                        val jsonObj = response.body()!!.asJsonObject
                        Log.i("TAG", "onResponse: "+jsonObj)
                        val status = jsonObj.get("status").asString
                        if (status.equals("OK")) {
                            val latestArticle = gson.fromJson(jsonObj.toString(), LatestArticle::class.java)
                            fetchLatestArticleCallBack.onSuccess(latestArticle)
                        } else {
                            fetchLatestArticleCallBack.onFailure("Not have any Fitness centers")
                        }
                    }

                }

                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    fetchLatestArticleCallBack.onFailure("${t.localizedMessage}")
                }
            })

    }

}