package com.aliprojects.movieapp.moviefavorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliprojects.movieapp.database.getDatabase
import com.aliprojects.movieapp.databinding.FavoriteMoviesFragmentBinding
import com.aliprojects.movieapp.movies.MoviesAdapter
import com.aliprojects.movieapp.repository.MoviesRepository

private const val TAG = "FavoriteMoviesFragment"
class FavoriteMoviesFragment : Fragment() {
    private lateinit var binding:FavoriteMoviesFragmentBinding
    private val database by lazy{ getDatabase(this.requireContext()) }
    private val repository by lazy{ MoviesRepository(database) }
    private  val viewModel: FavoriteMoviesViewModel by viewModels{
        FavoriteMoviesViewModel.Factory(repository)
    }
    private lateinit var  adapter:FavoriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteMoviesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter=FavoriteMoviesAdapter(viewModel)
        binding.rvFavoriteMovies.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       viewModel.favMovies.observe(viewLifecycleOwner){
           adapter.submitList(it)
       }
        adapter.getMovie {
            this.findNavController().navigate(FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(it))
        }
    }
}