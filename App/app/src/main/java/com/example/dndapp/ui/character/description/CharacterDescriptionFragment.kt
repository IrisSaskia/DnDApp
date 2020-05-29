package com.example.dndapp.ui.character.description


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dndapp.MainActivity

import com.example.dndapp.R

/**
 * A simple [Fragment] subclass.
 */
class CharacterDescriptionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity?)?.checkCurrentFragment()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}