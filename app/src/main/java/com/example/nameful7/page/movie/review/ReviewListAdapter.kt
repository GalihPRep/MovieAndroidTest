package com.example.nameful7.page.movie.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nameful7.databinding.ReviewListItemBinding
import com.example.nameful7.databinding.VideoListItemBinding
import com.example.nameful7.model.others.AuthorDetails
import com.example.nameful7.model.others.Review
import com.example.nameful7.model.others.Video

class ReviewListAdapter : RecyclerView.Adapter<ReviewViewHolder>(){
    private lateinit var reviewOnClickListener: ReviewOnClickListener
    val differ = AsyncListDiffer(
        this,
        object :DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review):
                    Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Review, newItem: Review):
                    Boolean = oldItem.authorDetails == newItem.authorDetails
        }
    )

    fun clickListener(reviewOnClickListener: ReviewOnClickListener){
        this.reviewOnClickListener = reviewOnClickListener
    }







    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        with(holder){
           with(differ.currentList[position]){
               val dateCreated = "created: $createdAt"
               val dateUpdated = "last updated: $updatedAt"
               binding.apply {
                   reviewAuthor.text = author
                   reviewAuthorDetailUsername.text = authorDetails.username
                   reviewContent.text = content
                   reviewCreatedAt.text = dateCreated
                   reviewUpdatedAt.text = dateUpdated
                   Glide.with(itemView).load(authorDetails.avatarPath?.drop(1)).into(reviewAvatar)
               }
           }
       }
    }

}