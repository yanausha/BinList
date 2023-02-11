package com.example.binlist.di

import com.example.binlist.data.network.ApiFactory
import com.example.binlist.data.network.ApiService
import com.example.binlist.data.repository.BinRepositoryImpl
import com.example.binlist.domain.BinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindBinRepository(impl: BinRepositoryImpl): BinRepository

    companion object {

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}