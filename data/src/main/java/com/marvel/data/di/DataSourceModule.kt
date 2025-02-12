package com.marvel.data.di

import com.marvel.data.datasource.CharacterByTypeDataSource
import com.marvel.data.datasource.CharacterByTypeDataSourceImpl
import com.marvel.data.datasource.CharactersDataSource
import com.marvel.data.datasource.CharactersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideCharactersDataSource(remoteDS: CharactersDataSourceImpl): CharactersDataSource

    @Binds
    abstract fun provideCharacterByTypeDataSource(remoteDS: CharacterByTypeDataSourceImpl): CharacterByTypeDataSource

}
