package norinori6791.gmail.com.cycledo.category

import android.database.sqlite.SQLiteDatabase
import norinori6791.gmail.com.cycledo.MainApplication
import norinori6791.gmail.com.cycledo.model.CategoryItem
import norinori6791.gmail.com.cycledo.model.Task

class CategoryRepository {
    val db: SQLiteDatabase = MainApplication.helper.writableDatabase

    fun addCategory(name: String){
        val sqlInsert = "INSERT INTO CATEGORY (name) VALUES (?)"
        val stmt = db.compileStatement(sqlInsert)

        stmt.bindString(1, name)

        stmt.executeInsert()
    }

    fun removeCategory(id: Int){
        val sqlUpdate = "UPDATE FROM TASK SET category = 0 WHERE category = ?"
        val stmt = db.compileStatement(sqlUpdate)

        stmt.bindString(1, Integer(id).toString())
        stmt.executeUpdateDelete()

    }

    fun getAll():List<CategoryItem>{
        val mutableList: MutableList<CategoryItem> = mutableListOf()
        val sqlSelect = "SELECT * FROM CATEGORY"
        var cousor = db.rawQuery(sqlSelect, null)
        while (cousor.moveToNext()) {
            val item = CategoryItem(
                cousor.getInt(cousor.getColumnIndex("_id")),
                cousor.getString(cousor.getColumnIndex("name"))
            )
            mutableList.add(item)
        }
        return mutableList
    }
}