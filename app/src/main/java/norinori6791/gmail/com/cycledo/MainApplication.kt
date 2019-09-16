package norinori6791.gmail.com.cycledo

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper

class MainApplication : Application() {
    companion object {
        lateinit var helper: SQLiteOpenHelper private set
    }

    override fun onCreate(){
        super.onCreate()
        helper = DatabaseHelper(this@MainApplication)
    }

    override fun onTerminate() {
        helper.close()
        super.onTerminate()
    }
}