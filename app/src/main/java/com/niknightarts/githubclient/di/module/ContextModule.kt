package com.niknightarts.githubclient.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideApplication() = application
}
