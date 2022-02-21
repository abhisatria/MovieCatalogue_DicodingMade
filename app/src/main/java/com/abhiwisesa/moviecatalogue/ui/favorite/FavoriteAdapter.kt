package com.abhiwisesa.moviecatalogue.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.moviecatalogue.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.databinding.ItemsFavoriteMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.ArrayList

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
//    PagingDataAdapter<Movie, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    private var listMovies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val itemsFavoriteMovieBinding = ItemsFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemsFavoriteMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = listMovies.size


    inner class FavoriteViewHolder(private val binding: ItemsFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemFavoriteDescription.text = movie.description
                Glide.with(itemView.context)
                    .load(BASE_URL_IMAGE +movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovies[bindingAdapterPosition])
            }
        }
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
//        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
//            object : DiffUtil.ItemCallback<Movie>() {
//                override fun areItemsTheSame(
//                    oldMovie: Movie,
//                    newMovie: Movie
//                ): Boolean {
//                    return oldMovie.title == newMovie.title
//                            && oldMovie.isMovie == newMovie.isMovie
//                            && oldMovie.dateAdded == newMovie.dateAdded
//                            && oldMovie.movieId == newMovie.movieId
//                            && oldMovie.language == newMovie.language
//                            && oldMovie.posterPath == newMovie.posterPath
//                            && oldMovie.releaseDate == newMovie.releaseDate
//                            && oldMovie.dateAdded == newMovie.dateAdded
//                            && oldMovie.isFavorite == newMovie.isFavorite
//                            && oldMovie.description == newMovie.description
//                }
//
//                @SuppressLint("DiffUtilEquals")
//                override fun areContentsTheSame(
//                    oldMovie: Movie,
//                    newMovie: Movie
//                ): Boolean {
//                    return oldMovie == newMovie
//                }
//            }
    }


}