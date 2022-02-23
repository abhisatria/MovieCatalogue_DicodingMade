package com.abhiwisesa.moviecatalogue.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.abhiwisesa.moviecatalogue.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
//        val sectionsPagerAdapter =
//            com.abhiwisesa.favorite.FavoriteSectionsPagerAdapter(requireActivity())
//        binding.viewPagerFavorite.adapter = sectionsPagerAdapter
//        TabLayoutMediator(binding.tabs,binding.viewPagerFavorite) { tab, position ->
//            tab.text = resources.getString(com.abhiwisesa.favorite.FavoriteSectionsPagerAdapter.TAB_TITLES[position])
//        }.attach()

        return binding.root
    }




}