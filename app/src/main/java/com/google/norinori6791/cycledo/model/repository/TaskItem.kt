package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.realm.Task
import com.google.norinori6791.cycledo.util.NowDate
import io.realm.Realm

class TaskItem {
    val realm = Realm.getDefaultInstance()
    private val nowDate = NowDate()

    fun insertTask(task: Task){
        realm.executeTransaction {
            it.copyToRealm(task)
        }
    }
}