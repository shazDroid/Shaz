package com.shazdroid.shaz.api

import com.shazdroid.shaz.model.UserDetailResponse
import com.shazdroid.shaz.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int) : UserListResponse

    @GET("users/{userId}/posts")
    suspend fun getUserPostById(@Path("userId") userId: String) : UserDetailResponse

}