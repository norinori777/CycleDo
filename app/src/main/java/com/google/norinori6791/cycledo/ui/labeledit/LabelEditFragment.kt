package com.google.norinori6791.cycledo.ui.labeledit

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentLabelEditBinding
import com.google.norinori6791.cycledo.model.data.Tag

class LabelEditFragment : Fragment() {

    private lateinit var labelEditViewModel: LabelEditViewModel
    private lateinit var dataBinding: FragmentLabelEditBinding
    private lateinit var tag: Tag

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Toolbar setting
        activity?.let {fragmentActivity ->
            var appCompatActivity = fragmentActivity as AppCompatActivity
            val toolbar = fragmentActivity.findViewById<Toolbar>(R.id.toolbar)
            appCompatActivity.setSupportActionBar(toolbar)
            appCompatActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true);
            appCompatActivity.supportActionBar?.setHomeButtonEnabled(true);
        }
        labelEditViewModel =
            ViewModelProviders.of(this).get(LabelEditViewModel::class.java)

        arguments?.let {arguments ->
            tag = arguments.getSerializable("item") as Tag
            tag?.let {
                labelEditViewModel.labelName.set(it.name)
                labelEditViewModel.label = it
            }
        }
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_label_edit, container, false)
        dataBinding.viewModel = labelEditViewModel

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding.unbind()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_label_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> findNavController().navigate(R.id.action_nav_label_edit_to_nav_label)
            R.id.action_edit -> labelEditViewModel.updateLabelName()
            R.id.action_delete -> labelEditViewModel.deleteLabel()
        }
        return true
    }
}