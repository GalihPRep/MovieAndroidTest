package com.example.nameful7.page.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.BuildConfig
import com.example.nameful7.databinding.MovieListItemBinding
import com.example.nameful7.model.others.Genre
import com.example.nameful7.model.others.Movie
import com.bumptech.glide.Glide

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>(){
    private val genres = ArrayList<Genre>()
    private lateinit var movieOnClickListener: MovieOnClickListener
    val differ = AsyncListDiffer(
        this,
        object :DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie):
                    Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie):
                    Boolean = oldItem == newItem
        }
    )

    fun clickListener(movieOnClickListener: MovieOnClickListener){
        this.movieOnClickListener = movieOnClickListener
    }

    fun setGenres(list: List<Genre>){
        this.genres.clear()
        this.genres.addAll(list)
    }







    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       with(holder){
           with(differ.currentList[position]){
               binding.apply {
                   listTitle.text = title
                   listLanguage.text = originalLanguage
                   listReleaseDate.text = differ.currentList[position].releaseDate
                   detailRating.text = voteAverage.toString()
                   detailRatingBar.rating = voteAverage?.div(2) ?: 0f
                   Glide
                       .with(itemView)
                       .load("${BuildConfig.BASE_URL_PHOTO}$posterPath")
                       .into(listPoster)
                   val genreListMap = genres.associate { it.id to it.name }
                   val genreListStringBuilder = StringBuilder()
                   val genreIdList = ArrayList<Int>()
                   if (genreIds != null){
                       genreIdList.addAll(genreIds)
                       for(genreId in genreIds){
                           genreListStringBuilder.append("${genreListMap[genreId]}, ")
                       }
                   }
                   listGenre.text = genreListStringBuilder.dropLast(2)
                   itemView.setOnClickListener {
                       movieOnClickListener.onClick(this@with, genreListStringBuilder.toString())
                   }
               }
           }
       }
    }

}