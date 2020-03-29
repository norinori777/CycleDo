package com.google.norinori6791.cycledo.model.data

import android.os.Looper.loop
import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.enum.CycleTerm
import com.google.norinori6791.cycledo.util.NowDate
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Task(
    @PrimaryKey
    open var uniqueId : String? = UUID.randomUUID().toString(),
    open var deleted: Int = 0,
    open var title: String? = "",
    open var content: String? = "",
    open var status: Int = 0,
    open var startDate: String? = "",
    open var addDate: String? = "",
    open var modifyDate: String? = "",
    open var tags: MutableList<Tag>? =  mutableListOf<Tag>()
): Serializable {
    fun getTaskTermColor(): String{
        val sdFormat = SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss")
        val now = Calendar.getInstance()
        var color = "title_try_number_1"

        run loop@ {
            CycleTerm.values().forEach {
                var beforeDate = Calendar.getInstance()
                beforeDate.time = sdFormat.parse(startDate)
                beforeDate.add(Calendar.DATE, it.term)
                var afterDate = Calendar.getInstance()
                afterDate.time = sdFormat.parse(startDate)
                afterDate.add(Calendar.DATE, it.term + 1)
                if(beforeDate.compareTo(now) < 0 &&
                        afterDate.compareTo(now) > 0){
                    color = it.backGroundColor
                    return@loop
                }
            }
        }
        return color
    }
}