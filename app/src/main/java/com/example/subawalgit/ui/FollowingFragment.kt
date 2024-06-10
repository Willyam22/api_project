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
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FollowingFragment : Fragment() {

    companion object{
        const val PROFILE_NAME = "profile_name"
    }

    private lateinit var rvFollowing: RecyclerView
    private lateinit var proBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        rvFollowing = view.findViewById(R.id.rv_following)
        proBar = view.findViewById(R.id.proBar)
        val proName = arguments?.getString(FollowersFragment.PROFILE_NAME)

        getFollowingList("$proName")


    }

    private fun getFollowingList(user:String){
        showLoading(true)
        var client = ApiConfig.getApiService().getFollowingList(user)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if(response.isSuccessful){
                    showLoading(false)
                    val responseBody: List<ItemsItem>? = response.body()
                    if(responseBody != null){
                        setFollowing(responseBody)
                        Log.e("following", "$responseBody")
                    }
                }else{
                    Log.e("following", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                showLoading(false)
                Log.e("following", "onFailure: ${t.message}")
            }

        })
    }

    private fun setFollowing(followerList: List<ItemsItem>){
        rvFollowing.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProfileAdapter()
        adapter.submitList(followerList)
        rvFollowing.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            proBar.visibility = View.VISIBLE
        }else{
            proBar.visibility = View.GONE
        }
    }
}