package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm

class ActiveAllTaskFilter: TaskFilter {
    override fun isMatches(task: Task): Boolean {
        if(task.deleted == 0 && task.status != CycleTerm.FINISH.term) return true
        return false
    }
}