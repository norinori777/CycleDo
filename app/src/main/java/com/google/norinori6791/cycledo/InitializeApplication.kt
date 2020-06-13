package com.google.norinori6791.cycledo

import android.app.Application
import com.google.norinori6791.cycledo.model.setting.Maintain
import com.google.norinori6791.cycledo.model.setting.Setting
import com.google.norinori6791.cycledo.model.setting.SettingStorage
import io.realm.Realm
import io.realm.RealmConfiguration

class InitializeApplication: Application() {
    private lateinit var settingStorage: SettingStorage
    private lateinit var setting: Setting

    override fun onCreate(){
        super.onCreate()

        /****************************
         * SharedPreference取得
         **************************/
        applicationContext?.let {
            settingStorage = SettingStorage(it, "setting")
            setting = Setting(settingStorage)
        }
        // SharedPreferenceの初期化処理
        val sharedPreference = Maintain(applicationContext)
        sharedPreference.doInitialize()

        // SharedPreferenceのバージョンアップ処理
        sharedPreference.doVersionUp()


        Realm.init(this)

        val builder = RealmConfiguration.Builder()
            .name("CycleDo.realm").deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(builder.build())
    }
}