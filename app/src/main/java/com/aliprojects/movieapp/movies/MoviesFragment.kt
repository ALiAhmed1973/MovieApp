package com.aliprojects.movieapp.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliprojects.movieapp.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {
    private lateinit var  binding:FragmentMoviesBinding
    private lateinit var  moviesAdapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMoviesBinding.inflate(inflater)
        //binding.li
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter= MoviesAdapter()
        binding.rvMoviesList.adapter = moviesAdapter
    }
}