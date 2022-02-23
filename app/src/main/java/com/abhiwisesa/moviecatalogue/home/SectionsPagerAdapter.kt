package com.abhiwisesa.moviecatalogue.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.abhiwisesa.moviecatalogue.movies.MovieFragment
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.moviecatalogue.tv_show.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
//            2 -> FavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show,R.string.favorite)
    }

}