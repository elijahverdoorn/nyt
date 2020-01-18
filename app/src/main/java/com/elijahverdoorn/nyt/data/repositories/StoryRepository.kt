package com.elijahverdoorn.nyt.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.sources.LocalStorySource
import com.elijahverdoorn.nyt.data.sources.RemoteStoryService

class StoryRepository(
    val localStorySource: LocalStorySource,
    val remoteStoryService: RemoteStoryService
) {
    fun getStories() = liveData {
        val cachedData = localStorySource.stories
        if (cachedData != null) {
            emitSource(cachedData)
        }

        val data = MutableLiveData<List<Story>>()
        localStorySource.stories = data
        data.value = remoteStoryService.fetchStories().results
        Log.d("ASDF", "ASDF")

        emitSource(data)
    }

}