package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task
import java.text.SimpleDateFormat
import java.util.*

class NowTaskFilter: TaskFilter {
    override fun isMatches(task: Task): Boolean {
        val sdFormat = SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss")
        val now = Calendar.getInstance()

        var taskDate = Calendar.getInstance()
        taskDate.time = sdFormat.parse(task.startDate)
        taskDate.add(Calendar.DATE, task.status)

        var beforeDate = Calendar.getInstance()
        var afterDate = Calendar.getInstance()
        afterDate.add(Calendar.DATE, 1)
        if(beforeDate.compareTo(taskDate) < 0 &&
                afterDate.compareTo(taskDate) > 0){
            return true
        }
        return false
    }
}