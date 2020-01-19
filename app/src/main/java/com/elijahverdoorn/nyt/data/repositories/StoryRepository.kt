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
            remoteStoryService.fetchStories().results.let { stories ->
                MutableLiveData<List<Story>>().let {
                    localStorySource.stories = it
                    liveData = it
                    it.value = stories
                    emitSource(it)
                }
                localStorySource.allStories = stories
            }
        }
    }

    fun searchStories(query: String) {
        liveData.value = localStorySource.allStories?.filter {
            it.title.toLowerCase().contains(query.toLowerCase())
        }
    }
}