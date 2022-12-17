package com.demo.android.k_crud.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.android.k_crud.database.PersonDao

class DetailUserViewModel(private val personId: Long = 0L, database: PersonDao) : ViewModel() {

    private val person = database.getPersonById(personId)

    private val _navigateToManagementUser = MutableLiveData<Boolean?>()
    val navigateToManagementUser: LiveData<Boolean?>
        get() = _navigateToManagementUser

    fun getPerson() = person

    fun doneNavigating() {
        _navigateToManagementUser.value = null
    }

    fun onClose() {
        _navigateToManagementUser.value = true
    }
}