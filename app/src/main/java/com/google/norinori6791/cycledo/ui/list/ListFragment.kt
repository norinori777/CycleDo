package com.google.norinori6791.cycledo.ui.list

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.view.*
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.list.adapter.ItemView
import com.google.norinori6791.cycledo.ui.list.adapter.ListConditionAdapter
import com.google.norinori6791.cycledo.ui.list.adapter.TaskListAdapter
import com.google.norinori6791.cycledo.ui.list.dialog.ListConditionDialogFragment
import com.google.norinori6791.cycledo.ui.list.swipe.SwipeToDeleteCallback


class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var dataBinding: FragmentListBinding
    private lateinit var showDetailDataBinding: ArticleDetailBinding
    private lateinit var detailTransform: MaterialContainerTransform
    private lateinit var listTransform: MaterialContainerTransform
    private lateinit var conditionDialog: ListConditionDialogFragment
    var itemView = ItemView()
    var nowTaskList: MutableList<Task> = mutableListOf()
    var tag = Tag(null, 0, "")

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
            detailTransform  = MaterialContainerTransform(context!!).apply {
                startView = itemView?.getItemView()
                endView = dataBinding.listArticleDetailCardView
                pathMotion = MaterialArcMotion()
                duration = 500
                scrimColor = Color.TRANSPARENT
            }

            TransitionManager.beginDelayedTransition(dataBinding.root as ViewGroup, detailTransform)
            dataBinding.listArticleDetailCardView.removeAllViews()
            dataBinding.listArticleDetailCardView.visibility = View.VISIBLE
            showDetailDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.article_detail, dataBinding.listArticleDetailCardView, true)

            showDetailDataBinding.item = it
            showDetailDataBinding.articleDetailTryNum.setBackgroundResource(context?.resources?.getIdentifier(it.getTaskTermColor(), "drawable", activity?.packageName)!!)
            showDetailDataBinding.viewModel = listViewModel
        })

        listViewModel.toList.observe(this, Observer {
            listTransform  = MaterialContainerTransform(context!!).apply {
                startView = dataBinding.listArticleDetailCardView
                endView =  itemView?.getItemView()
                pathMotion = MaterialArcMotion()
                duration = 500
                scrimColor = Color.TRANSPARENT
            }

            TransitionManager.beginDelayedTransition(showDetailDataBinding.root as ViewGroup, listTransform)
            dataBinding.listArticleDetailCardView.visibility = View.GONE
        })

        arguments?.let { arguments ->
            listViewModel.tag = arguments.getSerializable("item") as Tag
        }

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_list, container, false)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        setDialog()

        listViewModel.taskItems.observe(this, Observer {
           setListView(it)
        })

        listViewModel.onChangeFilter.observe(this, Observer {
            conditionDialog.dismiss()
        })
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getTask()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mDrawer = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        when(item.itemId) {
            android.R.id.home -> mDrawer?.openDrawer(GravityCompat.START)
            R.id.action_select_display_condition -> conditionDialog.show(fragmentManager!!, "condition_dialog")
            R.id.action_settings -> listViewModel
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setListView(taskList: MutableList<Task>){
        nowTaskList = taskList
        val taskListAdapter = TaskListAdapter(context, activity?.packageName, taskList, listViewModel, itemView)
        dataBinding.listRecyclerview.layoutManager = LinearLayoutManager(context)
        dataBinding.listRecyclerview.adapter = taskListAdapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dataBinding.listRecyclerview.addItemDecoration(decorator)

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = dataBinding.listRecyclerview.adapter as TaskListAdapter
                viewHolder?.let {
                    when(direction) {
                        4 -> listViewModel.deleteTask(nowTaskList[it.adapterPosition])
                        8 -> listViewModel.completeTask(nowTaskList[it.adapterPosition])
                    }
                    adapter.removeAt(it.adapterPosition)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(dataBinding.listRecyclerview)
    }

    private fun setDialog(){
        conditionDialog = ListConditionDialogFragment(listViewModel, context!!)
    }
}