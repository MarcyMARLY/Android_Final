package com.example.msise.androidfinal.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ContactGroup::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("contactGroup"),
            onDelete = ForeignKey.NO_ACTION
        )
    )
)
data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "mPhone")var mobilePhone: String,
    @ColumnInfo(name = "hPhone")var homePhone: String,
    @ColumnInfo(name = "wPhone")var workPhone: String,
    @ColumnInfo(name = "photo")var photo: String,
    @ColumnInfo(name = "contactGroup")var contactGroup: String
)