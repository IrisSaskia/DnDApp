package com.example.dndapp.ui.character.description

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity

import com.example.dndapp.R
import com.example.dndapp.MainViewModel
import kotlinx.android.synthetic.main.fragment_character_description.*

/**
 * A simple [Fragment] subclass.
 */
class CharacterDescriptionFragment : Fragment() {
    //private lateinit var viewModel: MainViewModel
    private lateinit var parentActivity: Activity
    private lateinit var activityVarRef: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //viewModel = activity?.run { ViewModelProvider(this).get(MainViewModel::class.java) } ?: throw Exception("Invalid Activity")
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!
        activityVarRef = ((activity as MainActivity?)!!)
    }

    private fun initViewModel() {
        activityVarRef.viewModel.currentDnDCharacter.observe(viewLifecycleOwner, Observer { currentDnDCharacter ->
            if(currentDnDCharacter != null) {
                tvBackgroundName.text = currentDnDCharacter.background
                activityVarRef.viewModel.getBackgroundInfo(currentDnDCharacter.background)
                tvBackgroundInfo.text = activityVarRef.viewModel.backgroundInfo.toString()
                Log.d("backgroundTV", tvBackgroundInfo.text as String)
            }
        })

        activityVarRef.viewModel.backgroundInfo.observe(viewLifecycleOwner, Observer { backgroundInfo ->
            if(backgroundInfo != null) {
                tvBackgroundInfo.text = backgroundInfo
            }
        })
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}