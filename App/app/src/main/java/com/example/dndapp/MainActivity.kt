package com.example.dndapp

import android.app.Activity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //TODO: overal comments toevoegen!!!
    private val mainActivity: Activity = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null

        this.initViews()
    }

    private fun initViews() {
        val hamburgerButton = this.findViewById(R.id.ibMenu) as ImageButton
        hamburgerButton.setOnClickListener {
            showPopUp(hamburgerButton)
        }
    }

    private fun showPopUp(buttonView: View?) {
        val popup = PopupMenu(mainActivity, buttonView)
        val inflater: MenuInflater = popup.menuInflater
        popup.setOnMenuItemClickListener { item ->
            val id = item.itemId

            if (id == R.id.action_my_characters) {
                /*Toast.makeText(this, "My Characters", Toast.LENGTH_LONG).show()*/
                startCharactersFragment()
            }
            if (id == R.id.action_notes) {
                startNotesFragment()
            }
            if (id == R.id.action_settings) {
                /*Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()*/
                startSettingsFragment()
            }

            super.onOptionsItemSelected(item)
        }
        inflater.inflate(R.menu.options_menu, popup.menu)
        popup.show()
    }

    //////////////////////////////////////////////////////////
    //          ALL NAVIGATION                              //
    //////////////////////////////////////////////////////////

    //TODO: misschien nog ervoor zorgen dat je maar 1 functie aanroept en daaraan meegeeft welk fragment

    //Navigation to the characters fragment
    private fun startCharactersFragment() {
        mainActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_characters)
    }

    //Navigation to the notes fragment
    private fun startNotesFragment() {
        mainActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_notes)
    }

    //Navigation to the settings fragment
    private fun startSettingsFragment() {
        mainActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_settings)
    }
}