package com.elijahverdoorn.nyt.api

import com.elijahverdoorn.nyt.data.models.Story

interface TopStoriesApi {
    suspend fun getTopStories(): List<Story>
}