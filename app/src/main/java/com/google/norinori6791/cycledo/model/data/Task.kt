package com.google.norinori6791.cycledo.model.data

import com.google.norinori6791.cycledo.model.data.Tag
import io.realm.annotations.PrimaryKey
import java.util.*

class Task(
    @PrimaryKey
    open var uniqueId : String? = UUID.randomUUID().toString(),
    open var title: String? = "",
    open var content: String? = "",
    open var addDate: String? = "",
    open var modifyDate: String? = "",
    open var tags: MutableList<Tag>? =  mutableListOf<Tag>()
)