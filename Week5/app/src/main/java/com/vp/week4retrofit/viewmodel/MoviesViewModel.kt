package com.vp.week4retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vp.week4retrofit.model.Genre
import com.vp.week4retrofit.model.MovieDetails
import com.vp.week4retrofit.repository.MoviesRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.vp.week4retrofit.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository:MoviesRespository)
    :ViewModel(){
    //Get Now Playing Data
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowPlaying: LiveData<ArrayList<Result>>
    get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let {
            response ->
                if (response.isSuccessful){
                    _nowPlaying.postValue(response.body()?.results as
                    ArrayList<Result>?)
                } else{
                    Log.e("Get Now Playing Data", "Failed!")
                }
        }
    }
    //Get Movie Details Data
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    fun getMovieDetail(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailsData(apiKey, movieId).let {
                response ->
            if (response.isSuccessful){
                _movieDetails.postValue(response.body() as MovieDetails)
            }else {
                Log.e("Get Movie Details Data", "Failed!")
            }
        }
    }
}