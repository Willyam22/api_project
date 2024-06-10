package com.example.subawalgit.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteUser (
    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(name = "id")
    var id :Int = 0,

    @ColumnInfo(name = "login")
    var login:String? = null,

    @ColumnInfo(name = "username")
    var username:String? = null,

    @ColumnInfo(name = "avatar")
    var avatar:String? = null

): Parcelable