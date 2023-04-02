package com.example.test.presentation.fragments

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.databinding.FragmentWallpaperBinding
import com.example.test.presentation.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

class WallpaperFragment : Fragment() {
    private lateinit var binding: FragmentWallpaperBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWallpaperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
        setListener()
    }

    private fun initPager() = with(binding) {
        vpImg.adapter = ViewPagerAdapter(images)

        circleIndicator.setViewPager(vpImg)
        circleIndicator.createIndicators(3, 0)
        circleIndicator.animatePageSelected(0)
    }

    private fun setListener() = with(binding) {
        btSetWallpaper.setOnClickListener {
            val wallpaperManager = WallpaperManager.getInstance(context)
            try {
                wallpaperManager.setResource(images[vpImg.currentItem])
                Toast.makeText(context, getString(R.string.set_wallpaper), Toast.LENGTH_SHORT)
                    .show()
            } catch (e: IOException) {
                Toast.makeText(context, getString(R.string.set_wallpaper_error), Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        }
    }

    companion object {
        val images = listOf(R.drawable.first, R.drawable.second, R.drawable.third)
    }
}