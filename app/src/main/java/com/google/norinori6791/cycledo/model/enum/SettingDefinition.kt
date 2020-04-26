package com.google.norinori6791.cycledo.model.enum

import kotlin.reflect.KClass

enum class SettingDefinition(val type: KClass<*>, val defaultValue: Any, val isReset: Boolean) {
    // 全体
    INITIALIZE(Boolean::class, true, false),
    SETTING_VERSION(Int::class, 1, false),

    // 通知
    NOTIFICATION_TIME(Int::class, 7, true);


    init {
        if (defaultValue != null) {
            requireNotNull(type)
            require(type.isInstance(defaultValue))
        } else require(type == null)
    }

    internal val isReadWriteKey: Boolean
        get() = type != null

    internal val isBooleanKey: Boolean
        get() = type == Boolean::class

    internal val isIntKey: Boolean
        get() = type == Int::class

    internal val isLongKey: Boolean
        get() = type == Long::class

    internal val isStringKey: Boolean
        get() = type == String::class

    internal val defaultBoolean: Boolean
        get() = defaultValue as Boolean

    internal val defaultInt: Int
        get() = defaultValue as Int

    internal val defaultLong: Long
        get() = defaultValue as Long

    internal val defaultString: String
        get() = defaultValue as String
}