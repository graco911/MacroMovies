package com.gracodev.macromovies.modules

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.gracodev.data.database.AppDatabase
import com.gracodev.data.database.IMovieRoom
import com.gracodev.data.database.MovieRoomDataSource
import com.gracodev.data.database.MovieRoomDatabase
import com.gracodev.data.remote.IMovieAPI
import com.gracodev.data.remote.MovieAPI
import com.gracodev.data.remote.MovieAPIDataSource
import com.gracodev.data.remote.RetrofitMovieAPI
import com.gracodev.data.repository.MoviePagingRepository
import com.gracodev.data.repository.MovieRepository
import com.gracodev.domain.usecase.FetchMovieListUseCase
import com.gracodev.domain.usecase.FetchMoviePagingListUseCase
import com.gracodev.macromovies.factories.MovieViewModelFactory
import com.gracodev.macromovies.utils.ApiKeyInterceptor
import com.gracodev.macromovies.utils.LanguageInterceptor
import com.gracodev.macromovies.viewmodels.MovieViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createAppModules(): Module = module {

    single {
        createWebService<MovieAPI>(
            okHttpClient = createHttpClient(get()),
            factory = CoroutineCallAdapterFactory(),
            baseUrl = ""
        )
    }

    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java,
            "movie_db"
        ).build()
    }

    single {
        val database = get<AppDatabase>()
        database.movieDAO()
    }

    single { Dispatchers.IO }

    single<IMovieAPI> {
        RetrofitMovieAPI(get())
    }
    single<IMovieRoom> {
        MovieRoomDatabase(get())
    }

    single { MovieAPIDataSource(get(), get()) }
    single { MovieRoomDataSource(get(), get()) }
    single { MovieRepository(get()) }
    single { MoviePagingRepository(get()) }
    single { FetchMovieListUseCase(get(), get()) }
    single { FetchMoviePagingListUseCase(get(), get()) }

    factory { MovieViewModelFactory(get(), get()) }

    viewModel{MovieViewModel(get(), get())}
}

fun createHttpClient(context: Context): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .addInterceptor(interceptor)
        .addInterceptor(ApiKeyInterceptor("c0823934438075d63f1dbda4023e76fc"))
        .addInterceptor(LanguageInterceptor())
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
