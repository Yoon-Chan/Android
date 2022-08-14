package com.example.chap11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chap11.databinding.FragmentTwoBinding

class ThreeFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater,container,false)
        return binding.root
    }
}