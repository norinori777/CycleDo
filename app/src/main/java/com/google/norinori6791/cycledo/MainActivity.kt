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
import androidx.lifecycle.ViewModelProviders
import com.google.norinori6791.cycledo.databinding.ActivityMainBinding
import com.google.norinori6791.cycledo.ui.add.AddViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var addViewModel: AddViewModel
    lateinit var databinging: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        addViewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)

        databinging = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(databinging.includeMain.toolbar)
        databinging.includeMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_list, R.id.nav_add, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), databinging.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        databinging.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
