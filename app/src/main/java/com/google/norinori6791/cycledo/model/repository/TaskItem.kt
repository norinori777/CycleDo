package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.util.NowDate
import io.realm.Realm

class TaskItem {
    val realm = Realm.getDefaultInstance()
    private val nowDate = NowDate()

    fun insertTask(task: Task){
        var realmTask = RealmTask()
        realmTask.title = task.title
        realmTask.content = task.content
        realmTask.startDate = nowDate.get()
        realmTask.addDate = nowDate.get()
        realmTask.modifyDate = nowDate.get()

        realm.executeTransaction {
            it.copyToRealm(realmTask)
        }
    }

    fun updateTask(task: Task?){
        realm.executeTransaction {
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId",task?.uniqueId).findFirst()
            updateTask?.title = task?.title
            updateTask?.content = task?.content
        }
    }

    fun logicalDeleteTask(task: Task){
        realm.executeTransaction {
            val deleteTask = it.where(RealmTask::class.java).equalTo("uniqueId",task.uniqueId).findFirst()
            deleteTask?.deleted = 1
        }
    }

    fun updateStatusTask(nextStatus: Int, task: Task){
        realm.executeTransaction{
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId", task.uniqueId).findFirst()
            updateTask?.status = nextStatus
        }
    }
}