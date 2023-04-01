package com.example.test.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

const val ONE = "2912"
const val TWO = "2738"

@AndroidEntryPoint
class LoadingScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        getResponse()
        return inflater.inflate(R.layout.fragment_loading_screen, container, false)
    }

    private fun getResponse() {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://testtasks.nutgeek.fun/a/").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()?.length.toString()

                CoroutineScope(Dispatchers.Main).launch {
                    findNavController().navigate(
                        when (responseBody) {
                            ONE -> (R.id.action_loadingScreenFragment_to_wallpaperFragment)
                            TWO -> (R.id.action_loadingScreenFragment_to_webFragment)
                            else -> Log.d("MyLog", "Error from getResponse")
                        }
                    )
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoadingScreenFragment()
    }
}