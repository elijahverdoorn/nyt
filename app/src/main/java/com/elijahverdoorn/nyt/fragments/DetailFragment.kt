package com.elijahverdoorn.nyt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.viewmodels.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment: Fragment() {
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "DetailFragment"
    }
}