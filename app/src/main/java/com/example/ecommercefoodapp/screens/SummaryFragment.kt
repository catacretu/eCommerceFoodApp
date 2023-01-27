package com.example.ecommercefoodapp.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.adapter.CartItemAdapter
import com.example.ecommercefoodapp.adapter.FoodItemAdapter
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.databinding.FragmentSummaryBinding
import com.example.ecommercefoodapp.listener.ItemClickListener
import com.example.ecommercefoodapp.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FoodViewModel>()
    private lateinit var cartItemAdapter: CartItemAdapter
    private lateinit var sh: SharedPreferences
    private val args: SummaryFragmentArgs by navArgs()

    private val backHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val action = SummaryFragmentDirections.goFromSummaryFragmentToOrderFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
    private val itemClickListener = object : ItemClickListener {
        override fun onClick(item: FoodItemEntity) {
            val action = SummaryFragmentDirections.goFromSummaryFragmentToProductDetailsFragment(item)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backHandler)
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        sh = requireActivity().getSharedPreferences("shopping_cart", Context.MODE_PRIVATE)
        val view = binding.root
        binding.editOrder.setOnClickListener {
            val action = SummaryFragmentDirections.goFromSummaryFragmentToCartFragment()
            findNavController().navigate(action)
        }
        binding.editAddress.setOnClickListener {
            val action = SummaryFragmentDirections.goFromSummaryFragmentToOrderFragment()
            findNavController().navigate(action)
        }
        binding.confirmOrder.setOnClickListener {
            val action = SummaryFragmentDirections.goFromSummaryFragmentToDoneFragment()
            findNavController().navigate(action)
        }
        computeDeliveryAddress()
        initRecyclerView(view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun computeDeliveryAddress() {
        val orderItem = args.orderItem
        val orderItemToString = "${orderItem.firstName} ${orderItem.lastName},\n" +
                "${orderItem.address}\n" +
                "${orderItem.city}, ${orderItem.phone}, ${orderItem.county}\n" +
                orderItem.delivery
        binding.address.text = orderItemToString
    }

    private fun initRecyclerView(view: View) {
        viewModel.getFoodObserver().observe(viewLifecycleOwner) { t ->
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            viewModel.filterCartItemsFromDataList(requireActivity())
            binding.amountTotal.text = viewModel.computeFinalPrice()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            cartItemAdapter =
                CartItemAdapter(
                    viewModel.cartItemList.toList(),
                    view.context,
                    itemClickListener,
                    null,
                    null,
                    true
                )
            recyclerView.adapter = cartItemAdapter
        }
    }
}