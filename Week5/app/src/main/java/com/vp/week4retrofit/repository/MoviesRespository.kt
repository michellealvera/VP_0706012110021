package com.vp.week4retrofit.repository

import com.vp.week4retrofit.retrofit.EndPointApi
import javax.inject.Inject

class MoviesRespository @Inject constructor(private val api: EndPointApi){
    suspend fun getNowPlayingData(apiKey: String, language: String, page: Int)
    = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailsData(apiKey: String, movieId: Int)
    = api.getMovieDetails(movieId, apiKey)

}