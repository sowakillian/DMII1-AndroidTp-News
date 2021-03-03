package com.gmail.killian.newsapplication.dal.utils

import com.gmail.killian.newsapplication.dal.online.models.ItemResponse
import com.gmail.killian.newsapplication.dal.online.models.SourceResponse
import com.gmail.killian.newsapplication.models.Article
import com.gmail.killian.newsapplication.models.Source

fun ItemResponse.toArticle() = Article(
    author = author ?: "",
    content = content ?: "",
    title = title ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    source = source?.toSource() ?: null,
    url = url ?: "",
    urlToImage = urlToImage ?: "",
)

fun SourceResponse.toSource() = Source(
    id = id ?: "",
    name = name ?: ""
)