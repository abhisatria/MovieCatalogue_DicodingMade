package com.abhiwisesa.favorite


import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.favorite.fragment.ListFavoriteFragment

class FavoriteSectionsPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = ListFavoriteFragment()

        fragment.arguments = Bundle().apply {
            putInt(ListFavoriteFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }


    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(com.abhiwisesa.favorite.R.string.movies, com.abhiwisesa.favorite.R.string.tv_show)
    }

}