package com.google.norinori6791.cycledo.ui.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.model.enum.ListCondition
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.model.repository.TaskItem
import com.google.norinori6791.cycledo.model.repository.TaskItems
import com.google.norinori6791.cycledo.ui.list.filter.*
import io.realm.RealmResults

class ListViewModel : ViewModel() {

    private val repository = TaskItems()
    private val repositoryTask = TaskItem()
    var conditionFilter = "now"
    var taskItems = MutableLiveData<MutableList<Task>>()
    var onCompleteDelete = MutableLiveData<Boolean>()
    var onCompleteUpdate = MutableLiveData<Boolean>()
    var onChangeFilter = MutableLiveData<Boolean>()
    var isShowDetail = ObservableBoolean(false)
    var toEdit= MutableLiveData<Task>()
    var toShow= MutableLiveData<Task>()
    var toList = MutableLiveData<Boolean>()
    lateinit var filter: TaskFilter

    fun getTask(){
        filter = when(conditionFilter){
            ListCondition.NOW.display -> NowTaskFilter()
            ListCondition.ALL.display -> ActiveAllTaskFilter()
            ListCondition.DELETED.display-> DeletedTaskFilter()
            ListCondition.COMPLETED.display -> CompletedTaskFilter()
            else -> NowTaskFilter()
        }
        taskItems.postValue(realmResultToTaskList(repository.getAllTasks(), filter))
    }

    private fun realmResultToTaskList(realmResults: RealmResults<RealmTask>, conditionFilter: TaskFilter): MutableList<Task>{
        var tasks:MutableList<Task> = mutableListOf()
        realmResults.forEach{
            var tags: MutableList<Tag> = mutableListOf()
            it.tags?.forEach{ tag ->
                tags.add(Tag(tag.uniqueId, tag.deleted, tag.name))
            }
            var task = Task(it.uniqueId, it.deleted, it.title, it.content, null, it.status, it.startDate, it.addDate, it.modifyDate, tags)
            if(filter.isMatches(task)) tasks.add(task)
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
                onCompleteUpdate.postValue(true)
                break
            }
        }
    }

    fun closeArticleDetail(){
        toList.postValue(true)
    }

    fun changeListCondition(condition: String){
        conditionFilter = condition
        getTask()
        onChangeFilter.postValue(true)
    }
}