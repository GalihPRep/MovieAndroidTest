package com.example.nameful7.page.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameful7.api.RequestState
import com.example.nameful7.model.response.VideoListResponse
import com.example.nameful7.repository.GeneralRepository
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class VideoListViewModel : ViewModel() {
    private var listResponse: VideoListResponse? = null
    private val repository : GeneralRepository = GeneralRepository()
    private var responseInitial = MutableLiveData<RequestState<VideoListResponse?>>()

    var response:LiveData<RequestState<VideoListResponse?>> = responseInitial




    fun getVideoList(movieId: Int){
        viewModelScope.launch {
            responseInitial.postValue(RequestState.Loading)
            responseInitial.postValue(handleVideoListResponse(repository.getMovieTrailers(movieId)))
        }
    }

    private fun handleVideoListResponse(
        response: Response<VideoListResponse>
    ): RequestState<VideoListResponse?> {
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