package com.google.norinori6791.cycledo.ui.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentLabelBinding
import com.google.norinori6791.cycledo.ui.label.adapter.LabelListAdapter

class LabelFragment : Fragment() {

    private lateinit var labelViewModel: LabelViewModel
    private lateinit var dataBinding: FragmentLabelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        labelViewModel =
            ViewModelProviders.of(this).get(LabelViewModel::class.java)
        labelViewModel.onLongClickLabel.observe(this, Observer {
            val bundle = Bundle()
            bundle.putSerializable("item", it)
            findNavController().navigate(R.id.action_nav_label_to_nav_label_edit, bundle)
        })
        labelViewModel.onClickLabel.observe(this, Observer {
            var bundle = Bundle()
            bundle.putSerializable("item", it)
            findNavController().navigate(R.id.action_nav_label_to_nav_list, bundle)
        })
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_label, container, false)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListView()
    }

    private fun setListView(){
        val labelListAdapter = LabelListAdapter(context!!, labelViewModel)
        dataBinding.listLabelRecyclerview.layoutManager = LinearLayoutManager(context)
        dataBinding.listLabelRecyclerview.adapter = labelListAdapter
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dataBinding.listLabelRecyclerview.addItemDecoration(decorator)
    }
}