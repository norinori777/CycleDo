package com.google.norinori6791.cycledo.model.enum

enum class CycleTerm(val term: Int, val display: String, val backGroundColor: String) {
    ONE(1, "1day", "oneDayTask"),
    FOUR(4, "4day", "fourDayTask"),
    SEVEN(7, "7day", "sevenDayTask"),
    ELEVEN(11, "11day", "elevenDayTask"),
    FIFTEEN(15, "15day", "fifteenDayTask"),
    TWENTY(20, "20day", "twentyDayTask")
}