package com.google.norinori6791.cycledo.ui.list.adapter

import android.view.View

class ItemView {
    lateinit var item: View

    fun setItemView(item: View){
        this.item = item
    }
    fun getItemView(): View{
        return item
    }
}