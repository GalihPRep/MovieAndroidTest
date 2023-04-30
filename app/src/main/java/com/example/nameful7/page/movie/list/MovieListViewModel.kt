package com.example.nameful7.page.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.nameful7.api.RequestState
import com.example.nameful7.model.response.GenreListResponse
import com.example.nameful7.model.response.MovieListResponse
import com.example.nameful7.repository.GeneralRepository
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.HttpException

class MovieListViewModel : ViewModel() {
    private var listResponse: MovieListResponse? = null
    private var page = 1
    private val repository : GeneralRepository = GeneralRepository()
    private var responseInitial = MutableLiveData<RequestState<MovieListResponse?>>()

    var response:LiveData<RequestState<MovieListResponse?>> = responseInitial




    fun getMovieList(){
        viewModelScope.launch {
            responseInitial.postValue(RequestState.Loading)
            responseInitial.postValue(handleMovieListResponse(repository.getMovies(page)))
        }
    }

    fun getMovieByNameList(query: String){
        viewModelScope.launch {
            responseInitial.postValue(RequestState.Loading)
            responseInitial.postValue(handleMovieListResponse(repository.getMoviesByName(page,query)))
        }
    }

    fun getMovieGenres() : LiveData<RequestState<GenreListResponse>> = liveData {
        emit(RequestState.Loading)
        try {
            emit(RequestState.Success(repository.getGenres().body()!!))
        }catch (e: HttpException){
            e.response()?.errorBody()?.string()?.let { RequestState.Error(it) }?.let { emit(it) }
        }
    }

    private fun handleMovieListResponse(
        response: Response<MovieListResponse>
    ): RequestState<MovieListResponse?> {
        return if(response.isSuccessful){
            response.body()?.let {
                page++
                if (listResponse == null)
                    listResponse = it
                else {
                    val moviesOld = listResponse?.results
                    val moviesNew = it.results
                    moviesOld?.addAll(moviesNew)
                }
            }
            RequestState.Success(listResponse ?: response.body())
        }else RequestState.Error(
            try{
                response.errorBody()?.string()?.let{
                    JSONObject(it).get("status_message")
                }
            }
            catch (e:JSONException){ e.localizedMessage } as String
        )
    }




}