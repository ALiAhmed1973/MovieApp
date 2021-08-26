package com.aliprojects.movieapp.network


import com.aliprojects.movieapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL ="https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface Service
{
    @GET("discover/movie")
    fun getDiscoverMovie(@Query("api_key") apiKey:String=BuildConfig.API_KEY):List<NetworkMovie>
}

object MovieApi
{
    val service:Service by lazy{
        retrofit.create(Service::class.java)
    }
}