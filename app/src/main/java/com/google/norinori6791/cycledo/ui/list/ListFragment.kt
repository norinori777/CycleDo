package com.google.norinori6791.cycledo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentListBinding
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.list.adapter.TaskListAdapter


class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var databinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProviders.of(this).get(ListViewModel::class.java)

        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)

        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listViewModel.taskItems.observe(this, Observer {
           setListView(it)
        })
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getAllTask()
    }

    private fun setListView(taskList: MutableList<Task>){
        val taskListAdapter = TaskListAdapter(context, taskList)
        databinding.listRecyclerview.layoutManager = LinearLayoutManager(context)
        databinding.listRecyclerview.adapter = taskListAdapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        databinding.listRecyclerview.addItemDecoration(decorator)
    }
}