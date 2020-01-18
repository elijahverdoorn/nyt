package com.elijahverdoorn.nyt.viewmodels

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.elijahverdoorn.nyt.R
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.repositories.StoryRepository
import com.elijahverdoorn.nyt.fragments.DetailFragment
import kotlinx.coroutines.launch

class HomeViewModel(
    private val storyRepository: StoryRepository
): ViewModel() {
    var stories = storyRepository.getStories()

    fun navigateToDetail(activity: FragmentActivity?, story: Story) {
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit)
            ?.replace(R.id.main_container, DetailFragment(story), DetailFragment.TAG)
            ?.addToBackStack(null)
            ?.commit()
    }
}