package norinori6791.gmail.com.cycledo.Util

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.Toolbar
import norinori6791.gmail.com.cycledo.R

class ToolbarSet(val toolbar: Toolbar) {
    fun setting() : Toolbar{
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(R.string.app_name)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setSubtitle(R.string.toolbar_subtitle)
        toolbar.setSubtitleTextColor(Color.LTGRAY)

        return toolbar
    }
}