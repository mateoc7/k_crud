package com.demo.android.k_crud.management

import com.demo.android.k_crud.database.Person

class ManagementUserListener(val clickListener: (personId: Long) -> Unit) {
    fun onClick(person: Person) = clickListener(person.id)
}