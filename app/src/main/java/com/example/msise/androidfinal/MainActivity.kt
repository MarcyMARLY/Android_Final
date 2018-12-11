package com.example.msise.androidfinal

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.msise.androidfinal.adapter.ContactAdapter
import com.example.msise.androidfinal.model.Contact
import com.example.msise.androidfinal.model.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var search: Toolbar
    private lateinit var rv: RecyclerView
    private lateinit var adapter: ContactAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    var database: MyDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    private val mUiHandler = Handler()
    private lateinit var presenter: MainPresenter
    private var list: MutableList<Contact>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(context = applicationContext)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        presenter = MainPresenter()

//        val contact = Contact(122, "ds", "8352332", "232332", "224324", "dfs", "sds")
//        list?.add(contact)
//        initView()
        search = activity_search_toolbar
        fetchContact()
        val fb = add
        fb.setOnClickListener {
            val intent: Intent = Intent(this, NewContact::class.java)
            startActivityForResult(intent, 1)
        }
    }

    private fun initView() {
        rv = activity_search_rv
        list?.let {
            adapter = ContactAdapter(list!!)
        }
        viewManager = LinearLayoutManager(applicationContext)
        rv.layoutManager = viewManager
        rv.adapter = adapter
    }

    private fun fetchContact() {
        val task = Runnable {
            val data =
                database?.contactDao()?.getContacts()
            mUiHandler.post {
                if (data == null || data?.size == 0) {

                    return@post
                } else {
                    //list = data as MutableList<Contact>?

                    initView()
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }
}
