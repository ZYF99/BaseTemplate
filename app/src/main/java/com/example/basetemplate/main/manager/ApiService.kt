package com.example.basetemplate.main.manager

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("titles")
    fun getHomepageTitle(): Single<TitleModel>

}