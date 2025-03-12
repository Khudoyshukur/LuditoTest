package com.ludito.test.data.di

import android.content.Context
import com.ludito.test.data.local.LuditoDatabase
import com.ludito.test.data.local.dao.AddressDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:01 AM
 * Email: Kjuraev.001@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LuditoDatabase {
        return LuditoDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAddressDao(db: LuditoDatabase): AddressDao {
        return db.addressDao
    }
}