package com.elijahverdoorn.nyt.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
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

        // Associate searchable configuration with the SearchView
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchStories(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?) = false
            })
        }
        return true
    }
}

