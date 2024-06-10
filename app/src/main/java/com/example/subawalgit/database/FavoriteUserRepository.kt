package com.example.subawalgit.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavoriteUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteuserdao()
    }

    fun getAllUser() :LiveData<List<FavoriteUser>> = mFavoriteUserDao.getUser()

    fun insert(favoriteuser: FavoriteUser){
        executorService.execute{mFavoriteUserDao.insert(favoriteuser)}
    }

    fun delete(favoriteuser: FavoriteUser){
        executorService.execute{mFavoriteUserDao.delete(favoriteuser)}
    }

    fun update(favoriteuser: FavoriteUser){
        executorService.execute{mFavoriteUserDao.update(favoriteuser)}
    }

    fun getFav(login:String): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getfav(login)

    fun deleteu(login:String){
        executorService.execute{mFavoriteUserDao.deleteu(login)}
    }

}