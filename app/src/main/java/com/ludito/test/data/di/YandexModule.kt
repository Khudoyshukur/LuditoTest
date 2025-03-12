package com.ludito.test.data.di

import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 10:25 PM
 * Email: Kjuraev.001@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object YandexModule {

    @Singleton
    @Provides
    fun provideSearchManager(): SearchManager {
        return SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
    }
}