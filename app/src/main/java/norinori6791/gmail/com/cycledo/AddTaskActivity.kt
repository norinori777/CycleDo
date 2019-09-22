package norinori6791.gmail.com.cycledo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import norinori6791.gmail.com.cycledo.Util.ToolbarSet
import norinori6791.gmail.com.cycledo.addtask.AddTaskViewModel
import norinori6791.gmail.com.cycledo.databinding.ActivityAddTaskBinding
import norinori6791.gmail.com.cycledo.task.TaskRepository

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private val taskRepository = TaskRepository()
    private val viewModel = AddTaskViewModel(taskRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)
        binding.addForm =viewModel

        val adapter = viewModel.getCategoryAdapter(applicationContext, R.layout.activity_add_task)
        binding.spinnerCategory.setAdapter(adapter)


        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        val toolbarset = ToolbarSet(toolbar)
        setSupportActionBar(toolbarset.setting())
    }
    private fun getCurrentTime() : Long = System.currentTimeMillis()
}
