package com.google.norinori6791.cycledo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class InitializeApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        Realm.init(this)

        val builder = RealmConfiguration.Builder()
            .name("CycleDo.realm").deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(builder.build())
    }
}