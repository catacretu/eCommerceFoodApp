package com.example.ecommercefoodapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.databinding.FragmentOrderBinding
import com.example.ecommercefoodapp.listener.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val backHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val action = OrderFragmentDirections.goBackToCartFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backHandler)
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        binding.continueButton.setOnClickListener {
            val action = OrderFragmentDirections.goToSummaryFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
