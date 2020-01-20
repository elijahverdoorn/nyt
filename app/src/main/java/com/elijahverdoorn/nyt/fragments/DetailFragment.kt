package com.elijahverdoorn.nyt.fragments

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.viewmodels.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment(val story: Story): Fragment() {
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.empty_option_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        view.findViewById<ImageView>(R.id.articleImageView).let { imgView ->
            val image = story.multimedia?.filter { it.format == "Normal" }?.first()
            Glide.with(context!!)
                .load(image?.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgView)
            imgView.contentDescription = image?.caption
            imgView.maxHeight = image?.height?:0
        }

        view.findViewById<TextView>(R.id.articleTitleTextView).apply {
            text = story.title
        }

        view.findViewById<TextView>(R.id.articleAbstract).apply {
            text = story.abstract
        }

        view.findViewById<ImageButton>(R.id.shareButton).apply {
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