package com.niknightarts.githubclient.di

import com.niknightarts.githubclient.di.module.ContextModule
import com.niknightarts.githubclient.di.module.NavigationModule
import com.niknightarts.githubclient.di.module.RepositoryModule
import com.niknightarts.githubclient.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun mainComponentBuilder(): MainComponent.Builder
}
