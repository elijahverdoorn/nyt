package com.elijahverdoorn.nyt.activities

import android.os.Bundle
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

        // Set up custom toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        // Hide default toolbar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            // If we're coming in for the first time, start the home fragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, HomeFragment(), HomeFragment.TAG)
                .commit()
        }
    }
}

