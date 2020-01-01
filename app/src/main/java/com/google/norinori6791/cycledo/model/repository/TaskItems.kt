package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.realm.RealmTask
import io.realm.Realm
import io.realm.RealmResults

class TaskItems {
    val realm = Realm.getDefaultInstance()

    fun getAllTasks(): RealmResults<RealmTask> = realm.where(RealmTask::class.java).findAll()
}