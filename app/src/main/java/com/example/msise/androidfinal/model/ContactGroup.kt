package com.example.msise.androidfinal.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
data class ContactGroup(
    @ColumnInfo(name = "id") var id: Long,
    @PrimaryKey var name: String,
    @ColumnInfo(name = "priority") var priority: String
) {
}