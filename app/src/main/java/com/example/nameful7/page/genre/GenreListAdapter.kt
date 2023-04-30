package com.example.nameful7.page.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nameful7.model.others.Genre
import com.example.nameful7.databinding.GenreListItemBinding

class GenreListAdapter : RecyclerView.Adapter<GenreViewHolder>(){
    private lateinit var genreOnClickListener: GenreOnClickListener
    val differ = AsyncListDiffer(
        this,
        object :DiffUtil.ItemCallback<Genre>(){
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre):
                    Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Genre, newItem: Genre):
                    Boolean = oldItem == newItem
        }
    )

    fun clickListener(genreOnClickListener: GenreOnClickListener){
        this.genreOnClickListener = genreOnClickListener
    }







    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GenreViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
//       with(holder){
//           with(differ.currentList[position]){
//               binding.apply {
//                   genreName.text = name.toString()
//                   itemView.setOnClickListener {
//                       genreOnClickListener.onClick(this@with)
//                   }
//               }
//           }
//       }
//    }
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genreExpected = differ.currentList[position]
        with(holder.binding) {
            genreName.text = genreExpected.name
            root.setOnClickListener {
                genreOnClickListener.onClick(genreExpected)
            }
        }
    }

}