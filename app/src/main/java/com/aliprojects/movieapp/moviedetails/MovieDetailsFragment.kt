package com.aliprojects.movieapp.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aliprojects.movieapp.database.getDatabase
import com.aliprojects.movieapp.databinding.MovieDetailsFragmentBinding
import com.aliprojects.movieapp.repository.MoviesRepository

private const val TAG = "MovieDetailsFragment"
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: MovieDetailsFragmentBinding
    private val database by lazy { getDatabase(this.requireContext()) }
    private val repository by lazy { MoviesRepository(database) }
    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModel.Factory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieDetails =
            com.aliprojects.movieapp.moviedetails.MovieDetailsFragmentArgs.fromBundle(
                requireArguments()
            ).movieDetails

        binding.iconFav.setOnClickListener {
            viewModel.addOrRemoveMovieFromFavorite()
        }
       viewModel.favMovies.observe(viewLifecycleOwner){
           viewModel.setMovieDetails(movieDetails,it)
        }

    }


}