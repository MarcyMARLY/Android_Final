package com.example.msise.androidfinal.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ContactDao {
    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getContacts(): List<Contact>
}