package com.example.dndapp.ui.extra.dice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity
import com.example.dndapp.R

class DiceFragment : Fragment() {
    private lateinit var diceViewModel: DiceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        diceViewModel = ViewModelProvider(this).get(DiceViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_dice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.checkCurrentFragment()

        initViews()
        initViewModel()
    }

    private fun initViews() {
        /*fab.setOnClickListener {
            newCharacter()
        }*/
    }

    private fun initViewModel() {

    }

    override fun onPause() {
        super.onPause()

        (activity as MainActivity?)?.checkCurrentFragment()
    }
}