package com.elijahverdoorn.nyt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.repositories.StoryRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val storyRepository: StoryRepository
): ViewModel() {
    var latestData: List<Story>? = null

    fun data() {
        viewModelScope.launch {
            storyRepository.getStories().observeForever {
                latestData = it
            }
        }
    }
}