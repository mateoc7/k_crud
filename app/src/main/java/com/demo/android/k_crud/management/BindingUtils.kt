package com.demo.android.k_crud.management

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.Person

@BindingAdapter("namesPerson")
fun TextView.setNamesPerson(person: Person?) {
    person?.let {
        text = it.names
    }
}

@BindingAdapter("emailPerson")
fun TextView.setEmailPerson(person: Person?) {
    person?.let {
        text = it.email
    }
}

@BindingAdapter("imagePerson")
fun ImageView.setImagePerson(person: Person?) {
    person?.let {
        when (it.sex) {
            "Masculino" -> setImageResource(R.drawable.ic_user_male)
            "Femenino" -> setImageResource(R.drawable.ic_user_female)
        }
    }
}

@BindingAdapter("genderPerson")
fun TextView.setGenderPerson(person: Person?) {
    person?.let {
        text = it.sex
    }
}

@BindingAdapter("phonePerson")
fun TextView.setPhonePerson(person: Person?) {
    person?.let {
        text = it.phone
    }
}