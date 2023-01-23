package com.example.ecommercefoodapp.screens

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var cartItemAdapter: CartItemAdapter
    private lateinit var dataList: List<FoodItemEntity>
    private lateinit var cartItemList: MutableList<FoodItemEntity>
    private lateinit var sh: SharedPreferences

    private val backHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val action = CartFragmentDirections.goToHomeFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
    private val itemClickListener = object : ItemClickListener {
        override fun onClick(item: FoodItemEntity) {
            val action = CartFragmentDirections.goToProductDetailsFragmentFromCartFragment(item)
            findNavController().navigate(action)
        }
    }

    private val addClickListener = object : ItemClickListener {
        override fun onClick(item: FoodItemEntity) {
            sh.edit().apply {
                val quantity = item.quantity + 1
                item.quantity = quantity
                putInt(item.title, quantity)
                updateTotalAmountPrice(item,"+")
            }.apply()
            cartItemAdapter.notifyDataSetChanged()
        }
    }

    private val decreaseClickListener = object : ItemClickListener {
        override fun onClick(item: FoodItemEntity) {
            sh.edit().apply {
                if (item.quantity == 0)
                    return
                val quantity = item.quantity - 1
                item.quantity = quantity
                putInt(item.title, quantity)
                updateTotalAmountPrice(item,"-")
            }.apply()
            cartItemAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backHandler)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        sh = requireActivity().getSharedPreferences("shopping_cart", Context.MODE_PRIVATE)
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
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            dataList = t!!
            filterCartItemsFromDataList()
            binding.totalAmount.text = computeFinalPrice()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cartItemAdapter =
                CartItemAdapter(
                    cartItemList.toList(),
                    view.context,
                    itemClickListener,
                    addClickListener,
                    decreaseClickListener
                )
            recyclerView.adapter = cartItemAdapter
        }

    }

    private fun filterCartItemsFromDataList() {
        cartItemList = mutableListOf()
        val shItemsList = sh.all
        for (item in dataList)
            for (shItem in shItemsList.entries.iterator()) {
                if (item.title == shItem.key) {
                    item.quantity = shItem.value as Int
                    cartItemList.add(item)
                }
            }
    }

    private fun computeFinalPrice(): String {
        var totalAmount = 0
        for (item in cartItemList)
            {var price = extractPrice(item.price)
                price = price * item.quantity
            totalAmount+= price}
        return "$totalAmount lei"
    }

    private fun extractPrice(price: String): Int {
       return price.substring(0,price.indexOf(" ")).toInt()
    }

    private fun updateTotalAmountPrice(item: FoodItemEntity, flagOperator: String){
        var newPrice = extractPrice(binding.totalAmount.text as String)
        if(flagOperator.equals("+"))
            newPrice = newPrice + extractPrice(item.price)
        else if(flagOperator.equals("-"))
            newPrice = newPrice - extractPrice(item.price)
        binding.totalAmount.text = "$newPrice lei"
    }
}