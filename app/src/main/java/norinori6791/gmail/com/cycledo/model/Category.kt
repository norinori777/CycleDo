package norinori6791.gmail.com.cycledo.model

import java.io.Serializable

data class CategoryItem (
    val id: Int,
    val name: String
): Serializable

data class Categories (
    val items: List<CategoryItem>
): Serializable