package com.aliprojects.movieapp.movies

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aliprojects.movieapp.databinding.FragmentMoviesBinding
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.network.IMAGE_URL

private const val TAG = "MoviesFragment"
class MoviesFragment : Fragment() {
    private lateinit var  binding:FragmentMoviesBinding
    private val viewModel:MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMoviesBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.rvMoviesList.adapter = MoviesAdapter(MoviesAdapter.MovieCardClickListener{
            viewModel.navigateToMovieDetails(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel?.navigateToMovieScreen?.observe(viewLifecycleOwner, Observer {
            this.findNavController().navigate(com.aliprojects.movieapp.movies.MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(it))
        })
    }
}