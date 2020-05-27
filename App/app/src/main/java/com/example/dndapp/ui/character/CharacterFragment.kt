package com.example.dndapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var characterViewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_character, container, false)
        initPage(root)
        return root

        //initPage()
    }

    private fun initPage(contentView: ContentView) {
        characterViewPager = vpCharacter
        val fragmentAdapter = activity?.supportFragmentManager?.let { CharacterPagerAdapter(it) }
        characterViewPager.adapter = fragmentAdapter

        tlCharacter.setupWithViewPager(vpCharacter)
    }
}