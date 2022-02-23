package com.hugorafaelcosta.mvvmapp.repository

import com.hugorafaelcosta.mvvmapp.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()
}