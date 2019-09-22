package norinori6791.gmail.com.cycledo.itemlist

import norinori6791.gmail.com.cycledo.MainApplication
import norinori6791.gmail.com.cycledo.model.Task
import java.text.SimpleDateFormat
import java.util.*

class ItemListRepository {
    fun getItems(cycle: Int = 0): MutableList<Task> {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val mutableList: MutableList<Task> = mutableListOf()
        val db = MainApplication.helper.writableDatabase
        val sqlSelect = "SELECT * FROM TASK"
        var cousor = db.rawQuery(sqlSelect, null)

        while (cousor.moveToNext()) {
            val item = Task(
                cousor.getInt(cousor.getColumnIndex("_id")),
                cousor.getInt(cousor.getColumnIndex("category")),
                cousor.getString(cousor.getColumnIndex("title")),
                cousor.getString(cousor.getColumnIndex("content")),
                dateFormat.format(Date(cousor.getInt(cousor.getColumnIndex("startdate")).toLong())),
                cousor.getInt(cousor.getColumnIndex("cycle"))
            )
            mutableList.add(item)
        }
        return mutableList
    }

    fun observeItems() : MutableList<Task> {
        val mutableList: MutableList<Task> = mutableListOf()
        return mutableList
    }
}