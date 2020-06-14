package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.model.realm.RealmTag
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.util.NowDate
import io.realm.Realm
import io.realm.RealmList
import java.util.*

class TaskItem {
    val realm: Realm = Realm.getDefaultInstance()
    private val nowDate = NowDate()

    fun insertTask(task: Task){
        realm.executeTransaction {
            var realmTask = RealmTask()
            realmTask.title = task.title
            realmTask.content = task.content
            realmTask.startDate = nowDate.get()
            realmTask.addDate = nowDate.get()
            realmTask.modifyDate = nowDate.get()
            task.tags?.forEach { tag ->
                var realmTag = realm.where(RealmTag::class.java).equalTo("name", tag.name).findFirst()
                realmTag?.let {
                    realmTask.tags?.add(realmTag)
                }
            }
            realm.copyToRealmOrUpdate(realmTask)
        }
    }

    fun updateTask(task: Task?){
        realm.executeTransaction {
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId",task?.uniqueId).findFirst()
            updateTask?.title = task?.title
            updateTask?.content = task?.content

            task?.tags?.forEach { tag ->
                run {
                    updateTask?.tags?.forEach { realmTag ->
                        if (realmTag.name == tag.name) return@run
                    }
                    val realmTag = RealmTag()
                    realmTag.uniqueId = tag.uniqueId
                    realmTag.deleted = tag.deleted
                    realmTag.name  = tag.name
                    updateTask?.tags?.add(realmTag)
                }
            }
        }
    }

    fun logicalDeleteTask(task: Task){
        realm.executeTransaction {
            val deleteTask = it.where(RealmTask::class.java).equalTo("uniqueId",task.uniqueId).findFirst()
            deleteTask?.deleted = 1
        }
    }

    fun notLogicalDeleteTask(task: Task){
        realm.executeTransaction {
            val deleteTask = it.where(RealmTask::class.java).equalTo("uniqueId",task.uniqueId).findFirst()
            deleteTask?.deleted = 0
        }
    }

    fun physicalDeleteTask(task: Task){
        realm.executeTransaction {
            val deleteTask = it.where(RealmTask::class.java).equalTo("uniqueId",task.uniqueId).findFirst()
            deleteTask?.deleteFromRealm()
        }
    }

    fun updateStatusTask(nextStatus: Int, task: Task){
        realm.executeTransaction{
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId", task.uniqueId).findFirst()
            updateTask?.status = nextStatus
        }
    }

    fun updateResetCycleAll(task: Task){
        realm.executeTransaction{
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId", task.uniqueId).findFirst()
            updateTask?.status = 0
            updateTask?.startDate = nowDate.get()
            updateTask?.modifyDate = nowDate.get()
        }
    }

    fun updateResetCycle(task: Task){
        realm.executeTransaction{
            val updateTask = it.where(RealmTask::class.java).equalTo("uniqueId", task.uniqueId).findFirst()
            updateTask?.status = task.status
            updateTask?.startDate = task.startDate
            updateTask?.modifyDate = nowDate.get()
        }
    }
}