package com.google.norinori6791.cycledo.ui.edit

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentEditBinding
import com.google.norinori6791.cycledo.model.data.Task

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    private lateinit var dataBinding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editViewModel =
            ViewModelProviders.of(this).get(EditViewModel::class.java)

        richEditorMenuObserve()
        taskCrudObserve()

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)

        var task = arguments?.getSerializable("item") as Task?
        task?.let {
            editViewModel.setInitialize(it)
            dataBinding.richEditor.html = it.content
        }

        dataBinding.richEditor.setPlaceholder(getString(R.string.add_edit_text_placeholder))

        dataBinding.viewModel = editViewModel

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
        inflater?.inflate(R.menu.fragment_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add -> editViewModel.updateTask()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    fun taskCrudObserve(){
        editViewModel.onCompleteAddTask.observe(this, Observer {
            Toast.makeText(activity, "登録しました。", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_nav_edit_to_nav_list)
        })
    }

    fun richEditorMenuObserve(){
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
}
