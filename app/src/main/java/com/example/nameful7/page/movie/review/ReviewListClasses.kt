package com.example.nameful7.page.movie.review


import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.databinding.ReviewListItemBinding
import com.example.nameful7.model.others.AuthorDetails
import com.example.nameful7.model.others.Review


class ReviewViewHolder(val binding: ReviewListItemBinding): RecyclerView.ViewHolder(binding.root)

interface ReviewOnClickListener { fun onClick(review: Review, authorDetails: AuthorDetails)}