package com.example.subawalgit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteuser: FavoriteUser)

    @Update
    fun update(favoriteuser: FavoriteUser)

    @Delete
    fun delete(favoriteuser: FavoriteUser)

    @Query("SELECT * from favoriteuser ORDER BY username ASC")
    fun getUser(): LiveData<List<FavoriteUser>>

    @Query("DELETE FROM favoriteuser WHERE login =:login")
    fun deleteu(login:String)

    @Query("SELECT * FROM favoriteuser WHERE login=:login")
    fun getfav(login:String): LiveData<List<FavoriteUser>>
}