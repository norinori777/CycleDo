package com.google.norinori6791.cycledo.model.enum

enum class CycleTerm(val term: Int, val display: String, val backGroundColor: String) {
    ZERO(0, "0", "title_try_number_0"),
    ONE(1, "1", "title_try_number_1"),
    FOUR(5, "4", "title_try_number_4"),
    SEVEN(12, "7", "title_try_number_7"),
    ELEVEN(23, "11", "title_try_number_11"),
    FIFTEEN(38, "15", "title_try_number_15"),
    TWENTY(58, "20", "title_try_number_20"),
    FINISH(999, "finish", "finish")
}