package com.elijahverdoorn.nyt.viewmodels

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.elijahverdoorn.nyt.data.models.Story

class DetailViewModel: ViewModel() {
    // Navigate up the fragment back stack
    fun back(fragment: Fragment) {
        fragment.fragmentManager?.popBackStack()
    }

    fun buildShareIntent(story: Story): Intent {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, story.url)
            type = "text/plain"
        }

        return Intent.createChooser(intent, "Share this Article")
    }
}