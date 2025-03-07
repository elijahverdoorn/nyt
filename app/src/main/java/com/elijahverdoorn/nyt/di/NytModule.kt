package com.elijahverdoorn.nyt.di

import com.elijahverdoorn.nyt.data.repositories.StoryRepository
import com.elijahverdoorn.nyt.data.sources.LocalStorySource
import com.elijahverdoorn.nyt.data.sources.RemoteStoryServiceFactory
import com.elijahverdoorn.nyt.viewmodels.DetailViewModel
import com.elijahverdoorn.nyt.viewmodels.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nytModule = module {
    factory { RemoteStoryServiceFactory().create() }
    factory { LocalStorySource() }
    single { StoryRepository(get(), get()) }
}

val vmModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel() }
}