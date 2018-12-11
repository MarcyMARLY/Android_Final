package com.example.msise.androidfinal.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = arrayOf(
    Contact::class, ContactGroup::class),
    version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
    abstract fun contactGroupDao(): ContactGroupDao


    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MyDatabase::class.java, "database.db")
                        .build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}