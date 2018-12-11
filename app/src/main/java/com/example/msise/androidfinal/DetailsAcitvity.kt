package com.example.msise.androidfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.msise.androidfinal.model.Contact

class DetailsAcitvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_acitvity)
        val data = intent.getParcelableExtra<Contact>("contact")
        val name = data.name
        val mobile = data.mobilePhone
        val home = data.homePhone
        val work = data.workPhone
        val photo = data.photo
        val group = data.contactGroup

        findViewById<TextView>(R.id.name).text = name
        findViewById<TextView>(R.id.mPhone).text = mobile
    }
}
