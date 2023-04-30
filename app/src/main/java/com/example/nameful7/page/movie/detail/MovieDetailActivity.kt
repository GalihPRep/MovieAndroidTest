package com.example.nameful7.page.movie.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.nameful7.model.others.Movie
import com.bumptech.glide.Glide
import com.example.nameful7.BuildConfig
import com.example.nameful7.R
import com.example.nameful7.api.RequestState
import com.example.nameful7.databinding.ActivityMovieDetailBinding
import com.example.nameful7.model.others.Genre
import com.example.nameful7.model.others.Video
import com.example.nameful7.page.movie.review.ReviewListActivity
import com.example.nameful7.page.parcelable

class MovieDetailActivity : AppCompatActivity(), View.OnClickListener {
    private var adapter: VideoListAdapter? = null
    private var bindingInitial: ActivityMovieDetailBinding? = null
    private var layoutManager: LayoutManager? = null
    private lateinit var viewModel: VideoListViewModel

    private val binding get() = bindingInitial!!



    companion object{
        const val MOVIE = "MOVIE"
        const val GENRES = "GENRES"
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[VideoListViewModel::class.java]
        intent.parcelable<Movie>(MOVIE)?.let {
            intent.getStringExtra(GENRES)?.let { genres -> showMovieDetail(it, genres) }
            if(it.id != null){ viewModel.getVideoList(it.id) }
        }

        showVideoList()
        setupRecyclerView()

        adapter?.clickListener(object: VideoOnClickListener {
            override fun onClick(video: Video) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v="+video.key)
                )
                startActivity(intent)
            }
        })
        val reviewButton: Button = findViewById(R.id.review_button)
        reviewButton.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        val movie = intent.parcelable<Movie>(MOVIE)
        when (p0?.id) {
            R.id.review_button -> {
                val intent = Intent(
                    this@MovieDetailActivity,
                    ReviewListActivity::class.java)
                if(movie != null) intent.putExtra(MOVIE,movie)
                startActivity(intent)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        bindingInitial = null
    }










    private fun showMovieDetail(movie: Movie, genres:String){
        with(movie) {
            binding.apply {
                Glide
                    .with(this@MovieDetailActivity)
                    .load("${BuildConfig.BASE_URL_PHOTO}${posterPath}")
                    .into(detailPoster)
                detailTitle.text = title
                detailReleaseDate.text = releaseDate
                detailRating.text = voteAverage.toString()
                detailRatingBar.rating = voteAverage?.div(2) ?: 0f
                detailGenres.text = genres.dropLast(2)
                detailOverview.text = movie.overview
            }
        }
    }

    private fun showVideoList(){
        viewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> {}
                    is RequestState.Success -> {
                        it.data?.results?.let {
                                data -> adapter?.differ?.submitList(data.toList())
                        }
                    }
                    is RequestState.Error -> {
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun setupRecyclerView(){
        adapter = VideoListAdapter()
        layoutManager = GridLayoutManager(this,2)
        binding.apply {
            detailTrailerList.adapter = adapter
            detailTrailerList.layoutManager = layoutManager
            detailTrailerList.addOnScrollListener(videoScrollListener)
        }
    }

    private val videoScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(!recyclerView.canScrollVertically(1)){
                intent.parcelable<Genre>(MOVIE)?.let{
                    if(it.id != null){ viewModel.getVideoList(it.id) }
                }
            }
        }
    }


}