package com.gmail.killian.newsapplication.dal.online

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.killian.newsapplication.dal.NewsDataSource
import com.gmail.killian.newsapplication.dal.online.services.RetrofitApiService
import com.gmail.killian.newsapplication.dal.utils.toArticle
import com.gmail.killian.newsapplication.models.Article
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class ArticleOnlineDataSource : NewsDataSource {
    private val service: RetrofitApiService

    init {
        val retrofit = buildClient()
        // Init the api service with retrofit
        service = retrofit.create(RetrofitApiService::class.java)
    }

    /**
     * Configure retrofit
     */

    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()
        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Add a logger to the client so that we log every request
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    /**
     * Intercept every request to the API and automatically add
     * the api key as query parameter
     */
    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(
            Interceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()

                chain.proceed(request)
            }
        )
    }

    override fun getArticles(query: String): LiveData<List<Article>> {
        val _data = MutableLiveData<List<Article>> ()

        Executors.newSingleThreadExecutor().execute {
            val articleList = service.getArticles(query = query).execute().body()?.articles ?: listOf()

            // TODO Convertir la liste des articles du modèle du web service vers le modèle métier ArcicleItem --> Article

            val articles = articleList.map {
                it.toArticle()
            }

            _data.postValue(articles)
        }
        return _data
    }

    companion object {
        private const val apiKey = "9b94cc25989d42f79277f9536abd6bc8"
        private const val apiUrl = "https://newsapi.org/v2/"
    }
}
