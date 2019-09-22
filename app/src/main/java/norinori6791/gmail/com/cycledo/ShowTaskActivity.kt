package norinori6791.gmail.com.cycledo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import norinori6791.gmail.com.cycledo.Util.ToolbarSet
import norinori6791.gmail.com.cycledo.databinding.ActivityShowTaskBinding
import norinori6791.gmail.com.cycledo.model.Task

class ShowTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_show_task)
        val binding: ActivityShowTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_task)
        val item:Task = intent.getSerializableExtra("item") as Task
        binding.item = item

        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        var toolbarset = ToolbarSet(toolbar)
        setSupportActionBar(toolbarset.setting())

        // Backボタンを有効にする
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setHomeButtonEnabled(true)

//        val edTask = findViewById<EditText>(R.id.ed_show_title)
//        val tvCycle = findViewById<TextView>(R.id.tv_show_cycle)
//        val tvStartdate = findViewById<TextView>(R.id.tv_show_start)
//        edTask.setText(intent.getStringExtra("title"))
//        tvCycle.setText(intent.getStringExtra("cycle"))
//        tvStartdate.setText(intent.getStringExtra("startdate"))

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            // 戻るボタンが押されたら
            android.R.id.home -> {
                // 現在のActivityを終了する
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
