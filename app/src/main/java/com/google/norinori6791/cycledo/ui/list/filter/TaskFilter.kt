package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task

interface TaskFilter {
    fun isMatches(task: Task): Boolean
}