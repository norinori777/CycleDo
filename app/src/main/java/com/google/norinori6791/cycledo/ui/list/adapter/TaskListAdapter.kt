package com.google.norinori6791.cycledo.ui.list.adapter

import android.content.Context
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.ListTaskBinding
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.list.ListViewModel

class TaskListAdapter(private val context: Context?, private val packageName: String?, private val items: MutableList<Task>, val viewModel: ListViewModel) : RecyclerView.Adapter<TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    val resource = context?.resources

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val databinding: ListTaskBinding = DataBindingUtil.inflate(inflater, R.layout.list_task, parent, false)
        databinding.listItemLayout.setOnClickListener(ItemClickListener(databinding))
        return TaskViewHolder(databinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        var resId = resource?.getIdentifier(items[position].getTaskTermColor(), "drawable", packageName)

        resId?.let {
            holder.databinding.listTryNum.setBackgroundResource(it)
        }

        items[position].content?.let {
            items[position].html = convertHtml(items[position].content!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
        holder.databinding.item = items[position]
    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private inner class ItemClickListener(val databinding: ListTaskBinding): View.OnClickListener {
        override fun onClick(view: View) {
            viewModel.toEdit.postValue(databinding.item)
        }
    }
    private fun convertHtml(html: String, fromHtmlModeCompact: Int): Spanned {
        return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}