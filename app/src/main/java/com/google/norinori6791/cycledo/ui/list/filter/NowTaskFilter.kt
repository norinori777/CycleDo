package com.google.norinori6791.cycledo.ui.list.filter

import com.google.norinori6791.cycledo.model.data.Task
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import java.text.SimpleDateFormat
import java.util.*

class NowTaskFilter: TaskFilter {
    override fun isMatches(task: Task): Boolean {
        val sdFormat = SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss")

        var taskDate = Calendar.getInstance()
        taskDate.time = sdFormat.parse(task.startDate)
        if(task.status > 0) taskDate.add(Calendar.MINUTE, task.status*24*60)

        var beforeDate = Calendar.getInstance()
        beforeDate.add(Calendar.DATE, -1)
        var afterDate = Calendar.getInstance()
        if(beforeDate < taskDate && afterDate > taskDate &&
            task.deleted == 0 && task.status < CycleTerm.FINISH.term) return true
        return false
    }
}