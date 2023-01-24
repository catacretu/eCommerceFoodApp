package com.example.ecommercefoodapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.databinding.FragmentDoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoneBinding.inflate(inflater,container,false)
        binding.backToHomeFromDone.setOnClickListener {
            val action = DoneFragmentDirections.goFromDoneFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}