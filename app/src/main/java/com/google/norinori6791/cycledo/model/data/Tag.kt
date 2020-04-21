package com.google.norinori6791.cycledo.model.data

import java.io.Serializable
import java.util.*

data class Tag (
    open var uniqueId : String? = UUID.randomUUID().toString(),
    open var deleted: Int = 0,
    open var name: String = ""
): Serializable