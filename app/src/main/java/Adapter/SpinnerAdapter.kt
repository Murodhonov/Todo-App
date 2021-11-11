package Adapter

import Models.User
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.green
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import kotlinx.android.synthetic.main.item_spinner.view.*

class SpinnerAdapter(private val list:List<User>):BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): User{
        return list[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("NewApi")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val itemView:View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_spinner,parent,false)

        itemView.txt.text = list[position].title

        if (itemView.txt.text.toString() == list[0].title){
            itemView.image.visibility = View.GONE
        }else{
            itemView.image.setImageResource(list[position].image)
        }

        return itemView

    }
}