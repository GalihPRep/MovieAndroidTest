package com.example.nameful7.page.movie.list

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
import com.example.nameful7.databinding.ActivityMovieListBinding
import com.example.nameful7.model.others.Genre
import com.example.nameful7.model.others.Movie
import com.example.nameful7.page.movie.detail.MovieDetailActivity
import com.example.nameful7.page.genre.GenreListActivity
import com.example.nameful7.page.parcelable

class MovieByGenreListActivity : AppCompatActivity() {
    private var adapter: MovieListAdapter? = null
    private var bindingInitial: ActivityMovieListBinding? = null
    private var layoutManager : LayoutManager? = null
    private lateinit var viewModel: MovieByGenreListViewModel

    private val binding get() = bindingInitial!!



    companion object{
        const val GENRE = "GENRE"
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieByGenreListViewModel::class.java]
        intent.parcelable<Genre>(GENRE)?.let{
            viewModel.getMovieList(it.id.toString())
        }

        showMovieList()
        showMovieGenres()
        setupRecyclerView()

        adapter?.clickListener(object : MovieOnClickListener {
            override fun onClick(movie: Movie, genres: String) {
                val intent = Intent(
                    this@MovieByGenreListActivity,
                    MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE, movie)
                intent.putExtra(MovieDetailActivity.GENRES, genres)
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
        return when(item.itemId){
            R.id.action_switch_list -> {
                val intent = Intent(
                    this@MovieByGenreListActivity,
                    GenreListActivity::class.java
                )
                startActivity(intent)
                true
            }
            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingInitial = null
    }









    private fun showMovieList(){
        viewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        it.data?.results?.let {
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

    private fun showMovieGenres(){
        viewModel.getMovieGenres().observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> {}
                    is RequestState.Success -> it.data.genres?.let { data -> adapter?.setGenres(data) }
                    is RequestState.Error -> Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun setupRecyclerView(){
        adapter = MovieListAdapter()
        layoutManager = GridLayoutManager(this, 2)
        binding.apply {
            movieList.adapter = adapter
            movieList.layoutManager = layoutManager
            movieList.addOnScrollListener(scrollListener)
        }
    }




    private val scrollListener = object : OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(!recyclerView.canScrollVertically(1)){
                intent.parcelable<Genre>(GENRE)?.let{
                    viewModel.getMovieList(it.id.toString())
                }
            }
        }
    }





    private fun showLoading(){ binding.loading.show() }
    private fun hideLoading(){ binding.loading.hide() }
}