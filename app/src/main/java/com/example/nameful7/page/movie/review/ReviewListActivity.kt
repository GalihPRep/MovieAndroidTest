package com.example.nameful7.page.movie.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.nameful7.model.others.Movie
import com.example.nameful7.api.RequestState
import com.example.nameful7.databinding.ActivityReviewListBinding
import com.example.nameful7.model.others.Genre
import com.example.nameful7.page.parcelable

class ReviewListActivity : AppCompatActivity() {
    private var adapter: ReviewListAdapter? = null
    private var bindingInitial: ActivityReviewListBinding? = null
    private var layoutManager: LayoutManager? = null
    private lateinit var viewModel: ReviewListViewModel

    private val binding get() = bindingInitial!!



    companion object{
        const val MOVIE = "MOVIE"
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivityReviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ReviewListViewModel::class.java]
        intent.parcelable<Movie>(MOVIE)?.let {
            if(it.id != null){ viewModel.getReviewList(it.id) }
        }

        showReviewList()
        setupRecyclerView()
    }
    override fun onDestroy() {
        super.onDestroy()
        bindingInitial = null
    }










    private fun showReviewList(){
        viewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        it.data?.results?.let {
                                data -> adapter?.differ?.submitList(data.toList())
                        }
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(){
        adapter = ReviewListAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.apply {
            detailReviewList.adapter = adapter
            detailReviewList.layoutManager = layoutManager
            detailReviewList.addOnScrollListener(scrollListener)
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(!recyclerView.canScrollVertically(1)){
                intent.parcelable<Genre>(MOVIE)?.let{
                    if(it.id != null){ viewModel.getReviewList(it.id) }
                }
            }
        }
    }

    private fun showLoading(){ binding.loading.show() }
    private fun hideLoading(){ binding.loading.hide() }
}