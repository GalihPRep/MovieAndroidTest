package com.example.nameful7.page.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameful7.api.RequestState
import com.example.nameful7.model.response.GenreListResponse
import com.example.nameful7.repository.GeneralRepository
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class GenreListViewModel : ViewModel() {
    private var listResponse: GenreListResponse? = null
    private var responseInitial = MutableLiveData<RequestState<GenreListResponse?>>()
    private val repository : GeneralRepository = GeneralRepository()

    var response:LiveData<RequestState<GenreListResponse?>> = responseInitial

    fun getGenres(){
        viewModelScope.launch {
            responseInitial.postValue(RequestState.Loading)
            responseInitial.postValue(handleMovieListResponse(repository.getGenres()))
        }
    }

    private fun handleMovieListResponse(
        response: Response<GenreListResponse>
    ): RequestState<GenreListResponse?> {
        return if(response.isSuccessful){
            RequestState.Success(listResponse ?: response.body())
        }else RequestState.Error(
            try{
                response.errorBody()?.string()?.let{
                    JSONObject(it).get("status_message")
                }
            }catch (e:JSONException){
                e.localizedMessage
            }as String
        )
    }
}