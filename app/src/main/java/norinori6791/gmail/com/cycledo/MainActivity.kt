package norinori6791.gmail.com.cycledo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import norinori6791.gmail.com.cycledo.Util.ToolbarSet
import norinori6791.gmail.com.cycledo.databinding.ActivityMainBinding
import norinori6791.gmail.com.cycledo.databinding.CardviewTaskBinding
import norinori6791.gmail.com.cycledo.itemlist.ItemListRepository
import norinori6791.gmail.com.cycledo.itemlist.ItemListViewModel
import norinori6791.gmail.com.cycledo.model.Task
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ItemListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ツールバーをセット
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarset = ToolbarSet(toolbar)
        setSupportActionBar(toolbarset.setting())

        // データ取得
        val ilRepository = ItemListRepository()
        val listItems = ilRepository.getItems(0)

        val adapter = MyAdpater(this)
        adapter.items = listItems
        binding.loRecycleview.layoutManager = LinearLayoutManager(this)
        binding.loRecycleview.adapter = adapter


        // タスク追加リスナーをセット
        val btadd = findViewById<FloatingActionButton>(R.id.btStartAdd)
        val listener = AddButtonClickListener()
        btadd.setOnClickListener(listener)

    }

    private inner class MyHolder(val binding: CardviewTaskBinding): RecyclerView.ViewHolder(binding.root)

    private inner class MyAdpater(context: Context) : RecyclerView.Adapter<MyHolder>(){
        var items: List<Task> = emptyList()
        private val inflater = LayoutInflater.from(context)

        override fun getItemCount(): Int = items.size

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val binding: CardviewTaskBinding = DataBindingUtil.inflate(inflater, R.layout.cardview_task, parent, false)
            binding.cardview.setOnClickListener(ItemClickListener(binding))
            return MyHolder(binding)
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.binding.item = items[position]
        }
    }

    inner class ItemClickListener(val binding: CardviewTaskBinding): View.OnClickListener{
        override fun onClick(view: View){

            val item = Task(
                binding.tvId.text.toString().toInt(),
                1,
                binding.tvTaskTitle.text.toString(),
                binding.edContent.text.toString(),
                binding.tvStart.text.toString(),
                binding.tvCycle.text.toString().toInt()
            )

            val intent = Intent(applicationContext, ShowTaskActivity::class.java)
            intent.putExtra("item", item)

            startActivity(intent)
        }
    }


    private inner class AddButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val intent = Intent(applicationContext, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
