package com.elijahverdoorn.nyt.util

import com.elijahverdoorn.nyt.data.models.Story

interface StoryItemClickListener {
    fun navigateToStory(story: Story)
}