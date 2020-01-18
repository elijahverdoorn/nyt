package com.elijahverdoorn.nyt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.fragments.HomeFragment
import com.elijahverdoorn.nyt.viewmodels.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, HomeFragment(), HomeFragment.Companion.TAG)
            .commit()

    }
}
