package com.example.dndapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.dndapp.MainActivity
import com.example.dndapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var characterFragmentAdapter: CharacterPagerAdapter
    private lateinit var characterViewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterFragmentAdapter = CharacterPagerAdapter(this)
        characterViewPager = view.findViewById(R.id.vpCharacter)
        characterViewPager.adapter = characterFragmentAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tlCharacter)
        TabLayoutMediator(tabLayout, characterViewPager) { tab, position ->
            if(position == 1) {
                tab.text = getString(R.string.title_character_features_traits)
            } else {
                tab.text = getString(R.string.title_character_description)
            }
        }.attach()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}