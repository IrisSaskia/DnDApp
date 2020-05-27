@file:Suppress("DEPRECATION")

package com.example.dndapp.ui.character

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dndapp.ui.character.description.CharacterDescriptionFragment
import com.example.dndapp.ui.character.features_traits.CharacterFeaturesTraitsFragment

class CharacterPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharacterDescriptionFragment()
            }
            else -> {
                return CharacterFeaturesTraitsFragment()
            }
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Description"
            else -> {
                return "Features and Traits"
            }
        }
    }
}