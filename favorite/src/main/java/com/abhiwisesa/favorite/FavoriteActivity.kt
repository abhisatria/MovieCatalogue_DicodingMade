package com.abhiwisesa.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhiwisesa.favorite.databinding.ActivityFavoriteBinding
import com.abhiwisesa.favorite.di.favoriteModule
import com.abhiwisesa.favorite.fragment.FavoriteViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this)
        binding.viewPagerFavorite.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPagerFavorite) { tab, position ->
            tab.text =
                resources.getString(FavoriteSectionsPagerAdapter.TAB_TITLES[position])
        }.attach()

    }
}