package com.elijahverdoorn.nyt.data.sources

import com.elijahverdoorn.nyt.service.RetrofitService

class RemoteStoryServiceFactory {
    fun create() = RetrofitService.create(RemoteStoryService::class.java, STORY_BASE_URL)

    companion object {
        private const val STORY_BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
        private const val API_KEY = "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj"
    }
}