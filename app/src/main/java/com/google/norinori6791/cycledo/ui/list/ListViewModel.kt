package com.google.norinori6791.cycledo.ui.list

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
    var toEdit = MutableLiveData<Task>()

    fun getAllTask() = taskItems.postValue(realmResultToTaskList(repository.getAllTasks()))

    private fun realmResultToTaskList(realmResults: RealmResults<RealmTask>): MutableList<Task>{
        var tasks:MutableList<Task> = mutableListOf()
        realmResults.forEach{
            var tags: MutableList<Tag> = mutableListOf()
            it.tags?.forEach{
                tags.add(Tag(it.name))
            }
            var task = Task(it.uniqueId, it.deleted, it.title, it.content, it.status, it.startDate, it.addDate, it.modifyDate, tags)
            tasks.add(task)
        }
        return tasks
    }

    fun deleteTask(task:Task){
        repositoryTask.logicalDeleteTask(task)
        onCompleteDelete.postValue(true)
    }

    fun completeTask(task: Task){
        CycleTerm.values().forEach {
            if(it.term > task.status){
                repositoryTask.updateStatusTask(it.term, task)
                return@forEach
            }
        }
        onCompleteUpdate.postValue(true)
    }
}