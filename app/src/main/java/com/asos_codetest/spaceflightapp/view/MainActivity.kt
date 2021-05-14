package com.asos_codetest.spaceflightapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.asos_codetest.spaceflightapp.R
import com.asos_codetest.spaceflightapp.view.dialog.FilterDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    private var filterDialog : FilterDialog?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // This line is only necessary if using the default action bar.
        setupActionBarWithNavController(navController, appBarConfiguration)
      //  loadFragment()

    }
    override fun onSupportNavigateUp(): Boolean {
        // Handle the back button event and return true to override
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.filter_item,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filterIcon){
           filterDialog = showFilterDialog()
        }
        return true
    }

    private fun showFilterDialog() : FilterDialog {
        return FilterDialog.instance.also { dialog->
            supportFragmentManager.let { manager->
                dialog.show(manager, FilterDialog::class.java.name)
            }
        }
    }
}