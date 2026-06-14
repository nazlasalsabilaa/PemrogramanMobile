package com.nazlasalsabila.global_icons

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String
    ): MovieResponse

    companion object {
        const val API_KEY = "b6ee0e40311ff58fd7984fcca38bd3f0"
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        val instance: TmdbApi by lazy {
            val json = Json { ignoreUnknownKeys = true }
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(TmdbApi::class.java)
        }
    }
}