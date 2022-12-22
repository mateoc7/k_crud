package com.demo.android.k_crud.management

import com.demo.android.k_crud.database.Person

class ManagementUserListener(val clickListener: (personId: Long, action: String) -> Unit) {
    fun onDetail(person: Person) = clickListener(person.id, "detail")
    fun onDelete(person: Person) = clickListener(person.id, "delete")
    fun onUpdate(person: Person) = clickListener(person.id, "update")
}