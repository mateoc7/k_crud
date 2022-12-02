package com.demo.android.k_crud.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.demo.android.k_crud.R
import com.demo.android.k_crud.databinding.FragmentManagementUserBinding

class ManagementUserFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentManagementUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_management_user,
            container, false
        )

        binding.btnAddUser.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementUserFragment_to_addUserFragment)
        }

        return binding.root
    }
}