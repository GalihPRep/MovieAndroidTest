package com.example.nameful7.page.genre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView


import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.nameful7.R
import com.example.nameful7.api.RequestState
import com.example.nameful7.databinding.ActivityGenreListBinding
import com.example.nameful7.model.others.Genre
import com.example.nameful7.page.movie.list.MovieByGenreListActivity
import com.example.nameful7.page.movie.list.MovieListActivity


class GenreListActivity : AppCompatActivity() {
    private var adapter: GenreListAdapter? = null
    private var bindingInitial: ActivityGenreListBinding? = null
    private var layoutManager : LayoutManager? = null
    private lateinit var viewModel: GenreListViewModel

    private val binding get() = bindingInitial!!






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivityGenreListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GenreListViewModel::class.java]
        viewModel.getGenres()

        showGenreList()
        setupRecyclerView()

        adapter?.clickListener(object : GenreOnClickListener {
            override fun onClick(genre: Genre) {
                val intent = Intent(
                    this@GenreListActivity,
                    MovieByGenreListActivity::class.java
                )
                intent.putExtra(MovieByGenreListActivity.GENRE,genre)
                startActivity(intent)
            }
        })
    }

    /**making the menu*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**switching between pages*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_switch_list -> {
                val intent = Intent(
                    this@GenreListActivity,
                    MovieListActivity::class.java
                )
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingInitial = null
    }









    private fun showGenreList(){
        viewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        it.data?.genres?.let {
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
        adapter = GenreListAdapter()
        layoutManager = GridLayoutManager(
            this,
            2)
        binding.apply {
            genreList.adapter = adapter
            genreList.layoutManager = layoutManager
            genreList.addOnScrollListener(scrollListener)
        }
    }






    private val scrollListener = object : OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(!recyclerView.canScrollVertically(1)){
                viewModel.getGenres()
            }
        }
    }




    private fun showLoading(){ binding.loading.show() }
    private fun hideLoading(){ binding.loading.hide() }
}