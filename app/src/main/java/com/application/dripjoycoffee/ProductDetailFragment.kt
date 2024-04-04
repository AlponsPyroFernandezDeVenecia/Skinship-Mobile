package com.application.dripjoycoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.dripjoycoffee.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        // Retrieve product details from arguments
        val product = arguments?.getSerializable("PRODUCT") as ProductDataClass?

        // Set product details to views
        product?.let {
            binding.Title.text = it.name
            binding.priceInt.text = "Price: ₱${it.price}"
            //binding.description.text = it.description
            // Load product image using Glide or any other image loading library
            // Example:
            // Glide.with(this)
            //     .load(it.imageUrl)
            //     .into(binding.productImage)
        }

        binding.back.setOnClickListener {
            // Navigate back to home fragment
            findNavController().navigate(R.id.product_detail_to_homeFragment)
        }

        return binding.root
    }
}
