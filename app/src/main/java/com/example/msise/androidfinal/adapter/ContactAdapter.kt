package com.example.msise.androidfinal.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.msise.androidfinal.R
import com.example.msise.androidfinal.model.Contact
import com.squareup.picasso.Picasso

class ContactAdapter(
    private val contactList: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_card, parent, false)

        return ContactViewHolder(itemView)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val cur = contactList[position]

        val imageUri = cur.photo

        holder.name.text = cur.name
        holder.group.text = cur.contactGroup
        Picasso.get().load(imageUri).resize(115, 120).centerCrop().into(holder.photo)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var group: TextView
        var photo: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            group = itemView.findViewById(R.id.group)
            photo = itemView.findViewById(R.id.image)
        }
    }
}