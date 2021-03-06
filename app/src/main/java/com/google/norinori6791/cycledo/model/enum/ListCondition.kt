package com.google.norinori6791.cycledo.model.enum

enum class ListCondition(val type: String, val display: String) {
    NOW("now", "本日"),
    ALL("all", "全部"),
    COMPLETED("completed", "完了済み"),
    DELETED("deleted", "ゴミ箱")
}