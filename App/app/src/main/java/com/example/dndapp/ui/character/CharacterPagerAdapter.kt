@file:Suppress("DEPRECATION")

package com.example.dndapp.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dndapp.ui.character.description.CharacterDescriptionFragment
import com.example.dndapp.ui.character.features_traits.CharacterFeaturesTraitsFragment

private const val ARG_OBJECT = "object"

class CharacterPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CharacterDescriptionFragment()
            1 -> return CharacterFeaturesTraitsFragment()
        }
        return CharacterDescriptionFragment()
        /*val fragment = CharacterDescriptionFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment*/
    }
}