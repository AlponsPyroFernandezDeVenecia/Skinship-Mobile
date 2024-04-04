package com.application.dripjoycoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.dripjoycoffee.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<ProductDataClass>
    private lateinit var productService: ProductService
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        productService = ProductService(requireContext())
        productList = mutableListOf()
        productAdapter = ProductAdapter(productList) { product ->
            findNavController().navigate(R.id.action_homeFragment_to_product_detail)
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = productAdapter

        fetchProducts()

        binding.profile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.logout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        return view
    }

    private fun fetchProducts() {
        productService.getProducts { products ->
            products?.let {
                productList.clear()
                productList.addAll(it)
                productAdapter.notifyDataSetChanged()
            }
        }
    }
}
