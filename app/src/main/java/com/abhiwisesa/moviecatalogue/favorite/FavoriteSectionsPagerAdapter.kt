package com.abhiwisesa.moviecatalogue.favorite


import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhiwisesa.moviecatalogue.R

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
        val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }

}