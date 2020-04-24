package com.google.norinori6791.cycledo.ui.label

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.realm.RealmTag
import com.google.norinori6791.cycledo.model.repository.TagItems
import com.google.norinori6791.cycledo.model.repository.TaskItems
import com.google.norinori6791.cycledo.ui.list.filter.TaskFilter
import io.realm.RealmResults

class LabelViewModel : ViewModel() {
    private val repository = TagItems()
    private val taskRepository = TaskItems()
    var allTags: MutableList<Tag> = getTags()
    var onClickLabel = MutableLiveData<Tag>()

    private fun getTags(): MutableList<Tag>{
        return realmResultToTagList(repository.getAllTags())
    }

    private fun realmResultToTagList(realmResults: RealmResults<RealmTag>): MutableList<Tag>{
        var tags: MutableList<Tag> = mutableListOf()
        realmResults.forEach {
            var tag = Tag(it.uniqueId, it.deleted, it.name)
            tags.add(tag)
        }
        return tags
    }

    fun getTaskByTag(tag: Tag, filter: TaskFilter): MutableList<Task>{
        return taskRepository.getTaskByTag(tag, filter)
    }

    fun labelClick(tag: Tag){
        onClickLabel.postValue(tag)
    }
}