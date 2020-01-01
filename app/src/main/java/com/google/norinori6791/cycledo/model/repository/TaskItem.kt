package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.util.NowDate
import io.realm.Realm
import java.util.*

class TaskItem {
    val realm = Realm.getDefaultInstance()
    private val nowDate = NowDate()

    fun insertTask(task: Task){
        var realmTask = RealmTask()
        realmTask.title = task.title
        realmTask.content = task.content
        realmTask.addDate = nowDate.get()
        realmTask.modifyDate = nowDate.get()

        realm.executeTransaction {
            it.copyToRealm(realmTask)
        }
    }
}