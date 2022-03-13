package com.lgomez.movies.di

import com.lgomez.movies.data.repositories.MoviesRepositoryImpl
import com.lgomez.movies.domain.repositories.MoviesRepository
import com.lgomez.movies.domain.usecases.GetDetailsMovieUseCase
import com.lgomez.movies.domain.usecases.GetPopularMoviesUseCase
import com.lgomez.movies.ui.viewmodels.MoviesDetailViewModel
import com.lgomez.movies.ui.viewmodels.MoviesMasterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Data- Repository provides
    @Provides
    fun provideMoviesRepository(): MoviesRepository = MoviesRepositoryImpl()


    // Usecases provides
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(moviesRepository)

    @Provides
    fun provideGetDetailsMovieUseCase(moviesRepository: MoviesRepository): GetDetailsMovieUseCase =
        GetDetailsMovieUseCase(moviesRepository)


    // Viewmodel provides
    @Provides
    fun provideMoviesMasterViewModel(getPopularMoviesUseCase: GetPopularMoviesUseCase): MoviesMasterViewModel =
        MoviesMasterViewModel(getPopularMoviesUseCase)

    @Provides
    fun provideMoviesDetailViewModel(getDetailsMovieUseCase: GetDetailsMovieUseCase): MoviesDetailViewModel =
        MoviesDetailViewModel(getDetailsMovieUseCase)

}