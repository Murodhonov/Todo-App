package Adapter

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.todoapp.R
import kotlinx.android.synthetic.main.expanded_list_child.view.*
import kotlinx.android.synthetic.main.item_expanded_list_group.view.*

class MyExpandedListAdapter(val titleList: List<String>, val map: HashMap<String, List<String>>) :
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = titleList.size

    override fun getChildrenCount(groupPosition: Int): Int = map[titleList[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int): Any = titleList[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        map[titleList[groupPosition]]!![childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        var itemGroup: View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_expanded_list_group, parent, false)

        itemGroup.tv.text = titleList[groupPosition]

        itemGroup.close.setOnClickListener {
            if (!isExpanded) {
                itemGroup.close.setImageResource(R.drawable.down)
            } else {
                itemGroup.close.setImageResource(R.drawable.up)
            }
        }

        return itemGroup
    }

    @SuppressLint("SetTextI18n")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val itemChild: View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.expanded_list_child, parent, false)

        itemChild.tv2.text = map[titleList[groupPosition]]!!.get(childPosition)

        itemChild.num.text = "${childPosition + 1}"

        return itemChild
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}