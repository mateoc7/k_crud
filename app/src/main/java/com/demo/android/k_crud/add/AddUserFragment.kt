package com.demo.android.k_crud.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.demo.android.k_crud.R
import com.demo.android.k_crud.listeners.ListenerStatus
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

        // Operacion cancelada
        binding.btnCancelRegister.setOnClickListener {
            it.findNavController()
                .navigate(AddUserFragmentDirections.actionAddUserFragmentToManagementUserFragment())
        }

        // Iniciar proceso de registro
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
        val sex = getSex(binding.radiogroupGender.checkedRadioButtonId)
        viewModel.validateData(names, email, phone, sex)
    }

    private fun onRegisterComplete() {
        val action = AddUserFragmentDirections.actionAddUserFragmentToManagementUserFragment()
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onRegisterComplete()
    }

    private fun getSex(idGender: Int): String? {
        var sex: String? = null
        if (idGender != -1) {
            // Obtener el id del boton seleccionado y asignar un valor
            when (idGender) {
                R.id.radio_male -> sex = "Masculino"
                R.id.radio_female -> sex = "Femenino"
            }
        }
        return sex
    }

    override fun onSuccess(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}