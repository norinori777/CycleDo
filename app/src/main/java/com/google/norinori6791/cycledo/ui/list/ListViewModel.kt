package com.google.norinori6791.cycledo.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.model.repository.TaskItems
import io.realm.RealmResults


class ListViewModel : ViewModel() {

    private val repository = TaskItems()
    var taskItems = MutableLiveData<MutableList<Task>>()

    fun getAllTask() = taskItems.postValue(realmResultToTaskList(repository.getAllTasks()))

    private fun realmResultToTaskList(realmResults: RealmResults<RealmTask>): MutableList<Task>{
        var tasks:MutableList<Task> = mutableListOf()
        realmResults.forEach{
            var tags: MutableList<Tag> = mutableListOf()
            it.tags?.forEach{
                tags.add(Tag(it.name))
            }
            var task = Task(it.uniqueId, it.title, it.content, it.addDate, it.modifyDate, tags)
            tasks.add(task)
        }
        return tasks
    }
}