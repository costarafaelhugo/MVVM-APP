package com.hugorafaelcosta.mvvmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.hugorafaelcosta.mvvmapp.adapter.MainAdapter
import com.hugorafaelcosta.mvvmapp.databinding.ActivityMainBinding
import com.hugorafaelcosta.mvvmapp.rest.RetrofitService

class MainActivity : AppCompatActivity() {

    private lateinit var bindig : ActivityMainBinding

    lateinit var viewModel: ViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}