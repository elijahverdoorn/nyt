package com.elijahverdoorn.nyt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.util.StoryItemClickListener

class StoryListAdapter(val context: Context, val stories: List<Story>, val clickListener: StoryItemClickListener) :
    RecyclerView.Adapter<StoryListAdapter.StoryViewHolder>() {
    class StoryViewHolder(storyView: View) : RecyclerView.ViewHolder(storyView) {
        val storyListItemContainer: View = storyView
        val storyTitleTextView: TextView = storyView.findViewById(R.id.articleTitleTextView)
        val storyLinkTextView: TextView = storyView.findViewById(R.id.articleLinkTextView)
        val storyThumbnailImageView: ImageView = storyView.findViewById(R.id.articleThumbnailImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view =
            (LayoutInflater.from(parent.context).inflate(R.layout.component_home_list_item, parent, false))
        return StoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.storyTitleTextView.text = stories[position].title
        holder.storyLinkTextView.text = stories[position].short_url?:stories[position].url
        Glide.with(context)
            .load(stories[position].multimedia?.first()?.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.storyThumbnailImageView)

        holder.storyListItemContainer.setOnClickListener {
            clickListener.navigateToStory(stories[position])
        }
    }
}
