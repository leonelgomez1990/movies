package com.lgomez.movies.di

import com.lgomez.movies.data.network.MoviesNetworkDataSource
import com.lgomez.movies.data.network.MoviesProvider
import com.lgomez.movies.data.repositories.MoviesRepositoryImpl
import com.lgomez.movies.domain.repositories.MoviesRepository
import com.lgomez.movies.domain.usecases.ConfigureLanguage
import com.lgomez.movies.domain.usecases.GetDetailsMovieUseCase
import com.lgomez.movies.domain.usecases.GetPopularMoviesUseCase
import com.lgomez.movies.ui.viewmodels.MoviesDetailViewModel
import com.lgomez.movies.ui.viewmodels.MoviesMasterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://api.themoviedb.org/3/".toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providerMoviesProvider(retrofit: Retrofit): MoviesProvider =
        retrofit.create(MoviesProvider::class.java)

    // Framework- DataSource provides
    @Provides
    @Singleton
    fun provideMoviesNetworkDataSource(moviesProvider: MoviesProvider): MoviesNetworkDataSource =
        MoviesNetworkDataSource(moviesProvider)


    // Data- Repository provides
    @Provides
    @Singleton
    fun provideMoviesRepository(moviesNetworkDataSource: MoviesNetworkDataSource): MoviesRepository =
        MoviesRepositoryImpl(moviesNetworkDataSource)


    // Usecases provides
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(moviesRepository)

    @Provides
    fun provideGetDetailsMovieUseCase(moviesRepository: MoviesRepository): GetDetailsMovieUseCase =
        GetDetailsMovieUseCase(moviesRepository)

    @Provides
    fun provideConfigureLanguage(moviesRepository: MoviesRepository): ConfigureLanguage =
        ConfigureLanguage(moviesRepository)

    // Viewmodel provides
    @Provides
    fun provideMoviesMasterViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        configureLanguage: ConfigureLanguage
    ): MoviesMasterViewModel =
        MoviesMasterViewModel(getPopularMoviesUseCase, configureLanguage)

    @Provides
    fun provideMoviesDetailViewModel(
        getDetailsMovieUseCase: GetDetailsMovieUseCase
    ): MoviesDetailViewModel =
        MoviesDetailViewModel(getDetailsMovieUseCase)

}