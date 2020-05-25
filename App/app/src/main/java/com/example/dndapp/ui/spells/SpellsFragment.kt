package com.example.dndapp.ui.spells

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.R

class SpellsFragment : Fragment() {

    private lateinit var spellsViewModel: SpellsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spellsViewModel =
            ViewModelProvider(this).get(SpellsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_spells, container, false)
        val textView: TextView = root.findViewById(R.id.text_spells)
        spellsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}