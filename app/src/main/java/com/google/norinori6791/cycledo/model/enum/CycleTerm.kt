package com.google.norinori6791.cycledo.model.enum

enum class CycleTerm(val term: Int, val display: String, val backGroundColor: String) {
    ZERO(0, "0day", "title_try_number_0"),
    ONE(1, "1day", "title_try_number_1"),
    FOUR(4, "4day", "title_try_number_4"),
    SEVEN(7, "7day", "title_try_number_7"),
    ELEVEN(11, "11day", "title_try_number_11"),
    FIFTEEN(15, "15day", "title_try_number_15"),
    TWENTY(20, "20day", "title_try_number_20"),
    FINISH(999, "finish", "finish")
}