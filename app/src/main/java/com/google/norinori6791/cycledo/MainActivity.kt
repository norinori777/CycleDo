package com.google.norinori6791.cycledo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.norinori6791.cycledo.databinding.ActivityMainBinding
import com.google.norinori6791.cycledo.ui.edit.EditFragment
import com.google.norinori6791.cycledo.ui.edit.EditViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var addViewModel: EditViewModel
    private lateinit var dataBinging: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        addViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        dataBinging = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)


        setSupportActionBar(dataBinging.includeMain.toolbar)
        dataBinging.includeMain.fab.setOnClickListener { view ->
            navController.navigate(R.id.nav_edit)
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_list, R.id.nav_edit, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), dataBinging.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        dataBinging.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
