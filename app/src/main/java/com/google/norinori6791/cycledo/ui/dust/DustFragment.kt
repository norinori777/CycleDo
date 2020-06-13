package com.google.norinori6791.cycledo.ui.dust

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.norinori6791.cycledo.R

class DustFragment : Fragment() {

    private lateinit var dustViewModel: DustViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dustViewModel =
            ViewModelProviders.of(this).get(DustViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        dustViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}