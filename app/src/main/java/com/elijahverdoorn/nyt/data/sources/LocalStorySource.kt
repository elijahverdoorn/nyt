package com.elijahverdoorn.nyt.data.sources

import androidx.lifecycle.MutableLiveData
import com.elijahverdoorn.nyt.data.models.Story

data class LocalStorySource(
    var stories: MutableLiveData<List<Story>>? = null,
    var allStories: List<Story>? = null)
