package com.example.ecommercefoodapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.databinding.FragmentHomeBinding
import com.example.ecommercefoodapp.data.remote.FoodAPI
import com.example.ecommercefoodapp.adapter.FoodAdapter
import com.example.ecommercefoodapp.data.remote.RetrofitHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecyclerView(view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView(view: ConstraintLayout) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val foodAPI = RetrofitHelper.getInstance().create(FoodAPI::class.java)
//        lifecycleScope.launchWhenCreated {
//            try {
//                val response = foodAPI.getFood()
//                if(response.isSuccessful) {
//                    val dataList = response.body()!!
//                    val foodAdapter = FoodAdapter(dataList, view.context)
//                    recyclerView.adapter = foodAdapter
//                }
//                else {
//                    Log.d("Food","Here!")
//                }
//            } catch (Ex:Exception) {
//                Log.e("Error", Ex.stackTraceToString())
//            }
//        }
    }
}

