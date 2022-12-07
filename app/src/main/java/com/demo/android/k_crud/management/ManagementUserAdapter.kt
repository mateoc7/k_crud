package com.demo.android.k_crud.management

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.Person
import com.demo.android.k_crud.listeners.ListenerAction

class ManagementUserAdapter(private val listener: ListenerAction) :
    RecyclerView.Adapter<ManagementUserAdapter.ViewHolder>() {

    var data = listOf<Person>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView: View, private val listener: ListenerAction) :
        RecyclerView.ViewHolder(itemView), OnClickListener {

        private val nameString: TextView = itemView.findViewById(R.id.name_string);
        private val emailString: TextView = itemView.findViewById(R.id.email_string);
        private val btnEdit: ImageView = itemView.findViewById(R.id.btn_edit)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)


        fun bind(item: Person) {
            val res = itemView.context.resources
            nameString.text = item.names
            emailString.text = item.email
            btnEdit.setOnClickListener(this)
            btnDelete.setOnClickListener(this)
        }

        companion object {
            fun from(parent: ViewGroup, listener: ListenerAction) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
                return ViewHolder(view, listener)
            }
        }

        override fun onClick(view: View?) {
            if (view is ImageView) {
                when (view.id) {
                    btnEdit.id -> listener.editAction(adapterPosition)
                    btnDelete.id -> listener.deleteAction(adapterPosition)
                }
            }
        }
    }
}