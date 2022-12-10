package com.demo.android.k_crud.management

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.Person

@BindingAdapter("namesPerson")
fun TextView.setNamesPerson(person: Person) {
    text = person.names
}

@BindingAdapter("formatEmail")
fun TextView.setFormatEmailPerson(person: Person) {
    text = person.email
}

@BindingAdapter("imagePerson")
fun ImageView.setImagePerson(person: Person) {
    when (person.sex) {
        "Masculino" -> setImageResource(R.drawable.ic_user_male)
        "Femenino" -> setImageResource(R.drawable.ic_user_female)
    }
}