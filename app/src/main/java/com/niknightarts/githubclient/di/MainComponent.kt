package com.niknightarts.githubclient.di

import com.niknightarts.githubclient.ui.MainActivity
import com.niknightarts.githubclient.ui.repositories.RepositoryListFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = []
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(repositoryListFragment: RepositoryListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}
