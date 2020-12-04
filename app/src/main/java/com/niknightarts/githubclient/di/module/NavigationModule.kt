package com.niknightarts.githubclient.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
object NavigationModule {

    private var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    fun provideRouter(): Router = cicerone.router
}
