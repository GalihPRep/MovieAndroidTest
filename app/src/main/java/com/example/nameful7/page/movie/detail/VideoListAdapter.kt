package com.example.nameful7.page.movie.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.databinding.VideoListItemBinding
import com.example.nameful7.model.others.Video

class VideoListAdapter : RecyclerView.Adapter<VideoViewHolder>(){
    private lateinit var videoOnClickListener: VideoOnClickListener
    val differ = AsyncListDiffer(
        this,
        object :DiffUtil.ItemCallback<Video>(){
            override fun areItemsTheSame(oldItem: Video, newItem: Video):
                    Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Video, newItem: Video):
                    Boolean = oldItem == newItem
        }
    )

    fun clickListener(videoOnClickListener: VideoOnClickListener){
        this.videoOnClickListener = videoOnClickListener
    }







    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
       with(holder){
           with(differ.currentList[position]){
               binding.apply {
                   videoName.text = name.toString()
                   itemView.setOnClickListener {
                       videoOnClickListener.onClick(this@with)
                   }
               }
           }
       }
    }

}