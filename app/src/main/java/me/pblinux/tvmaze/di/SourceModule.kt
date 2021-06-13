package me.pblinux.tvmaze.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import me.pblinux.tvmaze.data.repository.TVMazeRepository
import me.pblinux.tvmaze.data.source.local.TVDatabase
import me.pblinux.tvmaze.data.source.remote.TVMazeService
import me.pblinux.tvmaze.data.source.remote.TVMazeSource
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@ExperimentalSerializationApi
@Module
@InstallIn(ViewModelComponent::class)
object SourceModule {
    private const val baseURL = "https://api.tvmaze.com"
    private val contentType = "application/json".toMediaType()

    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun providesJsonConverter(): Converter.Factory {
        return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(providesHttpClient())
            .baseUrl(baseURL)
            .addConverterFactory(providesJsonConverter())
            .build()
    }

    @Provides
    fun providesTVMazeSource(): TVMazeSource {
        return TVMazeSource(providesRetrofit().create(TVMazeService::class.java))
    }

    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context): TVDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TVDatabase::class.java, "tv-maze"
        ).build()
    }

    @Provides
    fun providesTVMazeRepository(@ApplicationContext applicationContext: Context): TVMazeRepository {
        return TVMazeRepository(providesTVMazeSource(), providesDatabase(applicationContext))
    }
}