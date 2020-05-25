package com.example.dndapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dndapp.R

class CharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterViewModel =
            ViewModelProvider(this).get(CharacterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_character, container, false)
        val textView: TextView = root.findViewById(R.id.text_character)
        characterViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}