package com.elijahverdoorn.nyt.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.adapters.StoryListAdapter
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.util.StoryItemClickListener
import com.elijahverdoorn.nyt.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        viewModel.stories.observe(this, Observer {
            view.findViewById<RecyclerView>(R.id.storyListRecyclerView).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = StoryListAdapter(context!!, it, object: StoryItemClickListener {
                    override fun navigateToStory(story: Story) {
                        viewModel.navigateToDetail(activity, story)
                    }
                })
            }
        })
        return view
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}