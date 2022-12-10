package com.demo.android.k_crud.management

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.k_crud.database.Person
import com.demo.android.k_crud.database.PersonDao
import kotlinx.coroutines.launch

class ManagementUserViewModel(private val database: PersonDao) : ViewModel() {

    val people = database.getAllPerson()
}