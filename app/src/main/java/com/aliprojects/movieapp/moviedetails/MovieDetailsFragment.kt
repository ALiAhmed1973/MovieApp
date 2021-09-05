package com.aliprojects.movieapp.moviedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aliprojects.movieapp.R
import com.aliprojects.movieapp.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : Fragment()
{
private lateinit var binding:MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= MovieDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieDetails = com.aliprojects.movieapp.moviedetails.MovieDetailsFragmentArgs.fromBundle(requireArguments()).movieDetails
        viewModel.setMovieDetails(movieDetails)
    }



}