package com.example.dndapp.ui.character.description


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity

import com.example.dndapp.R
import com.example.dndapp.ui.character.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_description.*

/**
 * A simple [Fragment] subclass.
 */
class CharacterDescriptionFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    private fun initViews() {
    }

    private fun initViewModel() {
        characterViewModel.currentDnDCharacter.observe(viewLifecycleOwner, Observer { currentDnDCharacter ->
            if(currentDnDCharacter != null) {
                tvBackgroundName.text = currentDnDCharacter.background
            }
        })
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}