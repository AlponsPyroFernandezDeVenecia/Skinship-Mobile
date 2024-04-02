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
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.registerUser(inputName, inputEmail, inputPassword, inputConfirmPassword)
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        if (response.isSuccessful) {
                            val registerResponse = response.body()
                            if (registerResponse != null) {
                                if (registerResponse.success) {
                                    // Registration successful, navigate to home fragment
                                    Toast.makeText(requireContext(), registerResponse.message ?: "Registration successful", Toast.LENGTH_LONG).show()
                                    requireActivity().runOnUiThread {
                                        findNavController().navigate(R.id.action_signup1Fragment2_to_homeFragment)
                                    }
                                } else {
                                    // Registration failed, display error message
                                    Toast.makeText(requireContext(), registerResponse.message ?: "Registration failed", Toast.LENGTH_LONG).show()
                                    Log.e("Registration failed", "Response: $registerResponse")
                                    Log.e("dddd", "Response: $registerResponse")
                                }
                            } else {
                                // Response body is null
                                Toast.makeText(requireContext(), "B", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            // HTTP request failed
                            val errorMessage = response.errorBody()?.string() ?: "C"
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), "Network Error. Please try again later.", Toast.LENGTH_LONG).show()
                    }
                })
        }
        return binding.root
    }
}
