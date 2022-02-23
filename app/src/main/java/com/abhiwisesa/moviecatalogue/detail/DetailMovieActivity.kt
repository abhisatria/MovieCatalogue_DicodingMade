package com.abhiwisesa.moviecatalogue.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.abhiwisesa.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.abhiwisesa.moviecatalogue.databinding.ContentDetailMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentDetailBinding: ContentDetailMovieBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var menu: Menu? = null
    private var statusFavorite : Boolean = false
    private var tempMovie : Movie? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailBinding = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tempMovie = intent.getParcelableExtra(EXTRA_DATA)
        showDetailMovie(tempMovie)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            supportActionBar?.title = movie.title
            contentDetailBinding.apply {
                tvDetailTitle.text = movie.title
                textDescription.text = movie.description
                tvDetailDate.text = movie.releaseDate
                tvDetailLanguage.text = movie.language
                tvDetailScore.text = movie.voteAverage.toString()
            }
            loadImage(BASE_URL_IMAGE + movie.posterPath)

            statusFavorite = movie.isFavorite
        }
    }

    private fun loadImage(url: String?) {
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(contentDetailBinding.imageViewPoster)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        setStatusFavorite(statusFavorite)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            statusFavorite = !statusFavorite
            tempMovie?.let { detailViewModel.setFavoriteTourism(it,statusFavorite) }
            setStatusFavorite(statusFavorite)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setStatusFavorite(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    }
}