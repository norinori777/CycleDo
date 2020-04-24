package com.google.norinori6791.cycledo.ui.labeledit

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag

class LabelEditViewModel : ViewModel() {

    var labelName = ObservableField<String>("")
    var label = Tag()

    fun updateLabelName(){

    }

    fun deleteLabel(){

    }
}