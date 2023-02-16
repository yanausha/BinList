package com.example.binlist.di

import android.app.Application
import com.example.binlist.data.database.AppDatabase
import com.example.binlist.data.database.BinInfoDao
import com.example.binlist.data.network.ApiFactory
import com.example.binlist.data.network.ApiService
import com.example.binlist.data.repository.BinRepositoryImpl
import com.example.binlist.domain.BinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindBinRepository(impl: BinRepositoryImpl): BinRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideBinInfoDao(application: Application): BinInfoDao {
            return AppDatabase.getInstance(application).binInfoDao()
        }
    }
}