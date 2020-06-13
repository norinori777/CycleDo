package com.google.norinori6791.cycledo.model.setting

import android.content.Context
import android.content.SharedPreferences
import com.google.norinori6791.cycledo.model.enum.SettingDefinition

class Maintain(private val context: Context) {
    private val settingVersion = 1
    private val sharedPreference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init{
        sharedPreference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }

    private fun isInitialize(): Boolean = sharedPreference.contains(SettingDefinition.INITIALIZE.name)

    private fun isVersionUp(): Boolean{
        val nowVersion = sharedPreference.getInt(SettingDefinition.SETTING_VERSION.name, SettingDefinition.SETTING_VERSION.defaultInt)
        if(nowVersion < settingVersion){
            return true
        }
        return false
    }

    fun doInitialize(){
        if(!isInitialize()){
            SettingDefinition.values().forEach {
                putSetting(it)
            }
            editor.putInt(SettingDefinition.SETTING_VERSION.name, settingVersion)
        }

    }

    // バージョンアップ
    fun doVersionUp(){
        if(isVersionUp()){
            SettingDefinition.values().forEach {
                if(!sharedPreference.contains(it.name)){
                    putSetting(it)
                }
            }
            editor.putInt(SettingDefinition.SETTING_VERSION.name, settingVersion)
        }
    }

    // 設定値をDefault値に戻す
    fun resetSetting(){
        SettingDefinition.values().forEach {
            if(it.isBooleanKey) putSetting(it)
        }
    }

    private fun putSetting(setting: SettingDefinition){
        setting.let {
            if(it.isBooleanKey) editor.putBoolean(it.name, it.defaultBoolean)
            if(it.isIntKey) editor.putInt(it.name, it.defaultInt)
            if(it.isLongKey) editor.putLong(it.name, it.defaultLong)
            if(it.isStringKey) editor.putString(it.name, it.defaultString)
        }
    }
}