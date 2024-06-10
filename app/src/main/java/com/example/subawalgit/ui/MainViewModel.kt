package com.example.subawalgit.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.subawalgit.data.response.ItemsItem
import com.example.subawalgit.data.response.ProfileResponse
import com.example.subawalgit.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel: ViewModel() {
    private val _listItems = MutableLiveData<List<ItemsItem>>()
    val listItems : LiveData<List<ItemsItem>> = _listItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        findProfile()
    }

    companion object{
        private const val TAG = "MainViewModel"
    }

    private fun findProfile() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProfile()
        client.enqueue(object : Callback<List<ItemsItem>> {

            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    _listItems.value =response.body()
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun searchProfile(search:String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchProfile(search)
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _listItems.value =response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}