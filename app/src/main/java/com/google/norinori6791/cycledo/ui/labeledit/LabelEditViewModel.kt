package com.google.norinori6791.cycledo.ui.labeledit

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.repository.TagItem
import com.google.norinori6791.cycledo.model.repository.TagItems

class LabelEditViewModel : ViewModel() {

    private val repository = TagItem()
    var labelName = ObservableField<String>("")
    var label = Tag()
    var onUpdate = MutableLiveData<Boolean>()
    var onDelete = MutableLiveData<Boolean>()

    fun updateLabelName(){
        label.name = labelName.get()!!
        repository.updateTag(label)
        onUpdate.postValue(true)
    }

    fun deleteLabel(){
        repository.deleteTag(label)
        onDelete.postValue(true)
    }
}