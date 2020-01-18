package com.elijahverdoorn.nyt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.repositories.StoryRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val storyRepository: StoryRepository
): ViewModel() {
    var stories = storyRepository.getStories()
}