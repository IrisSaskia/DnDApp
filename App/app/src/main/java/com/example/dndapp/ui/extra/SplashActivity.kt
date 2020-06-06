package com.example.dndapp.ui.extra

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dndapp.MainActivity
import com.example.dndapp.R

class SplashActivity : AppCompatActivity() {
    private lateinit var parentActivity: Activity

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()

        //return inflater.inflate(R.layout.activity_splash,container,false)
    }

    fun initViews() {
        //parentActivity = activity!!

        startTimer()
    }


    fun startTimer() {

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            //startHomeFragment()
            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
/*
    //////////////////////////////////////////////////////////
    //          ALL NAVIGATION                              //
    //////////////////////////////////////////////////////////

    //TODO: misschien nog ervoor zorgen dat je maar 1 functie aanroept en daaraan meegeeft welk fragment

    //Navigation to the dice fragment
    private fun startHomeFragment(){
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
    }*/
}