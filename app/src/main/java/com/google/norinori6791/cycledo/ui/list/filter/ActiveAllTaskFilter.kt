package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task

class ActiveAllTaskFilter: TaskFilter {
    override fun isMatches(task: Task): Boolean {
        if(task.deleted == 0) return true
        return false
    }
}