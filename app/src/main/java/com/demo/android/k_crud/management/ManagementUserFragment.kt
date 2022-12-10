package com.demo.android.k_crud.management

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.PersonDatabase
import com.demo.android.k_crud.databinding.FragmentManagementUserBinding

class ManagementUserFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentManagementUserBinding

    // ViewModel
    private lateinit var factory: ManagementUserViewModelFactory
    private lateinit var viewModel: ManagementUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_management_user,
            container, false
        )

        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)

        // Obtenga el contexto del fragment actual
        val application = requireNotNull(this.activity).application

        // Obtenga una referencia de la base de datos
        val dataSource = PersonDatabase.getInstance(application).personDatabaseDao

        // Cree una instancia de ViewModel Factory
        factory = ManagementUserViewModelFactory(dataSource)

        // Obtenga una referencia al ViewModel asociado con este fragmento
        viewModel = ViewModelProvider(this, factory)[ManagementUserViewModel::class.java]

        val adapter = ManagementUserAdapter()
        binding.personList.adapter = adapter

        // Observe la lista proporcionada por la base de datos
        viewModel.people.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        return binding.root
    }

    @Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    @Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}