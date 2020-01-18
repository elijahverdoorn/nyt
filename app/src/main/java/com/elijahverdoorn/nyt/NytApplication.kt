package com.elijahverdoorn.nyt

import android.app.Application
import android.util.Log
import androidx.lifecycle.liveData
import com.elijahverdoorn.nyt.data.models.Story
import com.elijahverdoorn.nyt.data.repositories.StoryRepository
import com.elijahverdoorn.nyt.data.sources.LocalStorySource
import com.elijahverdoorn.nyt.data.sources.RemoteStoryServiceFactory
import com.elijahverdoorn.nyt.di.nytModule
import com.elijahverdoorn.nyt.di.vmModule
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NytApplication: Application() {

    init {
        startKoin {
            androidContext(this@NytApplication)
            modules(listOf(nytModule, vmModule))
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}