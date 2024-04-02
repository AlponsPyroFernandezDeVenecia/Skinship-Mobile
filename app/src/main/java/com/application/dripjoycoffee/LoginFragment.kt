package com.application.dripjoycoffee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.dripjoycoffee.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email and password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Make an asynchronous call using enqueue method
            RetrofitClient.instance.loginUser(email, password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        val token = loginResponse?.token
                        val userId = loginResponse?.user?.id
                        if (userId != null) {
                            Log.d("MyApp", "User ID: $userId")
                            AuthManager.instance.setUserId(userId.toInt())
                        } else {
                            Log.d("MyApp", "User ID is null or empty")
                        }
                        Toast.makeText(requireContext(), loginResponse?.message, Toast.LENGTH_LONG).show()
                        if (token != null) {
                            AuthManager.instance.setAuthToken(token)
                        }
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(requireContext(), "Incorrect Email or Password", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Handle failure, e.g., show an error message
                    Toast.makeText(requireContext(), "Login failed. Please try again.", Toast.LENGTH_LONG).show()
                }
            })
        }


        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signup1Fragment2)
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_diamondFragment)
        }

        return binding.root
    }
}
