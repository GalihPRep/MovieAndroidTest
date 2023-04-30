package com.example.nameful7.page.movie.list


import com.example.nameful7.model.others.Movie
import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.databinding.MovieListItemBinding



class MovieViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)

interface MovieOnClickListener { fun onClick(movie: Movie, genres:String) }