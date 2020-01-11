package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.realm.RealmTask
import io.realm.Realm
import io.realm.RealmResults

class TaskItems {
    private val realm = Realm.getDefaultInstance()

    fun getAllTasks(): RealmResults<RealmTask> {
        val deleted = 0
        return realm.where(RealmTask::class.java).equalTo("deleted", deleted).findAll()
    }
}