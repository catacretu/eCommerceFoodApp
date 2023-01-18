package com.example.ecommercefoodapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.adapter.CartItemAdapter
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.databinding.FragmentCartBinding
import com.example.ecommercefoodapp.listener.ItemClickListener
import com.example.ecommercefoodapp.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FoodViewModel>()
    private lateinit var dataList: List<FoodItemEntity>

    private val backHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val action = ProductDetailsFragmentDirections.goBackToHomeScreen()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
    private val itemClickListener = object : ItemClickListener {
        override fun onClick(item: FoodItemEntity) {
            val action = CartFragmentDirections.goToProductDetailsFragmentFromCartFragment(item)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backHandler)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecyclerView(view)
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(view: View) {
        viewModel.getFoodObserver().observe(viewLifecycleOwner) { t ->
            dataList = t!!
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val cartItemAdapter = CartItemAdapter(dataList, view.context, itemClickListener)
            recyclerView.adapter = cartItemAdapter
        }
    }

}