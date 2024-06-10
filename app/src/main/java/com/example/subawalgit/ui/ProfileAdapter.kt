package com.example.subawalgit.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.subawalgit.data.response.ItemsItem
import com.example.subawalgit.databinding.ItemRowProfileBinding

class ProfileAdapter : ListAdapter<ItemsItem, ProfileAdapter.ListViewHolder >(DIFF_CALLBACK){

    class ListViewHolder(val binding: ItemRowProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: ItemsItem){
            binding.tvItemName.text ="${profile.login}"
            Glide.with(itemView.context)
                .load("${profile.avatarUrl}")
                .into(binding.imgItemPhoto)

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailActivity::class.java)

                intent.putExtra(DetailActivity.PROFILE_NAME, "${profile.login}")
                itemView.context.startActivity(intent)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}