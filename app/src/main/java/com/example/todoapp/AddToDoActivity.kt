package com.example.todoapp

import Models.User
import Models.FromTask4
import android.os.Bundle
import android.widget.Toast
import Adapter.SpinnerAdapter
import Cache.MySharedPreference.init
import Cache.MySharedPreference.myObject
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_to_do.*

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        init(this)

        todo_degree.adapter = SpinnerAdapter(load())

        btn_save.setOnClickListener {

            if (todo_degree.selectedItemPosition != 0 &&
                todo_name.text.toString().trim() != "" &&
                todo_dedline.text.toString().trim() != "" &&
                todo_creation.text.toString().trim() != "" &&
                todo_creation.text.toString().trim() != "") {

                val todoName = todo_name.text.toString()
                val todoDedline = todo_dedline.text.toString()
                val todoCreation = todo_creation.text.toString()
                val todoDegree = todo_degree.selectedItemPosition
                val todoDescription = todo_description.text.toString()

                val list = myObject
                var isHave = false

                for (i in list.indices) {
                    if (list[i].todoName == todoName &&
                        list[i].todoFolders == "open" &&
                        list[i].todoDegree == todoDegree &&
                        list[i].todoDedline == todoDedline &&
                        list[i].todoCreation == todoCreation &&
                        list[i].todoDescription == todoDescription) {
                        isHave = true
                        break
                    }
                }

                if (!isHave) {
                    list.add(FromTask4(todoName, todoDescription, todoDegree, todoCreation, todoDedline, "open"))
                    myToast("Saved",false)
                    myObject = list
                    finish()
                }
            } else {
                myToast("Fields are empty",false)
            }
        }
    }

    private fun load(): ArrayList<User> {

        val array = ArrayList<User>()
        array.add(User("Degree", 0))
        array.add(User("Low", R.drawable.gray))
        array.add(User("Urgent", R.drawable.red))
        array.add(User("Normal", R.drawable.blue))
        array.add(User("High", R.drawable.yellow))
        return array
    }

    private fun myToast(text:String,isLong:Boolean) = if (isLong) Toast.makeText(this, text, Toast.LENGTH_LONG).show() else Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}