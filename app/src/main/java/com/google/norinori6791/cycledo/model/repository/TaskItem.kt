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
        var realmTask = RealmTask()
        realmTask.title = task.title
        realmTask.content = task.content
        realmTask.startDate = nowDate.get()
        realmTask.addDate = nowDate.get()
        realmTask.modifyDate = nowDate.get()
        task.tags?.forEach {
            var realmTag = RealmTag()
            realmTag.deleted = it.deleted
            realmTag.name = it.name
            realmTask.tags?.add(realmTag)
        }

        realm.executeTransaction {
            it.copyToRealm(realmTask)
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
                    var realmTag = RealmTag()
                    realmTag.deleted = tag.deleted
                    realmTag.name = tag.name
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