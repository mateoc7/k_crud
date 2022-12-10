package com.demo.android.k_crud

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

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