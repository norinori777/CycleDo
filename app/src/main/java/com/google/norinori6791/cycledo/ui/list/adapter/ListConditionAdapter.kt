package com.google.norinori6791.cycledo.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.ConditionItemBinding
import com.google.norinori6791.cycledo.model.enum.ListCondition
import com.google.norinori6791.cycledo.ui.list.ListViewModel

class ListConditionAdapter(private val context: Context, val viewModel: ListViewModel):
    RecyclerView.Adapter<ListConditionHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListConditionHolder {
        var dataBinding: ConditionItemBinding = DataBindingUtil.inflate(inflater, R.layout.condition_item, parent, false)
        dataBinding.conditionItemLayout.setOnClickListener(ItemClickListener(dataBinding))
        return ListConditionHolder(dataBinding)
    }

    override fun getItemCount(): Int = ListCondition.values().size

    override fun onBindViewHolder(holder: ListConditionHolder, position: Int) {
        holder.dataBinding.display = ListCondition.values()[position].display
    }

    private inner class ItemClickListener(val dataBinding: ConditionItemBinding): View.OnClickListener {
        override fun onClick(view: View){
            dataBinding.display?.let {
                viewModel.changeListCondition(it)
            }
        }
    }

}