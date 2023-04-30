package com.example.nameful7.page.movie.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameful7.api.RequestState
import com.example.nameful7.model.response.ReviewListResponse
import com.example.nameful7.repository.GeneralRepository
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class ReviewListViewModel : ViewModel() {
    private var listResponse: ReviewListResponse? = null
    private var page = 1
    private val repository : GeneralRepository = GeneralRepository()
    private var responseInitial = MutableLiveData<RequestState<ReviewListResponse?>>()
    var response:LiveData<RequestState<ReviewListResponse?>> = responseInitial




    fun getReviewList(movieId: Int){
        viewModelScope.launch {
            responseInitial.postValue(RequestState.Loading)
            responseInitial.postValue(handleReviewListResponse(repository.getMovieReviews(movieId,page)))
        }
    }

    private fun handleReviewListResponse(
        response: Response<ReviewListResponse>
    ): RequestState<ReviewListResponse?> {
        return if(response.isSuccessful){
            response.body()?.let {
                page++
                if (listResponse == null)
                    listResponse = it
                else {
                    val reviewsOld = listResponse?.results
                    val reviewsNew = it.results
                    reviewsOld?.addAll(reviewsNew)
                }
            }
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