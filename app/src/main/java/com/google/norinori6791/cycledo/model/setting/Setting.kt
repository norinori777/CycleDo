package com.google.norinori6791.cycledo.model.setting

import com.google.norinori6791.cycledo.model.enum.SettingDefinition

class Setting(private val storage: SettingStorage) {
    // 通知時間設定
    fun changeNotificationTime(value: Int){
        storage.writeInt(SettingDefinition.NOTIFICATION_TIME, value)
    }
    fun getNotificationTime(): Int{
        return storage.readInt(SettingDefinition.NOTIFICATION_TIME)
    }
}