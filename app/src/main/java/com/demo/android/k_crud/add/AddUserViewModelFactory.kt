package com.demo.android.k_crud.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.k_crud.callbacks.ListenerStatus

class AddUserViewModelFactory(private val callback: ListenerStatus) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java))
            return AddUserViewModel(callback) as T
        throw IllegalArgumentException("Unknown AddUserViewModel class")
    }
}