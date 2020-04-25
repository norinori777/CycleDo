package com.google.norinori6791.cycledo.model.repository

import com.google.norinori6791.cycledo.model.realm.RealmTag
import io.realm.Realm
import io.realm.RealmResults

class TagItems {
    private val realm = Realm.getDefaultInstance()

    fun getAllTags(): RealmResults<RealmTag> {
        return realm.where(RealmTag::class.java).findAll()
    }
}