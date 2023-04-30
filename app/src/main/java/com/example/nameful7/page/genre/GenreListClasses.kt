package com.example.nameful7.page.genre


import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.databinding.GenreListItemBinding
import com.example.nameful7.model.others.Genre


class GenreViewHolder(val binding: GenreListItemBinding) : RecyclerView.ViewHolder(binding.root)

interface GenreOnClickListener { fun onClick(genre: Genre) }