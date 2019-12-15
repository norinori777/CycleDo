package com.google.norinori6791.cycledo.model.realm

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class Tag: RealmObject(){
    open var name: String = ""
    @LinkingObjects("tags")
    open val task: RealmResults<Task>? = null
}