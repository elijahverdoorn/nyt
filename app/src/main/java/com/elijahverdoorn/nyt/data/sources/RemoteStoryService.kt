package com.elijahverdoorn.nyt.data.sources

import com.elijahverdoorn.nyt.data.models.ApiResponse
import retrofit2.http.GET

interface RemoteStoryService {
    @GET("/svc/topstories/v2/world.json?api-key=OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
    suspend fun fetchStories(): ApiResponse
}