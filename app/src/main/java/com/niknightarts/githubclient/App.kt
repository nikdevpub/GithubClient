package com.niknightarts.githubclient

import android.app.Application
import com.niknightarts.githubclient.di.AppComponent
import com.niknightarts.githubclient.di.DaggerAppComponent
import com.niknightarts.githubclient.di.MainComponent
import com.niknightarts.githubclient.di.module.ContextModule
import com.niknightarts.githubclient.di.module.NavigationModule
import timber.log.Timber

class App : Application() {
    companion object {
        private lateinit var instance: App
        private var appComponent: AppComponent? = null
        private var mainComponent: MainComponent? = null

        fun appComponent(): AppComponent {
            checkNotNull(appComponent) { "AppComponent should be initialized!" }
            return appComponent as AppComponent
        }

        // MAIN
        fun initMainComponent(): MainComponent? {
            if (mainComponent != null) return mainComponent

            mainComponent = appComponent!!.mainComponentBuilder()
                .build()
            return mainComponent
        }

        fun mainComponent(): MainComponent {
            checkNotNull(mainComponent) { "Main component should be initialized!" }

            return mainComponent as MainComponent
        }

        fun destroyMainComponent() {
            mainComponent = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initAppComponent()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(instance))
            .navigationModule(NavigationModule)
            .build()
    }
}
