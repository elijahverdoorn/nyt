package com.elijahverdoorn.nyt.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.fragments.HomeFragment
import com.elijahverdoorn.nyt.viewmodels.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, HomeFragment(), HomeFragment.TAG)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        // Handle user interaction on search bar
        menu.findItem(R.id.search).let {
            it.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?) = true

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    viewModel.showAllStories()
                    return true
                }
            })
            (it.actionView as SearchView).apply {
                setOnCloseListener(object : SearchView.OnCloseListener {
                    override fun onClose(): Boolean {
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
        return true
    }
}

