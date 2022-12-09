package com.demo.android.k_crud

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.demo.android.k_crud.listeners.ListenerAction

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
fun alertDialog(
    msg: String,
    action: String,
    position: Int,
    listener: ListenerAction,
    activity: FragmentActivity
) {
    val builder: AlertDialog.Builder = activity.let {
        AlertDialog.Builder(it)
    }

    builder.setMessage(msg)
    builder.setTitle("Atención")

    builder.setPositiveButton("Aceptar") { dialog, id ->
        listener.onAction(action, position)
    }

    builder.setNegativeButton("Cancelar") { dialog, id ->
        Log.i("Util", "Negative button pressed")
    }

    val dialog: AlertDialog = builder.create()

    dialog.show()
}