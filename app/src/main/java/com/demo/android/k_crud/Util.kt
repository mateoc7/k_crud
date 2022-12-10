package com.demo.android.k_crud

import android.util.Log
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
    action: String,
    position: Int,
    activity: FragmentActivity
) {
    val builder: AlertDialog.Builder = activity.let {
        AlertDialog.Builder(it)
    }

    builder.setMessage(msg)
    builder.setTitle("Atención")

    builder.setPositiveButton("Aceptar") { dialog, id ->
        Log.i("Util", "Positive button pressed")
    }

    builder.setNegativeButton("Cancelar") { dialog, id ->
        Log.i("Util", "Negative button pressed")
    }

    val dialog: AlertDialog = builder.create()

    dialog.show()
}