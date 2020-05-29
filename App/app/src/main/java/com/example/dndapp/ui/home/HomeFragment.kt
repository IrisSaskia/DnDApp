package com.example.dndapp.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var parentActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Add all supers again or check if needed

        initViews()
        initViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!

        ibDice.setOnClickListener {
            startDiceFragment()
        }
        ibCombat.setOnClickListener {
            startCombatFragment()
        }
    }

    private fun initViewModel() {

    }

    //////////////////////////////////////////////////////////
    //          ALL NAVIGATION                              //
    //////////////////////////////////////////////////////////

    //TODO: misschien nog ervoor zorgen dat je maar 1 functie aanroept en daaraan meegeeft welk fragment

    //Navigation to the dice fragment
    private fun startDiceFragment() {
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_dice)
    }

    //Navigation to the combat fragment
    private fun startCombatFragment() {
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_combat)
    }
}