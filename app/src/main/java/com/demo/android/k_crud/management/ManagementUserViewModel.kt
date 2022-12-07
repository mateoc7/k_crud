package com.demo.android.k_crud.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.k_crud.database.Person
import com.demo.android.k_crud.database.PersonDao
import com.demo.android.k_crud.listeners.ListenerAction
import com.demo.android.k_crud.listeners.ListenerStatus
import kotlinx.coroutines.launch

class ManagementUserViewModel(private val database: PersonDao) : ViewModel() {

    val people = database.getAllPerson()

    fun onDelete(position: Int) {
        viewModelScope.launch { delete(people.value?.get(position)) }
    }

    private suspend fun delete(person: Person?) {
        database.delete(person)
    }
}