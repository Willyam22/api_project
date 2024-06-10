package com.example.subawalgit.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.subawalgit.database.FavoriteUser
import com.example.subawalgit.database.FavoriteUserRepository

class  FavoriteActivityViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository:FavoriteUserRepository = FavoriteUserRepository(application)
    fun getALlUser():LiveData<List<FavoriteUser>> =mFavoriteUserRepository.getAllUser()



}