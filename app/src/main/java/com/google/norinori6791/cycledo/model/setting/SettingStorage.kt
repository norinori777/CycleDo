package com.google.norinori6791.cycledo.model.setting

import android.content.Context
import android.content.SharedPreferences
import com.google.norinori6791.cycledo.model.enum.SettingDefinition

class SettingStorage(val context: Context, val setting: String) {
    private object PreferencesHolder {
        private var preferences: SharedPreferences? = null

        @Synchronized
        internal fun get(context: Context, setting: String): SharedPreferences {
            preferences?.let {
                return it
            }
            return context.getSharedPreferences(setting, Context.MODE_PRIVATE).also {
                preferences = it
            }
        }
    }
    private val preferences: SharedPreferences = PreferencesHolder.get(context, "setting")

    operator fun contains(key: SettingDefinition): Boolean {
        return preferences.contains(key.name)
    }

    // Boolean読み込み、書き込み
    fun writeBoolean(key: SettingDefinition, value: Boolean){
        require(key.isBooleanKey) { key.name + "is not key for Boolean"}
        preferences.edit().putBoolean(key.name, value).apply()
    }
    fun readBoolean(key: SettingDefinition): Boolean {
        require(key.isBooleanKey) { key.name + "is not key for Boolean"}
        return preferences.getBoolean(key.name, key.defaultBoolean)
    }

    // Int読み込み、書き込み
    fun writeInt(key: SettingDefinition, value: Int){
        require(key.isIntKey) { key.name + "is not key for Int"}
        preferences.edit().putInt(key.name, value).apply()
    }
    fun readInt(key: SettingDefinition): Int {
        require(key.isIntKey) { key.name + "is not key for Int"}
        return preferences.getInt(key.name, key.defaultInt)
    }

    // Long読み込み、書き込み
    fun writeLong(key: SettingDefinition, value: Long){
        require(key.isLongKey) { key.name + "is not key for Long"}
        preferences.edit().putLong(key.name, value).apply()
    }
    fun readLong(key: SettingDefinition): Long {
        require(key.isLongKey) { key.name + "is not key for Long"}
        return preferences.getLong(key.name, key.defaultLong)
    }

    // String読み込み、書き込み
    fun writeString(key: SettingDefinition, value: String){
        require(key.isStringKey) { key.name + "is not key for String"}
        preferences.edit().putString(key.name, value).apply()
    }
    fun readString(key: SettingDefinition): String {
        require(key.isStringKey) { key.name + "is not key for String"}
        return preferences.getString(key.name, key.defaultString)!!
    }
}