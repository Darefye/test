package com.example.test.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.test.R
import com.example.test.databinding.FragmentWallpaperBinding
import com.example.test.presentation.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        binding.vpImg.adapter = ViewPagerAdapter(images)
        binding.circleIndicator.setViewPager(binding.vpImg)

        binding.circleIndicator.createIndicators(3, 0);

        binding.circleIndicator.animatePageSelected(0)
    }

    companion object {

        val images = listOf(R.drawable.first, R.drawable.second, R.drawable.third)


        @JvmStatic
        fun newInstance() = WallpaperFragment()

    }
}