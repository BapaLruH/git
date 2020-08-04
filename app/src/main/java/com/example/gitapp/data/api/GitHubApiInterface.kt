package com.example.gitapp.data.api

import com.example.gitapp.data.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiInterface {

    @GET("./users")
    fun getUsersList(@Query("since") startPosition: Int, @Query("per_page") valuesPerPage: Int): Single<List<UserResponse>>

    @GET("./users/{userId}")
    fun getUserById(@Path("userId") userId: String): Single<UserResponse>

}