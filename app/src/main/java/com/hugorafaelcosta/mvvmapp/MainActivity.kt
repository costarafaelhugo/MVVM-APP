package com.hugorafaelcosta.mvvmapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hugorafaelcosta.mvvmapp.adapter.MainAdapter
import com.hugorafaelcosta.mvvmapp.databinding.ActivityMainBinding
import com.hugorafaelcosta.mvvmapp.repository.MainRepository
import com.hugorafaelcosta.mvvmapp.rest.RetrofitService
import com.hugorafaelcosta.mvvmapp.viewModel.main.MainViewModel
import com.hugorafaelcosta.mvvmapp.viewModel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var bindig : ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{
        openLink(it.link)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        bindig.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        viewModel.liveList.observe(this, {lives->
            Log.i("Hugo", "onStart")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, { message->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }
}