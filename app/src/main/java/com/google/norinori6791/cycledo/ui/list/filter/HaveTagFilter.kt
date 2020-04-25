package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm

class HaveTagFilter: TagFilter {
    override fun isNotMatches(task: Task, tag: Tag): Boolean {
        task.tags?.let {
            if(it.contains(tag)) return false
        }
        return true
    }
}