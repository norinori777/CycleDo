package norinori6791.gmail.com.cycledo.itemlist

import javax.inject.Inject
import norinori6791.gmail.com.cycledo.model.Task
import norinori6791.gmail.com.cycledo.task.TaskService

class ItemListViewModel @Inject constructor(
    private val service : ItemListService,
    private val taskService : TaskService
) {
    fun getItems() = service.getItems()
    fun observeItems() = service.observeItems()
//    fun addItem(item: Item) = taskService.addItem(item)
//    fun removeItem(item:Item) = taskService.removeItem(item)
}