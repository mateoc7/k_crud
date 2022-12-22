package com.demo.android.k_crud

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

/**
 * Use este metodo para mostrar un AlertDialog. Tenga en cuenta que su funcion es ejecutar una
 * accion sobre un dato en especifico ubicado en una clase de datos o una base de datos.
 *
 * @param msg
 * @param action
 * @param position
 * @param activity
 */
@Suppress("UNUSED_ANONYMOUS_PARAMETER")
fun alertDialog(
    msg: String,
    activity: FragmentActivity
) : AlertDialog.Builder {
    val builder: AlertDialog.Builder = activity.let {
        AlertDialog.Builder(it)
    }

    builder.setMessage(msg)
    builder.setTitle("Atención")

    return builder
}