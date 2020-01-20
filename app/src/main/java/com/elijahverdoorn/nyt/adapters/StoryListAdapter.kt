package com.elijahverdoorn.nyt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.util.StoryItemClickListener

class StoryListAdapter(val context: Context,
                       val stories: List<Story>,
                       val clickListener: StoryItemClickListener) : RecyclerView.Adapter<StoryListAdapter.StoryViewHolder>() {

    class StoryViewHolder(storyView: View) : RecyclerView.ViewHolder(storyView) {
        val storyListItemContainer: View = storyView
        val storyTitleTextView: TextView = storyView.findViewById(R.id.articleTitleTextView)
        val storyLinkTextView: TextView = storyView.findViewById(R.id.articleLinkTextView)
        val storyThumbnailImageView: ImageView = storyView.findViewById(R.id.articleThumbnailImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = StoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.component_home_list_item, parent, false))

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        stories[position].let { thisStory ->
            holder.storyTitleTextView.text = thisStory.title
            holder.storyLinkTextView.text = thisStory.short_url ?: thisStory.url
            Glide.with(context)
                .load(thisStory.multimedia?.first()?.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.storyThumbnailImageView)

            holder.storyListItemContainer.setOnClickListener {
                clickListener.navigateToStory(thisStory)
            }
        }
    }
}
