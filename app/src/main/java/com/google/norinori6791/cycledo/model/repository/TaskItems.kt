package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.realm.RealmTask
import com.google.norinori6791.cycledo.ui.list.filter.TaskFilter
import io.realm.Realm
import io.realm.RealmResults

class TaskItems {
    private val realm = Realm.getDefaultInstance()

    fun getAllTasks(): RealmResults<RealmTask> {
        return realm.where(RealmTask::class.java).findAll()
    }

    fun getTaskByTag(tag: Tag, filter: TaskFilter): MutableList<Task> {
        var result: MutableList<Task> = mutableListOf()
        var tasks =  realm.where(RealmTask::class.java).equalTo("tags.uniqueId", tag.uniqueId).findAll()
        result = convertRealmTasksToTasks(tasks, filter)
        return result
    }

    private fun convertRealmTasksToTasks(realmTask: RealmResults<RealmTask>, filter: TaskFilter): MutableList<Task> {
        var tasks: MutableList<Task> = mutableListOf()
        realmTask.forEach { realmTask ->
            var tags: MutableList<Tag> = mutableListOf()
            realmTask.tags?.forEach { realmTag ->
                tags.add(Tag(realmTag.uniqueId, realmTag.deleted, realmTag.name))
            }
            var task = Task(realmTask.uniqueId, realmTask.deleted, realmTask.title, realmTask.content, null, realmTask.status, realmTask.startDate, realmTask.addDate, realmTask.modifyDate, tags)
            if(filter.isMatches(task)) tasks.add(task)
        }
        return tasks
    }
}