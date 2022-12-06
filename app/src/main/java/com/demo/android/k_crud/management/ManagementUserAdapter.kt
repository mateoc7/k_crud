package com.demo.android.k_crud.management

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.k_crud.R
import com.demo.android.k_crud.database.Person

class ManagementUserAdapter : RecyclerView.Adapter<ManagementUserAdapter.ViewHolder>() {

    var data = listOf<Person>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameString: TextView = itemView.findViewById(R.id.name_string);
        private val emailString: TextView = itemView.findViewById(R.id.email_string);

        fun bind(item: Person) {
            val res = itemView.context.resources
            nameString.text = item.names
            emailString.text = item.email
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
                return ViewHolder(view)
            }
        }
    }
}