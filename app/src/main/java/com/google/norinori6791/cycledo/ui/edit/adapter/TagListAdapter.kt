package com.google.norinori6791.cycledo.ui.edit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.DialogSelectTagItemBinding
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.ui.edit.EditViewModel

class TagListAdapter(private val context: Context, val viewModel: EditViewModel): RecyclerView.Adapter<TagViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        var dataBinding: DialogSelectTagItemBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_select_tag_item, parent, false)
        return TagViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = viewModel.allTags.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.dataBinding.viewModel = viewModel
        holder.dataBinding.tag = viewModel.allTags[position]
        holder.dataBinding.check = isCheckedTag(viewModel.allTags[position], viewModel.selectedTag)
    }

    private fun isCheckedTag(tag: Tag, tagList: MutableList<Tag>):Boolean{
        tagList?.forEach {
            if (tag == it) return true
        }
        return false
    }
}