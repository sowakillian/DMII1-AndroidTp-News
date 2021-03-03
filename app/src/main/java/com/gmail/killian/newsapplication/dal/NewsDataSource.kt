package com.gmail.killian.newsapplication.dal

import androidx.lifecycle.LiveData
import com.gmail.killian.newsapplication.models.Article

interface NewsDataSource {
    fun getArticles(query: String): LiveData<List<Article>>
}
