package com.elijahverdoorn.nyt

import android.app.Application
import com.elijahverdoorn.nyt.di.nytModule
import com.elijahverdoorn.nyt.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NytApplication: Application() {
    init {
        startKoin {
            androidContext(this@NytApplication)
            modules(listOf(nytModule, vmModule))
        }
    }
}