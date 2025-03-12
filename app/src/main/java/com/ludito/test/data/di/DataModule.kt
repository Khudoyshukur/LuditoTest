package com.ludito.test.data.di

import com.ludito.test.data.repository.AddressBookRepository
import com.ludito.test.data.repository.SearchRepository
import com.ludito.test.data.repository.impl.AddressBookRepositoryImpl
import com.ludito.test.data.repository.impl.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 10:20 PM
 * Email: Kjuraev.001@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindSearchRepo(impl: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    abstract fun bindAddressBookRepo(impl: AddressBookRepositoryImpl): AddressBookRepository
}