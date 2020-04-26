package com.google.norinori6791.cycledo.model.data

import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

data class Tag (
    @PrimaryKey
    open var uniqueId : String? = UUID.randomUUID().toString(),
    open var deleted: Int = 0,
    open var name: String = ""
): Serializable