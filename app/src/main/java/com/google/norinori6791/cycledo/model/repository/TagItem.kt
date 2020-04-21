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

    fun updateTag(tag: Tag){
        realm.executeTransaction {
            val updateTag = it.where(RealmTag::class.java).equalTo("uniqueId", tag.uniqueId).findFirst()
            updateTag?.name = tag.name
        }
    }
}