package com.example.dndapp.ui.spells

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dndapp.ui.spells.known.SpellsKnownFragment
import com.example.dndapp.ui.spells.prepared.SpellsPreparedFragment

class SpellsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SpellsPreparedFragment()
            1 -> return SpellsKnownFragment()
        }
        return SpellsPreparedFragment()
    }
}