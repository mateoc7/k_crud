package com.demo.android.k_crud.management

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.k_crud.database.PersonDao
import com.demo.android.k_crud.listeners.ListenerAction
import com.demo.android.k_crud.listeners.ListenerStatus

class ManagementUserViewModelFactory(
    private val database: PersonDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagementUserViewModel::class.java))
            return ManagementUserViewModel(database) as T
        throw IllegalArgumentException("Unknown ManagementUserViewModelFactory class")
    }
}