package com.demo.android.k_crud.detail

import androidx.lifecycle.ViewModel
import com.demo.android.k_crud.database.PersonDao

class DetailUserViewModel(private val personId: Long = 0L, database: PersonDao) : ViewModel() {

    private val person = database.getPersonById(personId)

    fun getPerson() = person
}