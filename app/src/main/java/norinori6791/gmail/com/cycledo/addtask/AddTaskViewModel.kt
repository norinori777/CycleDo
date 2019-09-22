package norinori6791.gmail.com.cycledo.addtask

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.databinding.*
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.richeditor.Utils.getCurrentTime
import norinori6791.gmail.com.cycledo.MainActivity
import norinori6791.gmail.com.cycledo.addtask.view.CategoryAdapter
import norinori6791.gmail.com.cycledo.category.CategoryRepository
import norinori6791.gmail.com.cycledo.model.CategoryItem
import norinori6791.gmail.com.cycledo.model.Task
import norinori6791.gmail.com.cycledo.task.TaskRepository

class AddTaskViewModel(val taskRepository: TaskRepository): BaseObservable() {
    val title = ObservableField<String>("")
    val categorySelectedPosition = ObservableInt(0)
    val content = ObservableField<String>("")
    val titleValid = ObservableBoolean(false)
    val contentValid = ObservableBoolean(false)
    val repository = MutableLiveData<CategoryItem>()

    fun validate(){
        val titleResult: Boolean = title.get().isNullOrBlank()
        val contentResult: Boolean = title.get().isNullOrBlank()
        titleValid.set(titleResult)
        contentValid.set(contentResult)
        if(!titleValid.get() && !contentValid.get()){
            addTask()
        }
    }

    private fun addTask(){
        val task = Task(
            null,
            0,
            title.get().toString(),
            content.get().toString(),
            getCurrentTime().toString(),
            1
        )
        Observable.create<String>{
            try {
                it.onNext("登録")
                it.onComplete()
            } catch(e:Exception){
                it.onError(Throwable("エラーになりました"))
            }

        }
            .subscribeOn(Schedulers.newThread())
            .doOnNext{
                taskRepository.addTask(task)
            }
            .doOnError {
                Log.v("null", "onError")
            }
            .doOnComplete {
                afterAddTask()
            }
            .subscribe({}, {})
    }

    private fun afterAddTask(){
        title.set("")
        content.set("")
    }

    private fun getCategory(): List<CategoryItem>{
        val categoryRepository = CategoryRepository()
        return categoryRepository.getAll()
    }

    fun getCategoryAdapter(context: Context, layout: Int): CategoryAdapter {
        val items = getCategory()
        val adapter = CategoryAdapter(context, layout, items)
        return adapter
    }

    fun onClickCancel(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)

    }
}