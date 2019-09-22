package norinori6791.gmail.com.cycledo.task

import android.database.sqlite.SQLiteDatabase
import jp.wasabeef.richeditor.Utils.getCurrentTime
import norinori6791.gmail.com.cycledo.MainApplication
import norinori6791.gmail.com.cycledo.model.Task

class TaskRepository {
    val db: SQLiteDatabase = MainApplication.helper.writableDatabase

    fun addTask(task: Task) {
        val sqlInsert = "INSERT INTO TASK (category, title, content, startdate, cycle) VALUES (?, ?, ?, ?, ?)"
        val stmt = db.compileStatement(sqlInsert)

        stmt.bindLong(1, task.category.toLong())
        stmt.bindString(2, task.title)
        stmt.bindString(3,task.content)
        stmt.bindLong(4, getCurrentTime())
        stmt.bindLong(5, 1)

        stmt.executeInsert()
    }

    fun removeTask(id: Int){
        val sqlDelete = "DELETE FROM TASK WHERE id = ?"
        val stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1, id.toLong())
        stmt.executeUpdateDelete()
    }
}