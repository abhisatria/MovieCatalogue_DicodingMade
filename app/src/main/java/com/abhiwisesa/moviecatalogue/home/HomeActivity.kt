package com.abhiwisesa.moviecatalogue.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.viewpager.widget.ViewPager
import com.abhiwisesa.moviecatalogue.R
import com.abhiwisesa.moviecatalogue.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        supportActionBar?.title = getString(R.string.movie)
        activityHomeBinding.viewPager.currentItem = 0
        activityHomeBinding.bottomNavigationView.setOnItemSelectedListener {
            invalidateOptionsMenu()
            when (it.itemId) {
                R.id.movie -> {
                    supportActionBar?.title = getString(R.string.movie)
                    activityHomeBinding.viewPager.currentItem = 0
                    position = 0
                }
                R.id.tvShow -> {
                    supportActionBar?.title = getString(R.string.tv_show)
                    activityHomeBinding.viewPager.currentItem = 1
                    position = 1
                }
                R.id.favorite -> {
                    supportActionBar?.title = getString(R.string.favorite)
                    activityHomeBinding.viewPager.currentItem = 2
                    position = 2
                }
            }
            return@setOnItemSelectedListener false
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        supportActionBar?.elevation = 0f
        activityHomeBinding.viewPager.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                activityHomeBinding.bottomNavigationView.menu.getItem(position).isChecked = true
            }

        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(position!=2)
        {
            menu?.findItem(R.id.action_alphabet_ascending)?.isVisible = false
            menu?.findItem(R.id.action_alphabet_descending)?.isVisible = false
            menu?.findItem(R.id.action_newest)?.isVisible = false
            menu?.findItem(R.id.action_oldest)?.isVisible = false
        }else{
            menu?.findItem(R.id.action_alphabet_ascending)?.isVisible = true
            menu?.findItem(R.id.action_alphabet_descending)?.isVisible = true
            menu?.findItem(R.id.action_newest)?.isVisible = true
            menu?.findItem(R.id.action_oldest)?.isVisible = true
        }

        return super.onPrepareOptionsMenu(menu)
    }
}