package com.gmail.killian.newsapplication.dal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.killian.newsapplication.R
import com.gmail.killian.newsapplication.databinding.ArticleItemBinding
import com.gmail.killian.newsapplication.models.Article

class ListArticlesAdapter(
    items: List<Article>
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {
    private val mArticles: List<Article> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ArticleItemBinding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles[position]
        val context = holder.binding.root.context

        println("im in article")
        Glide.with(context)
            .load(article.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo_placeholder)
            .error(R.drawable.photo_placeholder)
            .skipMemoryCache(false)
            .into(holder.binding.imageItemIv)
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    class ViewHolder(val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}