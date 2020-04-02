package com.google.norinori6791.cycledo.ui.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.model.repository.TaskItem
import com.google.norinori6791.cycledo.model.repository.TaskItems
import io.realm.RealmResults

class ListViewModel : ViewModel() {

    private val repository = TaskItems()
    private val repositoryTask = TaskItem()
    var taskItems = MutableLiveData<MutableList<Task>>()
    var onCompleteDelete = MutableLiveData<Boolean>()
    var onCompleteUpdate = MutableLiveData<Boolean>()
    var isShowDetail = ObservableBoolean(false)
    var toEdit= MutableLiveData<Task>()
    var toShow= MutableLiveData<Task>()
    var toList = MutableLiveData<Boolean>()

    fun getAllTask() = taskItems.postValue(realmResultToTaskList(repository.getAllTasks()))

    private fun realmResultToTaskList(realmResults: RealmResults<RealmTask>): MutableList<Task>{
        var tasks:MutableList<Task> = mutableListOf()
        realmResults.forEach{
            var tags: MutableList<Tag> = mutableListOf()
            it.tags?.forEach{ tag ->
                tags.add(Tag(tag.name))
            }
            var task = Task(it.uniqueId, it.deleted, it.title, it.content, null, it.status, it.startDate, it.addDate, it.modifyDate, tags)
            tasks.add(task)
        }
        return tasks
    }

    fun deleteTask(task:Task){
        repositoryTask.logicalDeleteTask(task)
        onCompleteDelete.postValue(true)
    }

    fun completeTask(task: Task){
        for(cycleTerm in CycleTerm.values()){
            if(cycleTerm.term > task.status){
                repositoryTask.updateStatusTask(cycleTerm.term, task)
                break
            }
        }
        onCompleteUpdate.postValue(true)
    }

    fun closeArticleDetail(){
        toList.postValue(true)

    }
}