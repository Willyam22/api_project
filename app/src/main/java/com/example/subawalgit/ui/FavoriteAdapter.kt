package com.example.subawalgit.ui

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.subawalgit.database.FavoriteUser
import com.example.subawalgit.databinding.ItemRowProfileBinding
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.UserViewHolder>() {

    private val ListFavoriteUser =ArrayList<FavoriteUser>()

    fun setListUser(listUser: List<FavoriteUser>){
        val diffCallback = FavoriteDiffCallBack(this.ListFavoriteUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.ListFavoriteUser.clear()
        this.ListFavoriteUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =ItemRowProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(ListFavoriteUser[position])
    }

    override fun getItemCount(): Int {
        return ListFavoriteUser.size
    }

    inner class UserViewHolder(private val binding:ItemRowProfileBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(favoriteUser: FavoriteUser){
            with(binding){
                tvItemName.text =favoriteUser.login
                Glide.with(itemView.context)
                    .load(favoriteUser.avatar)
                    .into(binding.imgItemPhoto)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)

                intent.putExtra(DetailActivity.PROFILE_NAME, favoriteUser.login)
                itemView.context.startActivity(intent)
            }
        }
    }
}