package com.google.norinori6791.cycledo.ui.edit

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    lateinit var databinding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editViewModel =
            ViewModelProviders.of(this).get(EditViewModel::class.java)

        richEditorMenuObserve()
        taskCrudObserve()

        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        databinding.richEditor.setPlaceholder(getString(R.string.add_edit_text_placeholder))

        databinding.viewModel = editViewModel

        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        databinding.unbind()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add -> editViewModel.addTask()
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
            databinding.richEditor.undo()
        })
        editViewModel.redo.observe(this, Observer {
            databinding.richEditor.redo()
        })
        editViewModel.bold.observe(this, Observer {
            databinding.richEditor.setBold()
        })
        editViewModel.italic.observe(this, Observer {
            databinding.richEditor.setItalic()
        })
        editViewModel.subScript.observe(this, Observer {
            databinding.richEditor.setSubscript()
        })
        editViewModel.superScript.observe(this, Observer {
            databinding.richEditor.setSuperscript()
        })
        editViewModel.strikethrough.observe(this, Observer {
            databinding.richEditor.setStrikeThrough()
        })
        editViewModel.underline.observe(this, Observer {
            databinding.richEditor.setUnderline()
        })
        editViewModel.heading1.observe(this, Observer {
            databinding.richEditor.setHeading(1)
        })
        editViewModel.heading2.observe(this, Observer {
            databinding.richEditor.setHeading(2)
        })
        editViewModel.heading3.observe(this, Observer {
            databinding.richEditor.setHeading(3)
        })
        editViewModel.heading4.observe(this, Observer {
            databinding.richEditor.setHeading(4)
        })
        editViewModel.heading5.observe(this, Observer {
            databinding.richEditor.setHeading(5)
        })
        editViewModel.heading6.observe(this, Observer {
            databinding.richEditor.setHeading(6)
        })
        editViewModel.textColor.observe(this, Observer {
            if(it!!)databinding.richEditor.setTextColor(Color.BLACK)
            if(!it)databinding.richEditor.setTextColor(Color.RED)
        })
        editViewModel.bgColor.observe(this, Observer {
            if(it!!)databinding.richEditor.setTextBackgroundColor(Color.TRANSPARENT)
            if(!it)databinding.richEditor.setTextBackgroundColor(Color.YELLOW)
        })
        editViewModel.indent.observe(this, Observer {
            databinding.richEditor.setIndent()
        })
        editViewModel.outdent.observe(this, Observer {
            databinding.richEditor.setOutdent()
        })
        editViewModel.alignLeft.observe(this, Observer {
            databinding.richEditor.setAlignLeft()
        })
        editViewModel.alignCenter.observe(this, Observer {
            databinding.richEditor.setAlignCenter()
        })
        editViewModel.alignRight.observe(this, Observer {
            databinding.richEditor.setAlignRight()
        })
        editViewModel.insertBullets.observe(this, Observer {
            databinding.richEditor.setBullets()
        })
        editViewModel.insertNumbers.observe(this, Observer {
            databinding.richEditor.setNumbers()
        })
        editViewModel.blockQuote.observe(this, Observer {
            databinding.richEditor.setBlockquote()
        })
        editViewModel.insertImage.observe(this, Observer {
            databinding.richEditor.insertImage("http://www.test.co.jp", "aaa")
        })
        editViewModel.insertLink.observe(this, Observer {
            databinding.richEditor.insertLink("http://www.test.co.jp", "aaa")
        })
        editViewModel.insertCheckBox.observe(this, Observer {
            databinding.richEditor.insertTodo()
        })
    }
}
