package norinori6791.gmail.com.cycledo.itemlist

import norinori6791.gmail.com.cycledo.model.Item
import javax.inject.Inject

class ItemListService @Inject constructor(
    private val repository : ItemListRepository
){
    fun getItems():MutableList<Item> = repository.getItems()
    fun observeItems():MutableList<Item> = repository.observeItems()
}