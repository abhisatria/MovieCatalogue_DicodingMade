package com.abhiwisesa.moviecatalogue.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.abhiwisesa.core.data.Resource
import com.abhiwisesa.core.ui.MovieAdapter
import com.abhiwisesa.moviecatalogue.databinding.FragmentMovieBinding
import com.abhiwisesa.moviecatalogue.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding
    private val mainViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = MovieAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            with(binding?.rvMovie) {
                this?.layoutManager = GridLayoutManager(context, 2)
                this?.setHasFixedSize(true)
            }

            mainViewModel.getListMovie.observe(requireActivity(), { result ->
                if (result != null) {
                    when (result) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            val userData = result.data
                            if (userData != null) {
                                if (userData.isEmpty()) {
                                    showErrorMessage(PLACEHOLDER_NO_DATA)
                                }
                                adapter.setMovies(userData)
                                binding?.rvMovie?.adapter = adapter
                            }

                        }
                        is Resource.Error -> {
                            showLoading(false)
                            result.message?.let { showErrorMessage(it) }
                        }
                    }
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.noDataTextView?.visibility = View.GONE
        if (isLoading) {
            binding?.apply {
                rvMovie.visibility = View.GONE
                movieShimmerFrameLayout.startShimmer()
                movieShimmerFrameLayout.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                rvMovie.visibility = View.VISIBLE
                movieShimmerFrameLayout.visibility = View.GONE
                movieShimmerFrameLayout.stopShimmer()
            }
        }
    }


    private fun showErrorMessage(message: String) {
        binding?.apply {
            if (message != PLACEHOLDER_NO_DATA) {
                noDataTextView.text = "ERROR\n$message"
            } else {
                noDataTextView.text = message
            }
            noDataTextView.visibility = View.VISIBLE
            rvMovie.visibility = View.GONE
        }
    }

    companion object {
        const val PLACEHOLDER_NO_DATA = "Nothing to show"
    }

}