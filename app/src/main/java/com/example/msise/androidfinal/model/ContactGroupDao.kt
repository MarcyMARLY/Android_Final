package com.example.msise.androidfinal.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ContactGroupDao {

    @Insert
    fun insertContactGroup(group: ContactGroup)

    @Query("SELECT * FROM ContactGroup")
    fun getGroups(): List<ContactGroup>
}