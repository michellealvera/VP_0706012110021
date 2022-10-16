package com.vp.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vp.week4retrofit.adapter.ListGenreAdapter
import com.vp.week4retrofit.adapter.ListProdCompanyAdapter
import com.vp.week4retrofit.adapter.ListSpokenLangAdapter
import com.vp.week4retrofit.databinding.ActivityMovieDetailBinding
import com.vp.week4retrofit.helper.Const
import com.vp.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Runnable

@AndroidEntryPoint
abstract class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var genre_adapter: ListGenreAdapter
    private lateinit var prodCompany_adapter: ListProdCompanyAdapter
    private lateinit var spokenLang_adapter: ListSpokenLangAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable(){
            kotlin.run {
                progressBar.visibility = View.GONE
            }
        }, 5000)
        val movieId = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: ${movieId}", Toast.LENGTH_SHORT).show()

        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
            moviesViewModel.movieDetails.observe(this, Observer { response ->
                binding.tvTitleMovieDetail.apply {
                    text = response.title
                }
                genre_adapter = ListGenreAdapter(response.genres)
                binding.rvListgenre.apply {
                    layoutManager =
                        LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = genre_adapter
                }
                prodCompany_adapter = ListProdCompanyAdapter(response.production_companies)
                binding.rvListprodcompany.apply {
                    layoutManager =
                        LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = prodCompany_adapter
                }
                spokenLang_adapter = ListSpokenLangAdapter(response.spoken_languages)
                binding.rvSpokenlang.apply {
                    layoutManager =
                        LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = spokenLang_adapter
                }
                binding.tvOverviewMovieDetail.text = response.overview
                binding.tvReleaseDateMovieDetail.text = response.release_date
                binding.tvRuntimeMovieDetail.text = response.runtime.toString() + " minutes"
                Glide.with(applicationContext).load(Const.IMG_URL + response.backdrop_path)
                    .into(binding.imgPosterMovieDetail)
            })
        }

}