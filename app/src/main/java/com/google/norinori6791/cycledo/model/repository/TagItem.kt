package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.data.Tag
import com.google.norinori6791.cycledo.model.realm.RealmTag
import io.realm.Realm

class TagItem {
    val realm: Realm = Realm.getDefaultInstance()

    fun insertTag(tag: Tag){
        var realmTag = RealmTag()
        realmTag.name = tag.name

        realm.executeTransaction {
            it.copyToRealm(realmTag)
        }
    }
}