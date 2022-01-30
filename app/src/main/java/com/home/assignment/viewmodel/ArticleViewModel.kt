package com.home.assignment.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.home.assignment.repository.ArticleRepository
import androidx.lifecycle.ViewModel
import club.aashiyana.app.network.ApiService
import club.home.assignment.network.ApiClient
import com.google.gson.JsonElement
import com.home.assignment.callback.FetchLatestArticleCallBack
import com.home.assignment.model.LatestArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {
    var latestArticleLiveData: MutableLiveData<LatestArticle> = MutableLiveData()
    fun getLatestArticles() {
        articleRepository.fetchLatestArticles(object : FetchLatestArticleCallBack {
            override fun onSuccess(latestArticle: LatestArticle) {
                latestArticleLiveData?.value = latestArticle
            }

            override fun onFailure(reason: String) {
                latestArticleLiveData = MutableLiveData()
            }

        })
    }

}