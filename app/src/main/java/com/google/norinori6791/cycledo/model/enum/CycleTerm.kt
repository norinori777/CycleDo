package com.google.norinori6791.cycledo.model.enum

enum class CycleTerm(val term: Int, val display: String, val backGroundColor: String) {
    ONE(1, "1day", "#FFFFFF"),
    FOUR(4, "4day", "＃FFFFC7"),
    SEVEN(7, "7day", "＃E5FFC2"),
    ELEVEN(11, "11day", "＃C1FFB5"),
    FIFTEEN(15, "15day", "#AAFFBB"),
    TWENTY(20, "20day", "＃96FFCE")
}