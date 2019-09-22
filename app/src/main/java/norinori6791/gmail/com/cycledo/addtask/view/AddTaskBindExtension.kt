package norinori6791.gmail.com.cycledo.addtask.view

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("showError", "errorText")
fun EditText.setErrorText(showError: Boolean, errorText: String?){
    error = if(showError) errorText else null
}
