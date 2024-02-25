package com.solucioneshr.soft.dynamiccolor_changingtextview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.solucioneshr.soft.dynamiccolor_changingtextview.databinding.ActivityMainBinding
import com.solucioneshr.soft.dynamiccolor_changingtextview.models.DynamicColors
import com.solucioneshr.soft.qrappreport.controllers.NetworkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(IO).launch {
            fetchAndChangeColor()
        }

        binding.btnUpdateColors.setOnClickListener {
            fetchAndChangeColor()
        }
    }

    private fun fetchAndChangeColor() {
        val serviceApi = NetworkClient.apiService.getPostById()
        serviceApi.enqueue(object : Callback<DynamicColors> {
            override fun onResponse(call: Call<DynamicColors>, response: Response<DynamicColors>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    Log.d("TAG", "onResponseSuccess: $post")

                    post?.let {
                        CoroutineScope(Main).launch {
                            binding.textDisplay.setTextColor(Color.parseColor(it.hex))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DynamicColors>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.stackTrace}")
                fetchAndChangeColor()
            }

        })
    }
}