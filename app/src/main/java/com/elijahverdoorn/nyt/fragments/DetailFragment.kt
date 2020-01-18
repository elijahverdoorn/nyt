package com.elijahverdoorn.nyt.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.viewmodels.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment(val story: Story): Fragment() {
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        Glide.with(context!!)
            .load(story.multimedia.first().url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view.findViewById<ImageView>(R.id.articleImageView))

        view.findViewById<TextView>(R.id.articleTitleTextView).apply {
            text = story.title
        }

        view.findViewById<TextView>(R.id.articleAbstract).apply {
            text = story.abstract
        }

        view.findViewById<Button>(R.id.shareButton).apply {
            setOnClickListener {
                startActivity(viewModel.buildShareIntent(story))
            }
        }

        return view
    }

    companion object {
        const val TAG = "DetailFragment"
    }
}