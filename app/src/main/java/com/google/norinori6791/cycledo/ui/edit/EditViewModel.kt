package com.google.norinori6791.cycledo.ui.edit

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.model.repository.TaskItem
import java.text.SimpleDateFormat
import java.util.*


class EditViewModel : ViewModel() {

    var task: Task? = null
    var title = ObservableField<String>("")
    var content: String? = ""
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
    var onResetCycleAll = MutableLiveData<Boolean>()
    var onResetCycle = MutableLiveData<Boolean>()

    fun resetCycle(task: Task){
        var term = 0
        var preTerm = 0
        for(cycleTerm in CycleTerm.values()){
            if(cycleTerm.term < task.status){
                preTerm = cycleTerm.term
                term = task.status - cycleTerm.term
            }
            if(term > CycleTerm.TWENTY.term){
                term = CycleTerm.TWENTY.term
            }
        }

        val sdFormat = SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss")
        var taskDate = Calendar.getInstance()
        taskDate.time = sdFormat.parse(task.startDate)
        taskDate.add(Calendar.DATE, term)

        task.status = preTerm
        task.startDate = sdFormat.format(taskDate.time)

        val taskItem = TaskItem()
        taskItem.updateResetCycle(task)
        onResetCycle.postValue(true)
    }

    fun resetCycleAll(task: Task){
        val taskItem = TaskItem()
        taskItem.updateResetCycleAll(task)
        onResetCycleAll.postValue(true)
    }

    fun changeEditMenu(hasFocus: Boolean){
        showEditMenu.set(hasFocus)
    }
    fun updateTask(){
        val taskItem = TaskItem()
        if(task == null) {
            val insertTask = Task("", 0, title.get(), content, null, 0, null, null, null)
            taskItem.insertTask(insertTask)
        } else {
            val updateTask = task
            updateTask?.title = title.get()
            updateTask?.content = content
            taskItem.updateTask(updateTask)
        }
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

    fun setInitialize(initialTask: Task){
        task = initialTask
        title.set(initialTask.title)
        content = initialTask.content
    }

}