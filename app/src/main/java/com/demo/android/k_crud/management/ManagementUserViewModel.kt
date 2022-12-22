package com.demo.android.k_crud.management

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.k_crud.database.PersonDao
import kotlinx.coroutines.launch

class ManagementUserViewModel(private val database: PersonDao) : ViewModel() {

    val people = database.getAllPerson()

    // Evento de item seleccionado
    private val _eventActionPersonClicked = MutableLiveData<String>()
    val eventActionPersonClicked: LiveData<String>
        get() = _eventActionPersonClicked

    fun onPersonClicked(key: String) {
        _eventActionPersonClicked.value = key
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun onActionCompleted() {
        _eventActionPersonClicked.value = null
    }

    fun onDelete(id: Long) {
        viewModelScope.launch {
            val person = database.findPersonById(id)
            database.delete(person)
        }
    }
}