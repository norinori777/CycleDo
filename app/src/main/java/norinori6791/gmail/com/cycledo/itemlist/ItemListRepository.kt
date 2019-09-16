package norinori6791.gmail.com.cycledo.itemlist

import norinori6791.gmail.com.cycledo.MainApplication
import norinori6791.gmail.com.cycledo.model.Item
import java.text.SimpleDateFormat
import java.util.*

class ItemListRepository {
    fun getItems(cycle: Int = 0): MutableList<Item> {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val mutableList: MutableList<Item> = mutableListOf()
        val db = MainApplication.helper.writableDatabase
        val sqlSelect = "SELECT * FROM TASK"
        var cousor = db.rawQuery(sqlSelect, null)

        while (cousor.moveToNext()) {
            val item: Item = Item(
                cousor.getInt(cousor.getColumnIndex("id")),
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

    fun observeItems() : MutableList<Item> {
        val mutableList: MutableList<Item> = mutableListOf()
        return mutableList
    }
}