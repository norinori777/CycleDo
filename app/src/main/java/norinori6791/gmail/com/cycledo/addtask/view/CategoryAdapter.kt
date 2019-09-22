package norinori6791.gmail.com.cycledo.addtask.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import norinori6791.gmail.com.cycledo.model.CategoryItem

class CategoryAdapter : ArrayAdapter<CategoryItem>{
    constructor(context: Context, layout: Int, items: List<CategoryItem>): super(context, layout, items)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView : TextView = super.getView(position, convertView, parent) as TextView
        textView.setText(getItem(position)?.name)
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView: TextView = super.getDropDownView(position, convertView, parent) as TextView
        textView.setText(getItem(position)?.name)
        return textView
    }
}