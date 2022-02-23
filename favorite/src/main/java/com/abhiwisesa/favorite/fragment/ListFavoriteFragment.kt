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
//            favoriteViewModel.getFavoriteMovie(SortUtils.OLDEST).observe(requireActivity(), movieObserver)
        } else if (index == 2) {
            favoriteViewModel.favoriteShow.observe(viewLifecycleOwner,{data ->
                adapter.setMovies(data)
                binding.noDataTextViewFav.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
            })
//            favoriteViewModel.getFavoriteShow(SortUtils.NEWEST).observe(requireActivity(), showObserver)
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
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_sort, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        var sort = ""
//        when (item.itemId) {
//            R.id.action_newest -> sort = SortUtils.NEWEST
//            R.id.action_oldest -> sort = SortUtils.OLDEST
//            R.id.action_alphabet_ascending -> sort = SortUtils.ALPHABETICAL_ASCENDING
//            R.id.action_alphabet_descending -> sort = SortUtils.ALPHABETICAL_DESCENDING
//        }
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
////        if (index == 1) {
////            favoriteViewModel.getFavoriteMovie(sort).observe(requireActivity(), movieObserver)
////        } else if (index == 2) {
////            favoriteViewModel.getFavoriteShow(sort).observe(requireActivity(), showObserver)
////        }
//        item.isChecked = true
//        return super.onOptionsItemSelected(item)
//    }

//    private val movieObserver = Observer<PagingData<Movie>> { movies ->
//        if (movies != null) {
//            adapter.submitList(movies)
//            if (movies.size == 0) {
//                binding?.noDataTextViewFav?.text = getString(R.string.no_data_found)
//                binding?.noDataTextViewFav?.visibility = View.VISIBLE
//            }else{
//                binding?.noDataTextViewFav?.visibility = View.INVISIBLE
//            }
//        }
//    }

//    private val movieObserver = Observer<PagedList<MovieEntity>> { movies ->
//        if (movies != null) {
//            adapter.submitList(movies)
//            if (movies.size == 0) {
//                binding?.noDataTextViewFav?.text = getString(R.string.no_data_found)
//                binding?.noDataTextViewFav?.visibility = View.VISIBLE
//            }else{
//                binding?.noDataTextViewFav?.visibility = View.INVISIBLE
//            }
//        }
//    }
//
//    private val showObserver = Observer<PagedList<MovieEntity>> { shows ->
//        if (shows != null) {
//            adapter.submitList(shows)
//            if (shows.size == 0) {
//                binding?.noDataTextViewFav?.text = getString(R.string.no_data_found)
//                binding?.noDataTextViewFav?.visibility = View.VISIBLE
//            }else{
//                binding?.noDataTextViewFav?.visibility = View.INVISIBLE
//            }
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}