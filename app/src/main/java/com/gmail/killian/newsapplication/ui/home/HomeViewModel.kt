package com.gmail.killian.newsapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.killian.newsapplication.models.Article
import com.gmail.killian.newsapplication.repositories.ArticleRepository

class HomeViewModel : ViewModel() {

    private val repository: ArticleRepository = ArticleRepository.getInstance()
    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()

    fun loadArticles(query: String): LiveData<List<Article>> {
        return repository.getArticles(query)
    }
}
