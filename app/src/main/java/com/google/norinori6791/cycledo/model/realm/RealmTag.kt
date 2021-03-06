package com.google.norinori6791.cycledo.model.realm

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmTag: RealmObject(){
    @PrimaryKey
    open var uniqueId : String? = UUID.randomUUID().toString()
    open var deleted: Int = 0
    open var name: String = ""
    @LinkingObjects("tags")
    open val task: RealmResults<RealmTask>? = null
}