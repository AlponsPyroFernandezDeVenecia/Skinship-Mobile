package com.application.dripjoycoffee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.dripjoycoffee.databinding.FragmentSignup1Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signup1Fragment : Fragment() {

    private lateinit var binding: FragmentSignup1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignup1Binding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_diamondFragment)
        }
        binding.next.setOnClickListener {
            val inputName = binding.Username.text.toString().trim()
            val inputEmail = binding.Email.text.toString().trim()
            val inputPassword = binding.password.text.toString().trim()
            val inputConfirmPassword = binding.confirmPassword.text.toString().trim()

            if (inputPassword != inputConfirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (inputName.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(requireContext(), "napindot", Toast.LENGTH_LONG).show()
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

            val request = RegisterRequest(inputName, inputEmail, inputPassword, inputConfirmPassword)
            registerUser(request)
        }
        return binding.root
    }

    private fun registerUser(request: RegisterRequest) {
        RetrofitClient.instance.registerUser(
            request.name,
            request.email,
            request.password,
            request.confirmpassword
        ).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        if (registerResponse.success) {
                            // Registration successful, navigate to home fragment
                            requireActivity().runOnUiThread {

                                Toast.makeText(requireContext(), registerResponse.message ?: "Registration successful", Toast.LENGTH_LONG).show()
                                Log.e("Registration successful", "Response: $registerResponse")
                                Log.e("kkkk", "Response: $registerResponse")
                            }
                        } else {
                            // Registration failed, display error message
                            Toast.makeText(requireContext(), registerResponse.message ?: "Registration failed", Toast.LENGTH_LONG).show()
                            Log.e("Registration failed", "Response: $registerResponse")
                            Log.e("gggg", "Response: $registerResponse")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network Error. Please try again later.", Toast.LENGTH_LONG).show()
            }
        })
    }
}
