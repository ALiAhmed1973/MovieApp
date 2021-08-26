package com.aliprojects.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aliprojects.movieapp.databinding.ActivityMainBinding
import com.aliprojects.movieapp.network.MovieApi
import com.aliprojects.movieapp.network.NetworkMovie
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}