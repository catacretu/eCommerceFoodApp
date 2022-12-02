package com.example.ecommercefoodapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercefoodapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity,2)

        //populate food items list
        val dataList = ArrayList<FoodItemModel>()
        for(i in 1 .. 10)
            dataList.add(
                FoodItemModel(
                    "Food $i",
                    "10$",
                    "https://www.bacanialuitomita.ro/wp-content/uploads/2020/11/124803226_4604318162943378_2793598950690771519_o.jpg"
                )
            )
        val foodAdapter = FoodAdapter(dataList, view.context)
        recyclerView.adapter = foodAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

