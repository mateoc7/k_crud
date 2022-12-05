package com.demo.android.k_crud.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.k_crud.database.PersonDao
import com.demo.android.k_crud.listeners.ListenerStatus

class AddUserViewModelFactory(private val listener: ListenerStatus, private val database: PersonDao) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java))
            return AddUserViewModel(listener, database) as T
        throw IllegalArgumentException("Unknown AddUserViewModel class")
    }
}