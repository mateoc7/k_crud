package com.demo.android.k_crud.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.demo.android.k_crud.R
import com.demo.android.k_crud.callbacks.ListenerStatus
import com.demo.android.k_crud.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment(), ListenerStatus {

    // Binding
    private lateinit var binding: FragmentAddUserBinding

    // ViewModel
    private lateinit var factory: AddUserViewModelFactory
    private lateinit var viewModel: AddUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño de este fragmento
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_user,
            container, false
        )

        // Cree una instancia de ViewModel Factory
        factory = AddUserViewModelFactory(this)

        // Obtenga una referencia al ViewModel asociado con este fragmento.
        viewModel = ViewModelProvider(this, factory)[AddUserViewModel::class.java]

        binding.btnCancelRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_addUserFragment_to_managementUserFragment)
        }
        binding.btnRegister.setOnClickListener {
            onRegister()
            viewModel.eventRegister.observe(viewLifecycleOwner) { success ->
                if (success)
                    onRegisterComplete()
            }
        }

        return binding.root
    }

    private fun onRegister() {
        val names = binding.inputName.text.toString()
        val email = binding.inputEmail.text.toString()
        val phone = binding.inputPhone.text.toString()
        viewModel.validateData(names, email, phone, "")
    }

    private fun onRegisterComplete() {
        val action = AddUserFragmentDirections.actionAddUserFragmentToManagementUserFragment()
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onRegisterComplete()
    }

    override fun onSuccess(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}