package com.example.msise.androidfinal

import com.example.msise.androidfinal.model.Contact
import java.util.*


class NewContactPresenter (view: View){

    private var curView: View = view
    fun send(
        name:String?,
        mobile: String?,
        home: String?,
        work: String?,
        photo: String?,
        group: String?){

        val id: Long = Random().nextLong()
        val contact = Contact(id,
            name ?: "",
            mobile ?: "",
            home ?: "",
            work ?: "",
            photo ?: "",
            group ?: "Base"
            )
        curView.onSend(contact)
    }

    interface View{
        fun onSend(contact: Contact)
    }
}