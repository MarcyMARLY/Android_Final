package com.example.msise.androidfinal.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Contact::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.NO_ACTION
        )
    )
)
data class ContactGroup(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "priority") var priority: String
) {
}