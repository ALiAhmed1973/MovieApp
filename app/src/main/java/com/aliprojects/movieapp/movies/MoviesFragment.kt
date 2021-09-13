package com.aliprojects.movieapp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliprojects.movieapp.database.MoviesDatabase
import com.aliprojects.movieapp.database.getDatabase
import com.aliprojects.movieapp.databinding.FragmentMoviesBinding
import com.aliprojects.movieapp.repository.MoviesRepository

private const val TAG = "MoviesFragment"

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val database by lazy{ getDatabase(this.requireContext())}
    private val repository by lazy{MoviesRepository(database)}
    private val viewModel: MoviesViewModel by viewModels{
        MoviesViewModel.Factory(repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvMoviesList.adapter = MoviesAdapter(MoviesAdapter.MovieCardClickListener {
            viewModel.navigateToMovieDetails(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel?.navigateToMovieScreen?.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController().navigate(
                    com.aliprojects.movieapp.movies.MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
                        it
                    )
                )
                viewModel.navigationCompleted()
            }
        })
    }
}