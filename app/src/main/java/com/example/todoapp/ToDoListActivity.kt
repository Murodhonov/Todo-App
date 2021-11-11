package com.example.todoapp

import Adapter.MyExpandedListAdapter
import Cache.MySharedPreference
import Cache.MySharedPreference.init
import Cache.MySharedPreference.myObject
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_to_do_list.*

class ToDoListActivity : AppCompatActivity() {

    lateinit var map: HashMap<String, List<String>>
    lateinit var array : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        init(this)

        map = HashMap()
        array = ArrayList()

        load()
 
        list_expanded.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->

            startActivity(Intent(this,ToDoViewActivity::class.java).putExtra("value",map[array[groupPosition]]!![childPosition]))

            true
        }

    }

    private fun load() {
        array = ArrayList()
        array.add("Open")
        array.add("Development")
        array.add("Uploading")
        array.add("Reject")
        array.add("Closed")

        val open = ArrayList<String>()
        val development = ArrayList<String>()
        val uploading = ArrayList<String>()
        val reject = ArrayList<String>()
        val closed = ArrayList<String>()

        for (i in myObject) {
            when (i.todoFolders) {
                "open" -> open.add(i.todoName)
                "closed" -> closed.add(i.todoName)
                "reject" -> reject.add(i.todoName)
                "uploading" -> uploading.add(i.todoName)
                "development" -> development.add(i.todoName)
            }
        }

        map[array[0]] = open
        map[array[1]] = development
        map[array[2]] = uploading
        map[array[3]] = reject
        map[array[4]] = closed

    }

    override fun onStart() {
        super.onStart()
        init(this)

        load()

        list_expanded.setAdapter(MyExpandedListAdapter(array, map))
    }
}