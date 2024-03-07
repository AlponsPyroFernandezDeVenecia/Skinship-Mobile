package com.application.dripjoycoffee

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.dripjoycoffee.databinding.FragmentLogoBinding

class LogoFragment : Fragment() {

    private lateinit var binding: FragmentLogoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogoBinding.inflate(inflater,container,false)

        var timer = object : CountDownTimer(3000,20){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                findNavController().navigate(R.id.action_logoFragment_to_diamondFragment)
            }
        }
        timer.start()

        return binding.root

    }

}