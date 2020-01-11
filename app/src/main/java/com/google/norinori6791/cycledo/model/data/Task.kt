package com.google.norinori6791.cycledo.model.data

import com.google.norinori6791.cycledo.model.data.Tag
import io.realm.annotations.PrimaryKey
import java.io.Serializable
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
): Serializable