package com.google.norinori6791.cycledo.ui.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.norinori6791.cycledo.R

class LabelFragment : Fragment() {

    private lateinit var labelViewModel: LabelViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        labelViewModel =
            ViewModelProviders.of(this).get(LabelViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_label, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        labelViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}