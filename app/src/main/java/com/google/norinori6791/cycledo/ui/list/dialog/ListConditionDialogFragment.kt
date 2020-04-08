package com.google.norinori6791.cycledo.ui.list.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.DialogSelectConditionBinding
import com.google.norinori6791.cycledo.ui.list.ListViewModel
import com.google.norinori6791.cycledo.ui.list.adapter.ListConditionAdapter

class ListConditionDialogFragment(val viewModel: ListViewModel, private val applicationContext: Context): DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: DialogSelectConditionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_select_condition,
            container,
false
        )
        var adapter = ListConditionAdapter(applicationContext, viewModel)
        dataBinding.listConditionRecyclerview.adapter = adapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dataBinding.listConditionRecyclerview.addItemDecoration(decorator)
        dataBinding.listConditionRecyclerview.layoutManager = LinearLayoutManager(applicationContext)

        return dataBinding.root
    }
    override fun onDestroyView() {
        view?.let {
            var v = it as ViewGroup
            v.removeAllViews()
        }
        super.onDestroyView()
    }
}