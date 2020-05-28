package com.example.dndapp.ui.character

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dndapp.ui.character.description.CharacterDescriptionFragment
import com.example.dndapp.ui.character.features_traits.CharacterFeaturesTraitsFragment

class CharacterPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CharacterDescriptionFragment()
            1 -> return CharacterFeaturesTraitsFragment()
        }
        return CharacterDescriptionFragment()
    }
}