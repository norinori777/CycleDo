package com.google.norinori6791.cycledo.model.data

import android.text.Spanned
import com.google.norinori6791.cycledo.model.enum.CycleTerm
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
    open var html: Spanned? = null,
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
                if(status == it.term) return it.backGroundColor
            }
        }
        return color
    }
}