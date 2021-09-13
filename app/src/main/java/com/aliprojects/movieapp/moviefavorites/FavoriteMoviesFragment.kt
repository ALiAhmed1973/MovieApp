package com.aliprojects.movieapp.moviefavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aliprojects.movieapp.databinding.FavoriteMoviesFragmentBinding

class FavoriteMoviesFragment : Fragment() {
    private lateinit var binding:FavoriteMoviesFragmentBinding
    private  val viewModel: FavoriteMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteMoviesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


}