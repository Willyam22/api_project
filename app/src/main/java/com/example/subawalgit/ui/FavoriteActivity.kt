package com.example.subawalgit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subawalgit.R
import com.example.subawalgit.databinding.ActivityDetailBinding
import com.example.subawalgit.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private lateinit var favoriteActivityViewModel: FavoriteActivityViewModel

    private lateinit var adapter : FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteViewModel =obtainViewModel(this)
        favoriteViewModel.getALlUser().observe(this){userList->
            adapter.setListUser(userList)
        }


        adapter = FavoriteAdapter()

        binding?.rvFavorite?.layoutManager = LinearLayoutManager(this)
        binding?.rvFavorite?.setHasFixedSize(true)
        binding?.rvFavorite?.adapter = adapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteActivityViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteActivityViewModel::class.java)
    }
}