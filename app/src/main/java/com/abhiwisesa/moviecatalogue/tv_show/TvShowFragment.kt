package com.abhiwisesa.moviecatalogue.tv_show

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.core.data.Resource
import com.abhiwisesa.core.ui.MovieAdapter
import com.abhiwisesa.moviecatalogue.databinding.FragmentTvShowBinding
import com.abhiwisesa.moviecatalogue.detail.DetailMovieActivity
import com.abhiwisesa.moviecatalogue.movies.MovieFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
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

            with(binding?.rvTvShow) {
                this?.layoutManager = GridLayoutManager(context,2)
                this?.setHasFixedSize(true)
            }

            tvShowViewModel.getListTvShow.observe(viewLifecycleOwner    , { result ->
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
                                binding?.rvTvShow?.adapter = adapter
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
                rvTvShow.visibility = View.GONE
                tvShowShimmerFrameLayout.startShimmer()
                tvShowShimmerFrameLayout.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                rvTvShow.visibility = View.VISIBLE
                tvShowShimmerFrameLayout.visibility = View.GONE
                tvShowShimmerFrameLayout.stopShimmer()
            }
        }
    }

    private fun showErrorMessage(message: String) {
        binding?.apply {
            if(message != MovieFragment.PLACEHOLDER_NO_DATA)
            {
                noDataTextView.text = "${getString(R.string.error)}\n$message"
            }
            else{
                noDataTextView.text = message
            }
            noDataTextView.visibility = View.VISIBLE
            rvTvShow.visibility = View.GONE
        }
    }

    companion object {
        const val PLACEHOLDER_NO_DATA = "Nothing to show"
    }
}