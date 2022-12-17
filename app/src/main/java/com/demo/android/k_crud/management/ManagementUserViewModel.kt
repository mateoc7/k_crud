package com.demo.android.k_crud.management

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.k_crud.database.Person
import com.demo.android.k_crud.database.PersonDao
import kotlinx.coroutines.launch

class ManagementUserViewModel(private val database: PersonDao) : ViewModel() {

    val people = database.getAllPerson()

    private val _navigateToPersonDetail = MutableLiveData<Long>()
    val navigateToPersonDetail
        get() = _navigateToPersonDetail

    fun onPersonClicked(id: Long) {
        _navigateToPersonDetail.value = id
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun onPersonDetailNavigated() {
        _navigateToPersonDetail.value = null
    }
}