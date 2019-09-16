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
import norinori6791.gmail.com.cycledo.model.Item
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

        // リサイクルビューにレイアウトマネージャーをセット
//        val menuTask =findViewById<RecyclerView>(R.id.lo_recycleview)
//        val layout =LinearLayoutManager(applicationContext)
//        menuTask.layoutManager = layout
        // アダプターにデータをセット
//        val adapter = RecycleListAdpater(items)
//        menuTask.adapter = adapter

        val adapter = MyAdpater(this)
        adapter.items = listItems
        binding.loRecycleview.layoutManager = LinearLayoutManager(this)
        binding.loRecycleview.adapter = adapter


        // タスク追加リスナーをセット
        val btadd = findViewById<FloatingActionButton>(R.id.btStartAdd)
        val listener = AddButtonClickListener()
        btadd.setOnClickListener(listener)


//        val disposable = CompositeDisposable()
//
//        disposable.addAll(
//            viewModel.getItems().subscribe(),
//            viewModel.observeItems().observeOn(AndroidSchedulers.mainThread())
//                .subscribeBy(
//                    onNext=adapter::setItems,
//                    onError=Timber::e
//                )
//            )
//        )
    }
//    private inner class RecyclerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        /**
//         * リスト1行分中でメニュー名を表示する画面部品。
//         */
//        val tvTaskTitle: TextView
//        val tvTaskCycle: TextView
//        val tvTaskStart: TextView
//
//
//        init {
//            //引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを取得。
//            tvTaskTitle = itemView.findViewById<TextView>(R.id.tv_task_title)
//            tvTaskCycle = itemView.findViewById<TextView>(R.id.tv_cycle)
//            tvTaskStart = itemView.findViewById<TextView>(R.id.tv_start)
//        }
//    }
    private inner class MyHolder(val binding: CardviewTaskBinding): RecyclerView.ViewHolder(binding.root)

//    private inner class RecycleListAdpater(private val _listData: MutableList<Item>) : RecyclerView.Adapter<RecyclerListViewHolder>(){
    private inner class MyAdpater(context: Context) : RecyclerView.Adapter<MyHolder>(){
        var items: List<Item> = emptyList()
        private val inflater = LayoutInflater.from(context)

        override fun getItemCount(): Int = items.size

 //       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val binding: CardviewTaskBinding = DataBindingUtil.inflate(inflater, R.layout.cardview_task, parent, false)
            binding.cardview.setOnClickListener(ItemClickListener(binding))
            return MyHolder(binding)
//                val inflater = LayoutInflater.from(applicationContext)
//            val view = inflater.inflate(R.layout.cardview_task, parent, false)
//            view.setOnClickListener(ItemClickListener())
//            val holder = RecyclerListViewHolder(view)
//            return holder
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.binding.item = items[position]
//            holder.binding.executePendingBindings()

//            val item = _listData[position]
//            val menuTitle = item.title
//            val menuCycle = item.cycle
//            val menuStartdate = item.startdate
//            holder.tvTaskTitle.text = menuTitle
//            holder.tvTaskCycle.text = menuCycle.toString()
//            holder.tvTaskStart.text = menuStartdate
        }
    }

    inner class ItemClickListener(val binding: CardviewTaskBinding): View.OnClickListener{
        override fun onClick(view: View){
//            val menuTitle = view.findViewById<TextView>(R.id.tv_task_title)
//            val menuCycle = view.findViewById<TextView>(R.id.tv_cycle)
//            val menuStartdate = view.findViewById<TextView>(R.id.tv_start)
//            val menuContent = view.findViewById<EditText>(R.id.ed_content)

            val item = Item(
                binding.tvId.text.toString().toInt(),
                1,
                binding.tvTaskTitle.text.toString(),
                binding.edContent.text.toString(),
                binding.tvStart.text.toString(),
                binding.tvCycle.text.toString().toInt()
            )

            val intent = Intent(applicationContext, ShowTaskActivity::class.java)
            intent.putExtra("item", item)
//            intent.putExtra("title", menuTitle.text)
//            intent.putExtra("cycle", menuCycle.text)
//            intent.putExtra("startdate", menuStartdate.text)
//            intent.putExtra("content", menuContent.text)

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
