package com.github.rexfilius.movieviewer.di

import android.content.Context
import androidx.room.Room
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import com.github.rexfilius.movieviewer.data.repositories.Repository
import com.github.rexfilius.movieviewer.data.source.local.LocalDataSource
import com.github.rexfilius.movieviewer.data.source.local.MovieDao
import com.github.rexfilius.movieviewer.data.source.local.MovieDatabase
import com.github.rexfilius.movieviewer.data.source.local.MovieLocalSource
import com.github.rexfilius.movieviewer.data.source.remote.MovieAPI
import com.github.rexfilius.movieviewer.data.source.remote.MovieRemoteSource
import com.github.rexfilius.movieviewer.data.source.remote.RemoteDataSource
import com.github.rexfilius.movieviewer.util.Constants
import com.github.rexfilius.movieviewer.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider =
        object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }

    @Provides
    @Singleton
    fun provideMovieRemoteSource(
        api: MovieAPI
    ): RemoteDataSource = MovieRemoteSource(api)

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteSource: RemoteDataSource,
        movieLocalSource: LocalDataSource,
        dispatcher: DispatcherProvider
    ): Repository = MovieRepository(movieRemoteSource, movieLocalSource, dispatcher)

    @Provides
    @Singleton
    fun provideMovieLocalSource(
        movieDao: MovieDao,
        dispatcher: DispatcherProvider
    ): LocalDataSource = MovieLocalSource(movieDao, dispatcher)

    @Provides
    @Singleton
    fun provideMovieDao(
        movieDatabase: MovieDatabase
    ): MovieDao = movieDatabase.movieDao

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies_database"
        ).build()
    }

}