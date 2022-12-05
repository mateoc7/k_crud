package com.demo.android.k_crud.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.k_crud.database.Person
import com.demo.android.k_crud.database.PersonDao
import com.demo.android.k_crud.listeners.ListenerStatus
import kotlinx.coroutines.launch

class AddUserViewModel(private val listener: ListenerStatus, private val database: PersonDao) : ViewModel() {

    private var _eventRegister = MutableLiveData<Boolean>()
    val eventRegister: LiveData<Boolean>
        get() = _eventRegister

    fun validateData(names: String, email: String, phone: String, sex: String?) {
        if (names.validateData()) {
            if (email.validateData()) {
                if (phone.validateData()) {
                    if (!sex.isNullOrEmpty()) {
                        val person = Person(names = names, email = email, phone = phone, sex = sex)
                        viewModelScope.launch { insert(person) }
                        onRegister()
                        listener.onSuccess("Registro exitoso")
                    } else {
                        listener.onError("Sexo inválido")
                    }
                } else {
                    listener.onError("Teléfono inválido")
                }
            } else {
                listener.onError("Email inválido")
            }
        } else {
            listener.onError("Nombre inválido")
        }
    }

    private fun String.validateData(): Boolean {
        return this.trim().isNotEmpty()
    }

    private fun onRegister() {
        _eventRegister.value = true
    }

    fun onRegisterComplete() {
        _eventRegister.value = false
    }

    private suspend fun insert(person: Person) {
        database.insert(person)
    }
}