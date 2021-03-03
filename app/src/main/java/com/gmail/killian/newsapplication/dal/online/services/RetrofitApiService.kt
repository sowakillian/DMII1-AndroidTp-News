package com.gmail.killian.newsapplication.dal.online.services

import com.gmail.killian.newsapplication.dal.online.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    // GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut apeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("everything")
    fun getArticles(@Query("q") query: String): Call<ArticleResponse>
}