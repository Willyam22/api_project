package com.example.subawalgit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.subawalgit.R
import com.example.subawalgit.data.response.FollowResponse
import com.example.subawalgit.data.response.ResponseDetail
import com.example.subawalgit.data.retrofit.ApiConfig
import com.example.subawalgit.database.FavoriteUser
import com.example.subawalgit.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val PROFILE_NAME = "profile_name"
        private const val TAG = "DetailActivity"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        const val EXTRA_PRO = "extra_pro"
    }

    private var favoriteUser:FavoriteUser = FavoriteUser()
    private lateinit var detailActivityViewModel: DetailActivityViewModel
    private var avatarU:String? = null
    private var isFaved:Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailActivityViewModel = obtainViewModel(this)

        val name_profile = intent.getStringExtra(PROFILE_NAME)



        val sectionPagerAdapter = SectionPagerAdapter(this, "$name_profile")
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f


        getDetail("$name_profile")




        binding.fabAdd.setOnClickListener {
            if(isFaved == true){
                val login = binding?.detailUsername?.text.toString()
                detailActivityViewModel.deleteu(login)
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
            }else{
                val login = binding?.detailUsername?.text.toString()
                val username = binding?.detailName?.text.toString()
                val avatar = avatarU
                favoriteUser.let{ favoriteUser ->
                    favoriteUser?.login = login
                    favoriteUser?.username = username
                    favoriteUser?.avatar = avatar
                }
                detailActivityViewModel.insert(favoriteUser as FavoriteUser)
                Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
            }
        }


        binding.fabClear.setOnClickListener{
            val login = binding?.detailUsername?.text.toString()
            detailActivityViewModel.deleteu("This is Name")
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
        }


    }



    private fun getDetail(detail : String){
        showLoading(true)
        val client = ApiConfig.getApiService().getDetailProfile(detail)
        client.enqueue(object: Callback<ResponseDetail>{
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful) {
                    showLoading(false)
                    val responseBody = response.body()
                    if (responseBody != null) {
                        binding.detailName.text = "${responseBody.name}"
                        binding.detailUsername.text = "${responseBody.login}"
                        binding.followers.text = "${responseBody.followers} Followers"
                        binding.following.text = "${responseBody.following} Following"
                        avatarU = "${responseBody.avatarUrl}"
                        isFav("${responseBody.login}")
                        Glide.with(this@DetailActivity)
                            .load("${responseBody.avatarUrl}")
                            .into(binding.imgDetail)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun isFav(login:String) {

        detailActivityViewModel.getFav(login).observe(this){favList->
            if(!favList.isEmpty()){
                binding.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
                isFaved = true
            }else{
                binding.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
                isFaved = false
            }
        }
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailActivityViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailActivityViewModel::class.java)
    }


}