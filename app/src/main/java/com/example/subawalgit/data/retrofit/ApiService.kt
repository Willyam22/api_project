package com.example.subawalgit.data.retrofit

import com.example.subawalgit.data.response.FollowResponse
import com.example.subawalgit.data.response.ItemsItem
import com.example.subawalgit.data.response.ProfileResponse
import com.example.subawalgit.data.response.ResponseDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.*

interface ApiService {
    @GET("users")
    fun getProfile(
    ): Call<List<ItemsItem>>

    @GET("search/users")
    fun searchProfile(
        @Query("q") query:String
    ): Call<ProfileResponse>

    @GET("users/{username}")
    fun getDetailProfile(
        @Path("username") username:String
    ): Call<ResponseDetail>


    @GET("users/{username}/followers")
    fun getFollowersList(
        @Path("username") username:String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowingList(
        @Path("username") username:String
    ): Call<List<ItemsItem>>
}