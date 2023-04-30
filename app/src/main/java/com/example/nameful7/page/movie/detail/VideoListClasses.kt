package com.example.nameful7.page.movie.detail


import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.databinding.VideoListItemBinding
import com.example.nameful7.model.others.Movie
import com.example.nameful7.model.others.Review
import com.example.nameful7.model.others.Video


class VideoViewHolder(val binding: VideoListItemBinding) : RecyclerView.ViewHolder(binding.root)

interface VideoOnClickListener { fun onClick(video: Video) }

interface MovieReviewOnClickListener { fun onClick(movie: Movie) }