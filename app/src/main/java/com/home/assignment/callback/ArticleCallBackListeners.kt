package com.home.assignment.callback

import com.home.assignment.model.LatestArticle

interface FetchLatestArticleCallBack {
    fun onSuccess(latestArticle: LatestArticle)
    fun onFailure(reason: String)
}


