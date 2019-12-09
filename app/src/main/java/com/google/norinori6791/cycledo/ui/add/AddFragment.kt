package com.google.norinori6791.cycledo.ui.add

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel
    lateinit var databinding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addViewModel =
            ViewModelProviders.of(this).get(AddViewModel::class.java)

        addViewModel.undo.observe(this, Observer {
            databinding.richEditor.undo()
        })
        addViewModel.redo.observe(this, Observer {
            databinding.richEditor.redo()
        })
        addViewModel.bold.observe(this, Observer {
            databinding.richEditor.setBold()
        })
        addViewModel.italic.observe(this, Observer {
            databinding.richEditor.setItalic()
        })
        addViewModel.subScript.observe(this, Observer {
            databinding.richEditor.setSubscript()
        })
        addViewModel.superScript.observe(this, Observer {
            databinding.richEditor.setSuperscript()
        })
        addViewModel.strikethrough.observe(this, Observer {
            databinding.richEditor.setStrikeThrough()
        })
        addViewModel.underline.observe(this, Observer {
            databinding.richEditor.setUnderline()
        })
        addViewModel.heading1.observe(this, Observer {
            databinding.richEditor.setHeading(1)
        })
        addViewModel.heading2.observe(this, Observer {
            databinding.richEditor.setHeading(2)
        })
        addViewModel.heading3.observe(this, Observer {
            databinding.richEditor.setHeading(3)
        })
        addViewModel.heading4.observe(this, Observer {
            databinding.richEditor.setHeading(4)
        })
        addViewModel.heading5.observe(this, Observer {
            databinding.richEditor.setHeading(5)
        })
        addViewModel.heading6.observe(this, Observer {
            databinding.richEditor.setHeading(6)
        })
        addViewModel.textColor.observe(this, Observer {
            if(it!!)databinding.richEditor.setTextColor(Color.BLACK)
            if(!it!!)databinding.richEditor.setTextColor(Color.RED)
        })
        addViewModel.bgColor.observe(this, Observer {
            if(it!!)databinding.richEditor.setTextBackgroundColor(Color.TRANSPARENT)
            if(!it!!)databinding.richEditor.setTextBackgroundColor(Color.YELLOW)
        })
        addViewModel.indent.observe(this, Observer {
            databinding.richEditor.setIndent()
        })
        addViewModel.outdent.observe(this, Observer {
            databinding.richEditor.setOutdent()
        })
        addViewModel.alignLeft.observe(this, Observer {
            databinding.richEditor.setAlignLeft()
        })
        addViewModel.alignCenter.observe(this, Observer {
            databinding.richEditor.setAlignCenter()
        })
        addViewModel.alignRight.observe(this, Observer {
            databinding.richEditor.setAlignRight()
        })
        addViewModel.insertBullets.observe(this, Observer {
            databinding.richEditor.setBullets()
        })
        addViewModel.insertNumbers.observe(this, Observer {
            databinding.richEditor.setNumbers()
        })
        addViewModel.blockQuote.observe(this, Observer {
            databinding.richEditor.setBlockquote()
        })
        addViewModel.insertImage.observe(this, Observer {
            databinding.richEditor.insertImage("http://www.test.co.jp", "aaa")
        })
        addViewModel.insertLink.observe(this, Observer {
            databinding.richEditor.insertLink("http://www.test.co.jp", "aaa")
        })
        addViewModel.insertCheckBox.observe(this, Observer {
            databinding.richEditor.insertTodo()
        })

        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        databinding.richEditor.setPlaceholder(getString(R.string.add_edit_text_placeholder))

        databinding.viewModel = addViewModel

        return databinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        databinding.unbind()
    }
}
