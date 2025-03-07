package com.elijahverdoorn.nyt.data.sources

import com.elijahverdoorn.nyt.data.models.ApiResponse
import retrofit2.http.GET

interface RemoteStoryService {
    @GET("/svc/topstories/v2/world.json?api-key=API_KEY_HERE")
    suspend fun fetchStories(): ApiResponse
}
