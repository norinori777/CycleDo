package norinori6791.gmail.com.cycledo.model

import java.io.Serializable

data class Item (
    val id: Int,
    val category: Int,
    val title: String,
    val content: String,
    val startdate: String,
    val cycle: Int
): Serializable
