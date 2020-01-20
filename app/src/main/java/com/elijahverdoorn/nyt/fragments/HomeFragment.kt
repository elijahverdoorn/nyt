package com.elijahverdoorn.nyt.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.adapters.StoryListAdapter
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.util.StoryItemClickListener
import com.elijahverdoorn.nyt.viewmodels.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        viewModel.stories.observe(this, Observer {
            view.findViewById<RecyclerView>(R.id.storyListRecyclerView)?.apply {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL)) // Lines between elements of RV

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.options_menu, menu)

        // Handle user interaction on search bar
        menu?.findItem(R.id.search).let {
            it?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?) = true

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    // When user closes search we want to go back to showing all stories
                    viewModel.showAllStories()
                    return true
                }
            })
            (it?.actionView as SearchView).apply {
                setOnCloseListener(object : SearchView.OnCloseListener {
                    override fun onClose(): Boolean {
                        // When user closes search we want to go back to showing all stories
                        viewModel.showAllStories()
                        return false
                    }
                })

                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            viewModel.searchStories(it)
                        }

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            viewModel.searchStories(it)
                        }
                        return true
                    }
                })
            }
        }
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}