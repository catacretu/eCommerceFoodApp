package com.example.ecommercefoodapp.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ecommercefoodapp.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val backHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val action = ProductDetailsFragmentDirections.goBackToHomeScreen()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backHandler)
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.toolbar.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProductDetailsData()

        binding.addToCartButton.setOnClickListener {
            val sh = requireActivity().getSharedPreferences("shopping_cart", Context.MODE_PRIVATE)
//            sh.edit().clear().apply()
            val itemName = binding.title.text.toString()
            var itemQuantity = 0

            sh.edit().apply {

                if (sh.contains(itemName)) {
                    itemQuantity = sh.getInt(itemName, 0)
                }

                if (itemQuantity > 0) { // if item exists, we will increase the quantity
                    itemQuantity++
                    putInt(itemName, itemQuantity)
                } else
                    putInt(itemName, 1) // if  not, we will put it in the cart

            }.apply()
        }
    }

    fun initProductDetailsData() {
        val foodItem = args.foodItem
        binding.title.text = foodItem.title
        Glide.with(binding.root)
            .load(foodItem.imageUrl)
            .into(binding.image)
        binding.price.text = foodItem.price
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}