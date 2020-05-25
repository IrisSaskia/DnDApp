package com.example.dndapp

import android.app.Activity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val mainActivity: Activity = this@MainActivity
        ?: throw RuntimeException("getCurrentSite() can only be called when an activity is in the resumed state")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_character, R.id.navigation_bag, R.id.navigation_spells
            )
        )*/
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null

        initViews()
    }

    fun initViews() {
        val hamburgerButton = this.findViewById(R.id.ibMenu) as ImageButton
        hamburgerButton.setOnClickListener {
            showPopUp(hamburgerButton)
        }
    }

    private fun showPopUp(buttonView: View?) {
        val popup = PopupMenu(mainActivity, buttonView)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options_menu, popup.menu)
        popup.show()
    }
}