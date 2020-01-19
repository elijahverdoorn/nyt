package com.elijahverdoorn.nyt.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.sources.LocalStorySource
import com.elijahverdoorn.nyt.data.sources.RemoteStoryService

class StoryRepository(
    val localStorySource: LocalStorySource,
    val remoteStoryService: RemoteStoryService
) {
    lateinit var liveData: MutableLiveData<List<Story>>

    fun getStories() = liveData {
        val cachedData = localStorySource.stories
        if (cachedData != null) {
            emitSource(cachedData)
        } else {
            val data = MutableLiveData<List<Story>>()
            localStorySource.stories = data
            liveData = data
            val apiResponse = remoteStoryService.fetchStories()
            data.value = apiResponse.results

            emitSource(data)
        }
    }

    fun searchStories(query: String) {
        liveData.value = liveData.value?.filter {
            it.title.contains(query)
        }
    }
}