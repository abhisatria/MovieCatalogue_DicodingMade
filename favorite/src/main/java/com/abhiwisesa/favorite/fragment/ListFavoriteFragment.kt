package com.abhiwisesa.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhiwisesa.core.ui.FavoriteAdapter
import com.abhiwisesa.moviecatalogue.databinding.FragmentListFavoriteBinding
import com.abhiwisesa.moviecatalogue.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ListFavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)
        _binding = FragmentListFavoriteBinding.inflate(layoutInflater, container, false)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val adapter = FavoriteAdapter()
        if (index == 1) {
            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner,{data ->
                adapter.setMovies(data)
                binding.noDataTextViewFav.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
            })
        } else if (index == 2) {
            favoriteViewModel.favoriteShow.observe(viewLifecycleOwner,{data ->
                adapter.setMovies(data)
                binding.noDataTextViewFav.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
            })
        }

        adapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvMovieFavorite) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}