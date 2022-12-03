package com.demo.android.k_crud.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.android.k_crud.callbacks.ListenerStatus

class AddUserViewModel(private val callback: ListenerStatus) : ViewModel() {

    private var _eventRegister = MutableLiveData<Boolean>()
    val eventRegister: LiveData<Boolean>
        get() = _eventRegister

    fun validateData(names: String, email: String, phone: String, sex: String) {
        if (names.validateData()) {
            if (email.validateData()) {
                if (phone.validateData()) {
                    onRegister()
                    callback.onSuccess("Registro exitoso")
                } else {
                    callback.onError("Teléfono inválido")
                }
            } else {
                callback.onError("Email inválido")
            }
        } else {
            callback.onError("Nombre inválido")
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
}