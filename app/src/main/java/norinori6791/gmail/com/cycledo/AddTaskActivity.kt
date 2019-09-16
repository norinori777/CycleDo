package norinori6791.gmail.com.cycledo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import norinori6791.gmail.com.cycledo.Util.ToolbarSet

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        val toolbarset = ToolbarSet(toolbar)
        setSupportActionBar(toolbarset.setting())

        val btadd = findViewById<Button>(R.id.btAdd)
        val addListener = AddButtonOnClick()
        btadd.setOnClickListener(addListener)

        val btcancel = findViewById<Button>(R.id.btCancel)
        val cancelListener = CancelButtonOnClick()
        btcancel.setOnClickListener(cancelListener)
    }
    inner class CancelButtonOnClick: View.OnClickListener {
        override fun onClick(view: View) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    inner class AddButtonOnClick: View.OnClickListener {
        override fun onClick(view: View?) {
            // DB登録処理
            val db = MainApplication.helper.writableDatabase
            val etTitle = findViewById<EditText>(R.id.edAddTitle)
            val etContent = findViewById<EditText>(R.id.edAddContent)
            val sqlInsert = "INSERT INTO TASK (category, title, content, startdate, cycle) VALUES (?, ?, ?, ?, ?)"
            val stmt = db.compileStatement(sqlInsert)

            stmt.bindLong(1,1)
            stmt.bindString(2, etTitle.text.toString())
            stmt.bindString(3,etContent.text.toString())
            stmt.bindLong(4, getCurrentTime())
            stmt.bindLong(5, 1)

            stmt.executeInsert()

            etTitle.setText("")
            etContent.setText("")
        }
    }
    private fun getCurrentTime() : Long = System.currentTimeMillis()
}
