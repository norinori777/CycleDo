package com.google.norinori6791.cycledo.ui.add

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.repository.TaskItem


class AddViewModel : ViewModel() {

    var title = ObservableField<String>("")
    var content: String = ""
    var undo = MutableLiveData<Boolean>()
    var redo = MutableLiveData<Boolean>()
    var bold = MutableLiveData<Boolean>()
    var italic = MutableLiveData<Boolean>()
    var subScript = MutableLiveData<Boolean>()
    var superScript = MutableLiveData<Boolean>()
    var strikethrough = MutableLiveData<Boolean>()
    var underline = MutableLiveData<Boolean>()
    var heading1 = MutableLiveData<Boolean>()
    var heading2 = MutableLiveData<Boolean>()
    var heading3 = MutableLiveData<Boolean>()
    var heading4 = MutableLiveData<Boolean>()
    var heading5 = MutableLiveData<Boolean>()
    var heading6 = MutableLiveData<Boolean>()
    var textColor = MutableLiveData<Boolean>()
    var bgColor = MutableLiveData<Boolean>()
    var indent = MutableLiveData<Boolean>()
    var outdent = MutableLiveData<Boolean>()
    var alignLeft = MutableLiveData<Boolean>()
    var alignCenter = MutableLiveData<Boolean>()
    var alignRight = MutableLiveData<Boolean>()
    var insertBullets = MutableLiveData<Boolean>()
    var insertNumbers = MutableLiveData<Boolean>()
    var blockQuote = MutableLiveData<Boolean>()
    var insertImage = MutableLiveData<Boolean>()
    var insertLink = MutableLiveData<Boolean>()
    var insertCheckBox = MutableLiveData<Boolean>()

    var showEditMenu = ObservableBoolean(false)
    var onCompleteAddTask = MutableLiveData<Boolean>()

    fun changeEditMenu(hasFocus: Boolean){
        showEditMenu.set(hasFocus)
    }
    fun addTask(){
        val taskItem = TaskItem()
        val task:Task = Task("", title.get(), content, "", "", null)
        taskItem.insertTask(task)
        onCompleteAddTask.postValue(true)
    }

    fun updateContent(text: String){
        content = text
    }
    fun clickUndo(){
        undo.postValue(true)
    }

    fun clickRedo(){
        redo.postValue(true)
    }

    fun clickBold(){
        bold.postValue(true)
    }

    fun clickItalic(){
        italic.postValue(true)
    }

    fun clickSubscript(){
        subScript.postValue(true)
    }

    fun clickSuperscript(){
        superScript.postValue(true)
    }

    fun clickStrikethrough(){
        strikethrough.postValue(true)
    }

    fun clickUnderline(){
        underline.postValue(true)
    }

    fun clickHeading1(){
        heading1.postValue(true)
    }

    fun clickHeading2(){
        heading2.postValue(true)
    }

    fun clickHeading3(){
        heading3.postValue(true)
    }

    fun clickHeading4(){
        heading4.postValue(true)
    }

    fun clickHeading5(){
        heading5.postValue(true)
    }

    fun clickHeading6(){
        heading6.postValue(true)
    }

    fun clickTextColor(){
        if(textColor.value == null){
            textColor.value ?: textColor.postValue(false)
            return
        }
        if(textColor.value!!)textColor.postValue(false)
        if(!textColor.value!!)textColor.postValue(true)
    }

    fun clickBackGroundColor(){
        if(bgColor.value == null){
            bgColor.postValue(false)
            return
        }
        if(bgColor.value!!)bgColor.postValue(false)
        if(!bgColor.value!!)bgColor.postValue(true)
    }

    fun clickIndent(){
        indent.postValue(true)
    }

    fun clickOutdent(){
        outdent.postValue(true)
    }

    fun clickAlignLeft(){
        alignLeft.postValue(true)
    }

    fun clickAlignCenter(){
        alignCenter.postValue(true)
    }

    fun clickAlignRight(){
        alignRight.postValue(true)
    }

    fun clickInsertBullets(){
        insertBullets.postValue(true)
    }

    fun clickInsertNumbers(){
        insertNumbers.postValue(true)
    }

    fun clickBlockQuote(){
        blockQuote.postValue(true)
    }

    fun clickInsertImage(){
        insertImage.postValue(true)
    }

    fun clickInsertLink(){
        insertLink.postValue(true)
    }

    fun clickInsertCheckbox(){
        insertCheckBox.postValue(true)
    }

}