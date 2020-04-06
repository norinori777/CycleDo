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

class TaskListAdapter(private val context: Context?, private val packageName: String?, private val items: MutableList<Task>, val viewModel: ListViewModel, var itemView: ItemView?) : RecyclerView.Adapter<TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val resource = context?.resources

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val dataBinding: ListTaskBinding = DataBindingUtil.inflate(inflater, R.layout.list_task, parent, false)
        dataBinding.listItemLayout.setOnLongClickListener(ItemLongClickListener(dataBinding))
        dataBinding.listItemLayout.setOnClickListener(ItemClickListener(dataBinding))
        return TaskViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        var resId = resource?.getIdentifier(items[position].getTaskTermColor(), "drawable", packageName)

        resId?.let {
            holder.dataBinding.listTryNum.setBackgroundResource(it)
        }

        items[position].content?.let {
            items[position].html = convertHtml(items[position].content!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
        holder.dataBinding.item = items[position]
    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private inner class ItemLongClickListener(val dataBinding: ListTaskBinding): View.OnLongClickListener {
        override fun onLongClick(view: View): Boolean {
            viewModel.toEdit.postValue(dataBinding.item)
            return true
        }
    }
    private inner class ItemClickListener(val dataBinding: ListTaskBinding): View.OnClickListener {
        override fun onClick(view: View){
            itemView?.setItemView(view)
            viewModel.toShow.postValue(dataBinding.item)
        }
    }
    private fun convertHtml(html: String, fromHtmlModeCompact: Int): Spanned {
        return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}