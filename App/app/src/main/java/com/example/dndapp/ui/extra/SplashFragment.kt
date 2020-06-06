package com.example.dndapp.ui.extra

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.dndapp.R

class SplashFragment : Fragment(){
    private lateinit var parentActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_extra_splash,container,false)
    }

    //////////////////////////////////////////////////////////
    //          ALL NAVIGATION                              //
    //////////////////////////////////////////////////////////

    //TODO: misschien nog ervoor zorgen dat je maar 1 functie aanroept en daaraan meegeeft welk fragment

    //Navigation to the dice fragment
    private fun startHomeFragment(){
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
    }
}