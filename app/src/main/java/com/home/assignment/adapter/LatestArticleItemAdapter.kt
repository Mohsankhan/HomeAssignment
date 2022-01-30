package com.home.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.home.assignment.R
import com.home.assignment.common.Utils
import com.home.assignment.model.LatestArticle
import com.home.assignment.model.MyDiffUtil


class LatestArticleItemAdapter(
    private val context: Context,
    private var latestArticleResults: List<LatestArticle.Result> = emptyList(),
    private var listener: ArticleItemClickListener? = null,
) : RecyclerView.Adapter<LatestArticleItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.item_latest_article_item,
            viewGroup,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return latestArticleResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(latestArticleResults.get(position))
    }

    fun updateArticleList(articles: List<LatestArticle.Result> ) {
        val diffCallback = MyDiffUtil(this.latestArticleResults, articles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.latestArticleResults = articles
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.title_label)
        private val abstract: TextView = itemView.findViewById(R.id.abstract_label)
        private val publishedDate: TextView = itemView.findViewById(R.id.time_ago_publish_date)
        private val articleThumbnail: ImageView = itemView.findViewById(R.id.image)



        fun bindData(item:LatestArticle.Result ) {
            title.text = item.title
            abstract.text = item.abstract
            publishedDate.text = "Published date :"+TimeAgo.using(Utils.dateToMilliSeconds(item.published_date)).toString()
            item.media.find {it.type.equals("image") }?.let {
                it?.mediaMetadata?.let { if(it.size>0){
                    articleThumbnail.visibility = View.VISIBLE

                    val options: RequestOptions = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)

                    Glide.with(context).load(it?.get(0).url).apply(options)
                        .into(articleThumbnail)
                }else{
                    articleThumbnail.visibility = View.GONE
                }
                    itemView.setOnClickListener({
                        listener?.onArticleClick(item.url)
                    })

                }
            }
        }

    }

    interface ArticleItemClickListener {
        fun onArticleClick(url:String)
    }
}