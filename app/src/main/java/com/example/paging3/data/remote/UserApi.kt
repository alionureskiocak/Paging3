package com.example.paging3.data.remote

import com.example.paging3.data.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    //https://api.github.com/users

    @GET("users")
    suspend fun getUsers(
        @Query("since") since : Int,
        @Query("per_page") perPage : Int
    ) : List<UserDto>
}