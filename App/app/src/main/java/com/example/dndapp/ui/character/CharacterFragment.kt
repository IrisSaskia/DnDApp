package com.example.dndapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.dndapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var characterFragmentAdapter: CharacterPagerAdapter
    private lateinit var characterViewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_character, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        characterFragmentAdapter = CharacterPagerAdapter(this)
        characterViewPager = view.findViewById(R.id.vpCharacter)
        characterViewPager.adapter = characterFragmentAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tlCharacter)
        TabLayoutMediator(tabLayout, characterViewPager) { tab, position ->
            if(position == 1) {
                tab.text = "Features and Traits"
            } else {
                tab.text = "Description"
            }
        }.attach()
    }
}