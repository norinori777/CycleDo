package norinori6791.gmail.com.cycledo.itemlist

import norinori6791.gmail.com.cycledo.model.Task
import javax.inject.Inject

class ItemListService @Inject constructor(
    private val repository : ItemListRepository
){
    fun getItems():MutableList<Task> = repository.getItems()
    fun observeItems():MutableList<Task> = repository.observeItems()
}