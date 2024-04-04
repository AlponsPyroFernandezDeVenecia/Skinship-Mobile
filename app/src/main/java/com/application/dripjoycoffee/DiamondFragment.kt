package com.application.dripjoycoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.dripjoycoffee.databinding.FragmentDiamondBinding

class DiamondFragment : Fragment() {

    private lateinit var binding: FragmentDiamondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiamondBinding.inflate(inflater,container,false)
        binding.phone.setOnClickListener {
            findNavController().navigate(R.id.action_diamondFragment_to_signup1Fragment2)
        }
        binding.Login.setOnClickListener {
            findNavController().navigate(R.id.action_diamondFragment_to_loginFragment)
        }
        return binding.root
    }

}