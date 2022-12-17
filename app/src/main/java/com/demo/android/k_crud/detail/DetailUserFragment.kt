package com.demo.android.k_crud.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.PersonDatabase
import com.demo.android.k_crud.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentDetailUserBinding

    // ViewModel
    private lateinit var factory: DetailUserViewModelFactory
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_user, container, false)

        // Obtenga el contexto del fragment actual
        val application = requireNotNull(this.activity).application

        // Obtenga los argumentos recibidos
        val arguments = DetailUserFragmentArgs.fromBundle(requireArguments())

        // Obtenga una referencia de la base de datos
        val dataSource = PersonDatabase.getInstance(application).personDatabaseDao

        // Cree una instancia de ViewModel Factory
        factory = DetailUserViewModelFactory(arguments.personId, dataSource)

        // Obtenga una referencia al ViewModel asociado con este fragmento
        viewModel = ViewModelProvider(this, factory)[DetailUserViewModel::class.java]

        binding.detailUserViewModel = viewModel

        // Defina el propiertario del ciclo de vida
        binding.lifecycleOwner = this

        viewModel.navigateToManagementUser.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController()
                    .navigate(DetailUserFragmentDirections.actionDetailUserFragmentToManagementUserFragment())
                viewModel.doneNavigating()
            }
        }

        return binding.root
    }
}