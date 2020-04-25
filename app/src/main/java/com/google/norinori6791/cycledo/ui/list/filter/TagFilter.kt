package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.data.Task

interface TagFilter {
    fun isNotMatches(task: Task, tag: Tag): Boolean
}