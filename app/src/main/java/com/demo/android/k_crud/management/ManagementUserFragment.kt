package com.demo.android.k_crud.management

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.demo.android.k_crud.R
import com.demo.android.k_crud.alertDialog
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

        val adapter = ManagementUserAdapter(ManagementUserListener { item, action ->
            // La clave se conforma de 'id_item-accion'
            val key = "$item-$action"
            viewModel.onPersonClicked(key)
        })
        binding.personList.adapter = adapter

        // Observe la lista proporcionada por la base de datos
        viewModel.people.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        // Observe la accion click sobre un item
        viewModel.eventActionPersonClicked.observe(viewLifecycleOwner) { key ->
            key?.let {
                val splitKey = it.split('-')
                val id = splitKey[0].toLong()
                val action = splitKey[1]

                when (action) {
                    "detail" -> onNavigateDetail(id)
                    "delete" -> onDelete(id)
                    "update" -> onUpdate(id)
                    else -> Log.i("ManagementUserFragment", "No action selected")
                }

                viewModel.onActionCompleted()
            }
        }

        // Defina el propiertario del ciclo de vida
        binding.lifecycleOwner = this

        return binding.root
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING")
    private fun onDelete(id: Long) {
        val dialog = alertDialog(
            "¿Está seguro que desea eliminar a esta persona?",
            requireNotNull(this.activity)
        )
        dialog.setPositiveButton(R.string.label_accept) { dialog, which ->
            viewModel.onDelete(id)
        }
        dialog.setNegativeButton(R.string.label_cancel) { dialog, which ->
            Log.i("ManagementUserFragment", "Cancel button selected")
        }
        dialog.show()
    }

    private fun onUpdate(id: Long) {
        Log.i("ManagementUserFragment", "Action update selected")
    }

    private fun onNavigateDetail(id: Long) {
        val action =
            ManagementUserFragmentDirections.actionManagementUserFragmentToDetailUserFragment(id)
        NavHostFragment.findNavController(this).navigate(action)
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