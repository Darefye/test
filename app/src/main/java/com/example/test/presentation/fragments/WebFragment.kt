package com.example.test.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.FragmentManager
import com.example.test.R
import com.example.test.databinding.FragmentWallpaperBinding
import com.example.test.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.fixedRateTimer

class WebFragment : Fragment() {
    private lateinit var binding: FragmentWebBinding
    private val link = "https://html5test.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = WebView(requireContext())
        webView.webViewClient = WebViewClient()
        webView.loadUrl(link)
        webView.webViewClient = WebViewClient()
        webView.canGoBack()

        binding.flWeb.addView(webView)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WebFragment()

    }
}