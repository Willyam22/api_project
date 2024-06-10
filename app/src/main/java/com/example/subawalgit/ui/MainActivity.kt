package com.example.subawalgit.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subawalgit.data.response.ItemsItem
import com.example.subawalgit.databinding.ActivityMainBinding
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.subawalgit.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        Thread.sleep(3000)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportActionBar?.hide()

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.listItems.observe(this){listItems->
            setProfileData(listItems)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }



        val layoutManager = LinearLayoutManager(this)
        binding.rvProfile.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvProfile.addItemDecoration(itemDecoration)

        binding.searchBar.inflateMenu(R.menu.fav_menu)
        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu1 ->{
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu2->{
                    val intent = Intent(this, ModeActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    if(searchBar.text.isNotEmpty()){
                        //searchProfile("${searchView.text}")
                        mainViewModel.searchProfile("${searchView.text}")
                    }else{
                        mainViewModel.listItems.observe(this@MainActivity){listItems->
                            setProfileData(listItems)
                        }
                    }
                    false
                }
        }

    }

    private fun setProfileData(profileList: List<ItemsItem>) {
        val adapter = ProfileAdapter()
        adapter.submitList(profileList)
        binding.rvProfile.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}