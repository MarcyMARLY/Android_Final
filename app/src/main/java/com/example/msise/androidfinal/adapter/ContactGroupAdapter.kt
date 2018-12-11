package com.example.msise.androidfinal.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.msise.androidfinal.R
import com.example.msise.androidfinal.model.ContactGroup

class ContactGroupAdapter ( private val groupList: List<ContactGroup>
) : RecyclerView.Adapter<ContactGroupAdapter.ContactGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactGroupViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ContactGroupViewHolder(itemView)
    }

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: ContactGroupViewHolder, position: Int) {
        val cur = groupList[position]

        holder.textViewGroup.text = cur.name
    }

    class ContactGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewGroup: TextView

        init {
            textViewGroup = itemView.findViewById(R.id.textViewGroup)
        }
    }
}