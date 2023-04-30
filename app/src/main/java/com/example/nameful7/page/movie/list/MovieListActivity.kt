package com.example.nameful7.page.movie.list

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView


import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.nameful7.R
import com.example.nameful7.api.RequestState
import com.example.nameful7.databinding.ActivityMovieListBinding
import com.example.nameful7.model.others.Movie
import com.example.nameful7.page.movie.detail.MovieDetailActivity
import com.example.nameful7.page.genre.GenreListActivity


class MovieListActivity : AppCompatActivity() {
    private var bindingInitial: ActivityMovieListBinding? = null
    private var layoutManager : LayoutManager? = null
    private var movieListAdapter: MovieListAdapter? = null
    private var movieByNameListAdapter: MovieListAdapter? = null
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var movieByNameListViewModel: MovieByNameListViewModel

    private val binding get() = bindingInitial!!






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        movieListViewModel.getMovieList()

        // Experimental
        movieByNameListViewModel = ViewModelProvider(this)[MovieByNameListViewModel::class.java]

        showMovieList()
        showMovieGenres()
        setupRecyclerView()

        movieListAdapter?.clickListener(object : MovieOnClickListener {
            override fun onClick(movie: Movie, genres: String) {
                val intent = Intent(
                    this@MovieListActivity,
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
        when(item.itemId){
            R.id.action_search -> {
                val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
                val searchItem : MenuItem = item
                val searchView = searchItem.actionView as SearchView

                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
                searchView.queryHint = resources.getString(R.string.search_hint)

                searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
                    override fun onMenuItemActionExpand(p0: MenuItem): Boolean { return true }
                    override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                        movieListViewModel.getMovieList()
                        setupRecyclerView()
                        return true
                    }
                })



                movieByNameListAdapter = MovieListAdapter()
                binding.movieList.adapter = movieByNameListAdapter

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        movieByNameListViewModel.getMovieByNameList(query.toString())
                        searchView.clearFocus()
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean { return false }
                })
                return true
            }
            R.id.action_switch_list -> {
                val intent = Intent(
                    this@MovieListActivity,
                    GenreListActivity::class.java
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









    private fun showMovieList(){
        movieListViewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        it.data?.results?.let {
                                data -> movieListAdapter?.differ?.submitList(data.toList())
                        }
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        movieByNameListViewModel.response.observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        clearSearchResults()
                        it.data?.results?.let {
                                data -> movieByNameListAdapter?.differ?.submitList(data.toList())
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
        movieListViewModel.getMovieGenres().observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> {}
                    is RequestState.Success -> it.data.genres?.let {
                            data -> movieListAdapter?.setGenres(data)
                    }
                    is RequestState.Error ->
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        movieByNameListViewModel.getMovieGenres().observe(this){
            if (it != null){
                when(it){
                    is RequestState.Loading -> {}
                    is RequestState.Success -> it.data.genres?.let {
                            data -> movieByNameListAdapter?.setGenres(data)
                    }
                    is RequestState.Error ->
                        Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView(){
        movieListAdapter = MovieListAdapter()
        layoutManager = GridLayoutManager(this, 2)
        binding.apply {
            movieList.adapter = movieListAdapter
            movieList.layoutManager = layoutManager
            movieList.addOnScrollListener(scrollListener)
//            iconSearch.setOnClickListener { searchMovie() }
            editInputLayout.setOnKeyListener { _, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
//                    searchMovie()
                    showLoading()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private val scrollListener = object : OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(!recyclerView.canScrollVertically(1)){
                movieListViewModel.getMovieList()
            }
        }
    }

//    private fun searchMovie() {
//        binding.apply {
//            val query = editInputLayout.text.toString()
//            if (query.isNotEmpty()) {
//                showLoading()
//                movieByNameListViewModel.getMovieByNameList(query)
//            }
//        }
//    }


    private fun clearSearchResults() {
        movieByNameListAdapter?.differ?.submitList(emptyList())
    }


    private fun showLoading(){ binding.loading.show() }
    private fun hideLoading(){ binding.loading.hide() }
}