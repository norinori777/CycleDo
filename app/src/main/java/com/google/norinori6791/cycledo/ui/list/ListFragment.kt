package com.google.norinori6791.cycledo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentListBinding
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.list.adapter.TaskListAdapter
import com.google.norinori6791.cycledo.ui.list.swipe.SwipeToDeleteCallback


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

        listViewModel.toEdit.observe(this, Observer {
            val bundle = Bundle()
            bundle.putSerializable("item", it)
            findNavController().navigate(R.id.action_nav_list_to_nav_edit, bundle)
        })

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
        val taskListAdapter = TaskListAdapter(context, taskList, listViewModel)
        databinding.listRecyclerview.layoutManager = LinearLayoutManager(context)
        databinding.listRecyclerview.adapter = taskListAdapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        databinding.listRecyclerview.addItemDecoration(decorator)

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = databinding.listRecyclerview.adapter as TaskListAdapter
                viewHolder?.let {

                    when(direction) {
                        4 -> listViewModel.deleteTask(taskList[it.adapterPosition])
                        8 -> listViewModel.completeTask(taskList[it.adapterPosition])
                    }
                    adapter.removeAt(it.adapterPosition)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(databinding.listRecyclerview)
    }
}