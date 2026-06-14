package com.nazlasalsabila.global_icons

import kotlinx.coroutines.flow.flow

class GlobalIconRepository(
    private val api: TmdbApi,
    private val dao: GlobalIconDao,
    private val lang: String
) {
    fun getMovies() = flow {
        dao.deleteAll()
        try {
            val response = api.getPopularMovies(language = lang)
            val results = if (response.results.isNotEmpty() && response.results[0].overview.isNullOrBlank()) {
                api.getPopularMovies(language = "en-US").results
            } else {
                response.results
            }
            dao.insertAll(results)
        } catch (e: Exception) {}
        emit(dao.getAllIcons())
    }
}