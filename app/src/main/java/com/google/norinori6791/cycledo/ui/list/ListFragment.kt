package com.google.norinori6791.cycledo.ui.list

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
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
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.ArticleDetailBinding
import com.google.norinori6791.cycledo.databinding.FragmentListBinding
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.list.adapter.TaskListAdapter
import com.google.norinori6791.cycledo.ui.list.swipe.SwipeToDeleteCallback


class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var dataBinding: FragmentListBinding
    private lateinit var showDetailDataBinding: ArticleDetailBinding
    private lateinit var detailTransform: MaterialContainerTransform
    private lateinit var listTransform: MaterialContainerTransform

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

        listViewModel.toShow.observe(this, Observer {
            TransitionManager.beginDelayedTransition(dataBinding.root as ViewGroup, detailTransform)
//            listViewModel.isShowDetail.set(true)
            dataBinding.listArticleDetailCardView.removeAllViews()
            dataBinding.listArticleDetailCardView.visibility = View.VISIBLE
            showDetailDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.article_detail, dataBinding.listArticleDetailCardView, true)
            showDetailDataBinding.item = it
            showDetailDataBinding.viewModel = listViewModel
        })

        listViewModel.toList.observe(this, Observer {
            TransitionManager.beginDelayedTransition(showDetailDataBinding.root as ViewGroup, listTransform)
            dataBinding.listArticleDetailCardView.visibility = View.GONE
        })

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_list, container, false)

        detailTransform  = MaterialContainerTransform(context!!).apply {
            startView = dataBinding.listRecyclerview
            endView = dataBinding.listArticleDetailCardView
            pathMotion = MaterialArcMotion()
            duration = 500
            scrimColor = Color.TRANSPARENT
        }

        listTransform  = MaterialContainerTransform(context!!).apply {
            startView = dataBinding.listArticleDetailCardView
            endView =  dataBinding.listRecyclerview
            pathMotion = MaterialArcMotion()
            duration = 500
            scrimColor = Color.TRANSPARENT
        }


        return dataBinding.root
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
        val taskListAdapter = TaskListAdapter(context, activity?.packageName, taskList, listViewModel)
        dataBinding.listRecyclerview.layoutManager = LinearLayoutManager(context)
        dataBinding.listRecyclerview.adapter = taskListAdapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dataBinding.listRecyclerview.addItemDecoration(decorator)

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = dataBinding.listRecyclerview.adapter as TaskListAdapter
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
        itemTouchHelper.attachToRecyclerView(dataBinding.listRecyclerview)
    }
}