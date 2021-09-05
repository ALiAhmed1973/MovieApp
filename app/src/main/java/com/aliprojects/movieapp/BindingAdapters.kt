package com.aliprojects.movieapp

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.movies.MoviesAdapter
import com.aliprojects.movieapp.network.IMAGE_URL

@BindingAdapter("imgUrl")
fun bindImage(imageview: ImageView, imgUrl:String?)
{
    if(!imgUrl.isNullOrEmpty()) {
        imageview.load(IMAGE_URL + imgUrl){
            error(R.drawable.ic_image_not_supported_24)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<Movie>?)
{
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}