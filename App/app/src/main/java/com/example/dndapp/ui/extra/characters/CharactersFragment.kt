package com.example.dndapp.ui.extra.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_characters.*

class CharactersFragment : Fragment() {
    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        charactersViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.checkCurrentFragment()

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            newCharacter()
        }
    }

    private fun initViewModel() {

    }

    private fun newCharacter() {
        Toast.makeText(charactersViewModel.getApplication(), R.string.add_character, Toast.LENGTH_SHORT).show()
    }

    /*override fun onPause() {
        super.onPause()

        saveNotes()
    }*/
}