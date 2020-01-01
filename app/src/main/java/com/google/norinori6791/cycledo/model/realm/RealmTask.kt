package com.google.norinori6791.cycledo.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmTask: RealmObject(){
    @PrimaryKey
    open var uniqueId : String? = UUID.randomUUID().toString()
    open var title: String? = ""
    open var content: String? = ""
    open var addDate: String? = ""
    open var modifyDate: String? = ""
    open var tags: RealmList<RealmTag>? =  RealmList()
}