package com.demo.android.k_crud.management

import androidx.lifecycle.ViewModel
import com.demo.android.k_crud.database.PersonDao
import com.demo.android.k_crud.listeners.ListenerStatus

class ManagementUserViewModel(
    private val listener: ListenerStatus,
    private val database: PersonDao
) : ViewModel() {

    val people = database.getAllPerson()

}