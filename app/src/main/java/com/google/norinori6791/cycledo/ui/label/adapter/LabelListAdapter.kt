package com.google.norinori6791.cycledo.ui.label.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.ListLabelBinding
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.ui.label.LabelViewModel
import com.google.norinori6791.cycledo.ui.list.filter.ActiveAllTaskFilter
import com.google.norinori6791.cycledo.ui.list.filter.NowTaskFilter

class LabelListAdapter(private val context: Context, val viewModel: LabelViewModel): RecyclerView.Adapter<LabelListHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelListHolder {
        var dataBinding: ListLabelBinding = DataBindingUtil.inflate(inflater, R.layout.list_label, parent, false)
        return LabelListHolder(dataBinding)
    }

    override fun getItemCount(): Int = viewModel.allTags.size

    override fun onBindViewHolder(holder: LabelListHolder, position: Int) {
        holder.dataBinding.viewModel = viewModel
        holder.dataBinding.tag = viewModel.allTags[position]
        holder.dataBinding.nowInLabel = viewModel.getTaskByTag(viewModel.allTags[position], NowTaskFilter()).size.toString()
        holder.dataBinding.allInLabel = viewModel.getTaskByTag(viewModel.allTags[position], ActiveAllTaskFilter()).size.toString()
    }
}