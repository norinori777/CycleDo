package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm

class CompletedTaskFilter: TaskFilter {
    override fun isMatches(task: Task): Boolean {
        if(task.status == CycleTerm.FINISH.term && task.deleted == 0) return true
        return false
    }
}