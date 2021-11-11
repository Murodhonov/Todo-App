package com.example.todoapp

import Cache.MySharedPreference
import Cache.MySharedPreference.myObject
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_to_do_view.*
import kotlinx.android.synthetic.main.activity_to_do_view.view.*

class ToDoViewActivity : AppCompatActivity() {

    var index = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_view)

        MySharedPreference.init(this)
        val list = myObject

        for (i in list.indices){
            if (list[i].todoName == intent.getStringExtra("value")){
                index = i
                break
            }
        }

        todoName.text = list[index].todoName
        todoDescription.text= list[index].todoDescription
        todo_creationDate.text = list[index].todoCreation
        todo_dedlineTime.text = list[index].todoDedline

        var image = 0
        var text = ""

        when(list[index].todoDegree){
            0 -> { image = R.drawable.red;     text = "Urgent"  }
            1 -> { image = R.drawable.yellow;  text = "High"    }
            2 -> { image = R.drawable.blue;    text = "Normal"  }
            3 -> { image = R.drawable.gray;    text = "Low"     }
        }

        when(list[index].todoFolders){
            "open"-> open.isChecked = true
            "development"-> development.isChecked = true
            "uploading"-> uploading.isChecked = true
            "reject"-> reject.isChecked = true
            "closed"-> closed.isChecked = true
        }

        flag_item.setImageResource(image)
        flag_txt.text = text

        btn_ok.setOnClickListener {
            if (open.isChecked){
                list[index].todoFolders = "open"
            }else{
                if (development.isChecked){
                    list[index].todoFolders = "development"
                }else{
                    if (uploading.isChecked){
                        list[index].todoFolders = "uploading"
                    }else{
                        if (reject.isChecked){
                            list[index].todoFolders = "reject"
                        }else{
                            if (closed.isChecked){
                                list[index].todoFolders = "closed"
                            }
                        }
                    }
                }
            }
            myObject = list
            finish()
        }
    }
}