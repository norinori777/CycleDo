package norinori6791.gmail.com.cycledo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION
){
  companion object {
      private const val DATABASE_NAME = "task.db"
      private const val DATABASE_VERSION = 1
  }
    override fun onCreate(db: SQLiteDatabase){
        var sb = StringBuilder()
        sb.append("CREATE TABLE TASK (")
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,")
        sb.append("category INTEGER,")
        sb.append("title TEXT,")
        sb.append("content TEXT,")
        sb.append("startdate INTEGER,")
        sb.append("cycle INTEGER")
        sb.append(");")

        var sql = sb.toString()
        db.execSQL(sql)

        sb = StringBuilder()
        sb.append("CREATE TABLE CATEGORY (")
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,")
        sb.append("name TEXT")
        sb.append(");")

        sql = sb.toString()
        db.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}