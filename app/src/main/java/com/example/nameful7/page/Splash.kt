package com.example.nameful7.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.nameful7.databinding.ActivitySplashBinding
import com.example.nameful7.page.movie.list.MovieListActivity

class Splash : AppCompatActivity() {
    private var bindingInitial: ActivitySplashBinding? = null
    private val binding get() = bindingInitial!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInitial = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MovieListActivity::class.java))
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingInitial = null
    }
}