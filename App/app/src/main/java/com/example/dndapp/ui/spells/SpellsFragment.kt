package com.example.dndapp.ui.spells

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

class SpellsFragment : Fragment() {
    private lateinit var spellsFragmentAdapter: SpellsPagerAdapter
    private lateinit var spellsViewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity?)?.checkCurrentFragment()

        spellsFragmentAdapter = SpellsPagerAdapter(this)
        spellsViewPager = view.findViewById(R.id.vpSpells)
        spellsViewPager.adapter = spellsFragmentAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tlSpells)
        TabLayoutMediator(tabLayout, spellsViewPager) { tab, position ->
            if(position == 1) {
                tab.text = getString(R.string.title_spells_known)
            } else {
                tab.text = getString(R.string.title_spells_prepared)
            }
        }.attach()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}