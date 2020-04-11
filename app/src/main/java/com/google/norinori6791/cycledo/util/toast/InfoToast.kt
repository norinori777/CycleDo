package com.google.norinori6791.cycledo.util.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.norinori6791.cycledo.databinding.ViewToastBinding

class InfoToast(private val context: Context) {
    private val inflater = LayoutInflater.from(context)

    fun show(layoutResource: Int, drawerResource: Int, message: String){
        val dataBinding: ViewToastBinding = DataBindingUtil.inflate(inflater, layoutResource, null, false)
        dataBinding.customToastTextView.text = message
        dataBinding.customToastTextView.setBackgroundResource(drawerResource)
        val toast = Toast(context)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = dataBinding.root
        toast.show()
    }
}