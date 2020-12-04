package com.niknightarts.githubclient.di.module

import com.niknightarts.githubclient.data.repository.GithubRepository
import com.niknightarts.githubclient.data.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun provideRepository(githubRepository: GithubRepository): Repository
}
