package com.example.subawalgit.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.subawalgit.database.FavoriteUser

class FavoriteDiffCallBack(private val oldFavoriteUserList: List<FavoriteUser>, private val newFavoriteUser: List<FavoriteUser>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavoriteUserList.size
    override fun getNewListSize(): Int = newFavoriteUser.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavoriteUserList[oldItemPosition].id == newFavoriteUser[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavoriteUser = oldFavoriteUserList[oldItemPosition]
        val newFavoriteUser = newFavoriteUser[newItemPosition]
        return oldFavoriteUser.login == newFavoriteUser.login && oldFavoriteUser.avatar == newFavoriteUser.avatar
    }
}