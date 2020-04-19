package com.google.norinori6791.cycledo.ui.edit.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.DialogSelectTagBinding
import com.google.norinori6791.cycledo.ui.edit.EditViewModel
import com.google.norinori6791.cycledo.ui.edit.adapter.TagListAdapter

class SelectTagDialogFragment(private val applicationContext: Context, val viewModel: EditViewModel): DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: DialogSelectTagBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_select_tag,
            container,
            false
        )
        var adapter = TagListAdapter(applicationContext, viewModel)
        dataBinding.selectTagRecyclerView.adapter = adapter
        dataBinding.selectTagRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        dataBinding.viewModel = viewModel
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