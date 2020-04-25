package com.google.norinori6791.cycledo.ui.edit

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentEditBinding
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.ui.edit.dialog.SelectTagDialogFragment
import com.google.norinori6791.cycledo.util.toast.InfoToast

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    private lateinit var dataBinding: FragmentEditBinding
    private lateinit var task: Task
    private lateinit var selectTagDialog: SelectTagDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Toolbar設定
        activity?.let { fragmentActivity ->
            var appCompatActivity = fragmentActivity as AppCompatActivity
            val toolbar = fragmentActivity.findViewById<Toolbar>(R.id.toolbar)
            appCompatActivity.setSupportActionBar(toolbar)
            appCompatActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            appCompatActivity.supportActionBar?.setHomeButtonEnabled(true)

            val fab = fragmentActivity.findViewById<FloatingActionButton>(R.id.fab)
            fab.visibility = View.GONE
        }

        editViewModel =
            ViewModelProviders.of(this).get(EditViewModel::class.java)
        richEditorMenuObserve()
        taskCrudObserve()

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)

        arguments?.let {arguments ->
            task = arguments.getSerializable("item") as Task
            task?.let {
                editViewModel.setInitialize(it)
                dataBinding.richEditor.html = it.content
            }
        }

        dataBinding.richEditor.setPlaceholder(getString(R.string.add_edit_text_placeholder))
        dataBinding.viewModel = editViewModel

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        editViewModel.onSelectTag.observe(this, Observer{
            when(it){
                true -> selectTagDialog.show(fragmentManager!!, "select_tag_dialog")
                false -> selectTagDialog.dismiss()
            }
        })
        setDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding.unbind()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> findNavController().navigate(R.id.action_nav_edit_to_nav_list)
            R.id.action_add -> editViewModel.updateTask()
            R.id.action_cycle_reset -> editViewModel.resetCycle(task)
            R.id.action_cycle_reset_all -> editViewModel.resetCycleAll(task)
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun taskCrudObserve(){
        editViewModel.onCompleteAddTask.observe(this, Observer {
            InfoToast(context!!).show(R.layout.view_toast, R.drawable.custom_toast_info, getString(R.string.edit_add_complete))
            findNavController().navigate(R.id.action_nav_edit_to_nav_list)
        })

        editViewModel.onResetCycleAll.observe(this, Observer {
            InfoToast(context!!).show(R.layout.view_toast, R.drawable.custom_toast_info, getString(R.string.edit_reset_cycle_all))
            findNavController().navigate(R.id.action_nav_edit_to_nav_list)
        })

        editViewModel.onResetCycle.observe(this, Observer {
            InfoToast(context!!).show(R.layout.view_toast, R.drawable.custom_toast_info, getString(R.string.edit_reset_cycle))
            findNavController().navigate(R.id.action_nav_edit_to_nav_list)
        })
    }

    private fun richEditorMenuObserve(){
        editViewModel.undo.observe(this, Observer {
            dataBinding.richEditor.undo()
        })
        editViewModel.redo.observe(this, Observer {
            dataBinding.richEditor.redo()
        })
        editViewModel.bold.observe(this, Observer {
            dataBinding.richEditor.setBold()
        })
        editViewModel.italic.observe(this, Observer {
            dataBinding.richEditor.setItalic()
        })
        editViewModel.subScript.observe(this, Observer {
            dataBinding.richEditor.setSubscript()
        })
        editViewModel.superScript.observe(this, Observer {
            dataBinding.richEditor.setSuperscript()
        })
        editViewModel.strikethrough.observe(this, Observer {
            dataBinding.richEditor.setStrikeThrough()
        })
        editViewModel.underline.observe(this, Observer {
            dataBinding.richEditor.setUnderline()
        })
        editViewModel.heading1.observe(this, Observer {
            dataBinding.richEditor.setHeading(1)
        })
        editViewModel.heading2.observe(this, Observer {
            dataBinding.richEditor.setHeading(2)
        })
        editViewModel.heading3.observe(this, Observer {
            dataBinding.richEditor.setHeading(3)
        })
        editViewModel.heading4.observe(this, Observer {
            dataBinding.richEditor.setHeading(4)
        })
        editViewModel.heading5.observe(this, Observer {
            dataBinding.richEditor.setHeading(5)
        })
        editViewModel.heading6.observe(this, Observer {
            dataBinding.richEditor.setHeading(6)
        })
        editViewModel.textColor.observe(this, Observer {
            if(it!!)dataBinding.richEditor.setTextColor(Color.BLACK)
            if(!it)dataBinding.richEditor.setTextColor(Color.RED)
        })
        editViewModel.bgColor.observe(this, Observer {
            if(it!!)dataBinding.richEditor.setTextBackgroundColor(Color.TRANSPARENT)
            if(!it)dataBinding.richEditor.setTextBackgroundColor(Color.YELLOW)
        })
        editViewModel.indent.observe(this, Observer {
            dataBinding.richEditor.setIndent()
        })
        editViewModel.outdent.observe(this, Observer {
            dataBinding.richEditor.setOutdent()
        })
        editViewModel.alignLeft.observe(this, Observer {
            dataBinding.richEditor.setAlignLeft()
        })
        editViewModel.alignCenter.observe(this, Observer {
            dataBinding.richEditor.setAlignCenter()
        })
        editViewModel.alignRight.observe(this, Observer {
            dataBinding.richEditor.setAlignRight()
        })
        editViewModel.insertBullets.observe(this, Observer {
            dataBinding.richEditor.setBullets()
        })
        editViewModel.insertNumbers.observe(this, Observer {
            dataBinding.richEditor.setNumbers()
        })
        editViewModel.blockQuote.observe(this, Observer {
            dataBinding.richEditor.setBlockquote()
        })
        editViewModel.insertImage.observe(this, Observer {
            dataBinding.richEditor.insertImage("http://www.test.co.jp", "aaa")
        })
        editViewModel.insertLink.observe(this, Observer {
            dataBinding.richEditor.insertLink("http://www.test.co.jp", "aaa")
        })
        editViewModel.insertCheckBox.observe(this, Observer {
            dataBinding.richEditor.insertTodo()
        })
    }

    private fun setDialog() {
        selectTagDialog = SelectTagDialogFragment(context!!, editViewModel)
        selectTagDialog.setStyle(R.style.TagDialogStyle, DialogFragment.STYLE_NO_TITLE)
    }
}
