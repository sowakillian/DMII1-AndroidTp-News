package com.gmail.killian.newsapplication.repositories

import androidx.lifecycle.LiveData
import com.gmail.killian.newsapplication.dal.NewsDataSource
import com.gmail.killian.newsapplication.dal.online.ArticleOnlineDataSource
import com.gmail.killian.newsapplication.models.Article

class ArticleRepository private constructor() {
    private val dataSource: NewsDataSource = ArticleOnlineDataSource()

    fun getArticles(query: String): LiveData<List<Article>> {
        return dataSource.getArticles(query = query)
    }

    companion object {
        private var instance: ArticleRepository? = null
        fun getInstance(): ArticleRepository {
            if (instance == null) {
                instance = ArticleRepository()
            }
            return instance!!
        }
    }
}
