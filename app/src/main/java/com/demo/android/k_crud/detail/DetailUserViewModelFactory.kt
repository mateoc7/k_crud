package com.demo.android.k_crud.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.k_crud.database.PersonDao

class DetailUserViewModelFactory(private val personId: Long, private val database: PersonDao) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(personId, database) as T
        }
        throw IllegalArgumentException("Unknown DetailUserViewModelFactory class")
    }
}