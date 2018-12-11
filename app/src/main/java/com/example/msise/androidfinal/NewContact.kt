package com.example.msise.androidfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.msise.androidfinal.adapter.ContactGroupAdapter
import com.example.msise.androidfinal.model.Contact
import com.example.msise.androidfinal.model.ContactGroup
import com.example.msise.androidfinal.model.MyDatabase
import kotlinx.android.synthetic.main.activity_new_contact.*
import kotlinx.coroutines.experimental.launch

class NewContact : AppCompatActivity(), NewContactPresenter.View {

    private lateinit var rv: RecyclerView

    var database: MyDatabase? = null
    private lateinit var adapter: ContactGroupAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mDbWorkerThread: DbWorkerThread
    private val mUiHandler = Handler()
    private var list: MutableList<ContactGroup>? = ArrayList()
    private lateinit var presenter: NewContactPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)
        database = MyDatabase.getInstance(context = applicationContext)
        mDbWorkerThread = DbWorkerThread("dbbb")
        mDbWorkerThread.start()
        presenter = NewContactPresenter(this)

        val contactGroup = ContactGroup(12, "Base", "high")
        insertGroupDataInDb(contactGroup)

        fetchGroups()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun initView() {
        val name = name
        val mobile = mobile
        val home = home
        val work = work
        val photo = photo
        rv = group_rv
        list?.let {
            adapter = ContactGroupAdapter(list!!)
        }
        viewManager = LinearLayoutManager(applicationContext)
        rv.layoutManager = viewManager
        rv.adapter = adapter

        val send = send
        send.setOnClickListener {
            presenter.send(
                name.text.toString(),
                mobile.text.toString(),
                home.text.toString(),
                work.text.toString(),
                photo.text.toString(),
                "")
        }
    }

    private fun fetchGroups() {
        val task = Runnable {
            val data =
                database?.contactGroupDao()?.getGroups()
            mUiHandler.post {
                if (data == null || data?.size == 0) {

                    return@post
                } else {
                    list = data as MutableList<ContactGroup>?

                    initView()
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertGroupDataInDb(data: ContactGroup) {
        launch {
            database?.contactGroupDao()?.insertContactGroup(data)
        }
    }

    private fun insertDataInDb(data: Contact) {
        launch {
            database?.contactDao()?.insertContact(data)
        }
    }

    override fun onSend(contact: Contact) {
        insertDataInDb(contact)
        finish()
    }
}
