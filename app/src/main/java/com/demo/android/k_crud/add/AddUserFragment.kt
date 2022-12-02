package com.demo.android.k_crud.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.demo.android.k_crud.R
import com.demo.android.k_crud.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentAddUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_user,
            container, false
        )

        binding.btnCancelRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_addUserFragment_to_managementUserFragment)
        }

        return binding.root
    }
}