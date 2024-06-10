package com.example.subawalgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subawalgit.R
import com.example.subawalgit.data.response.ItemsItem
import com.example.subawalgit.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FollowersFragment : Fragment() {
    private lateinit var rvFollowers : RecyclerView
    private lateinit var Progress: ProgressBar

    companion object{
        const val PROFILE_NAME = "profile_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFollowers = view.findViewById(R.id.rv_followers)
        Progress = view.findViewById(R.id.progressBar)
        val proName = arguments?.getString(PROFILE_NAME)


        getFollowersList("$proName ")

    }

    private fun getFollowersList(user: String){
        showLoading(true)
        var client = ApiConfig.getApiService().getFollowersList(user)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if(response.isSuccessful){
                    showLoading(false)
                    val responseBody: List<ItemsItem>? = response.body()
                    if(responseBody != null){
                        setFollowers(responseBody)
                    }
                }else{
                    Log.e("followers", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                showLoading(false)
                Log.e("followers", "onFailure: ${t.message}")
            }

        })
    }

    private fun setFollowers(followerList: List<ItemsItem>){
        rvFollowers.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProfileAdapter()
        adapter.submitList(followerList)
        rvFollowers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            Progress.visibility = View.VISIBLE
        }else{
            Progress.visibility = View.GONE
        }
    }


}