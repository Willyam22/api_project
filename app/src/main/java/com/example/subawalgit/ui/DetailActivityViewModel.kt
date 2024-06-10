package com.example.subawalgit.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.subawalgit.database.FavoriteUser
import com.example.subawalgit.database.FavoriteUserRepository

class DetailActivityViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository : FavoriteUserRepository = FavoriteUserRepository(application)

    fun insert(favoriteuser: FavoriteUser){
        mFavoriteUserRepository.insert(favoriteuser)
    }

    fun update (favoriteuser: FavoriteUser){
        mFavoriteUserRepository.update(favoriteuser)
    }

    fun delete(favoriteuser: FavoriteUser){
        mFavoriteUserRepository.delete(favoriteuser)
    }

    fun deleteu(login:String){
        mFavoriteUserRepository.deleteu(login)
    }

    fun getFav(login:String):LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getFav(login)
}