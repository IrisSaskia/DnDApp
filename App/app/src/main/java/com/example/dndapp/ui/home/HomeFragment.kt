package com.example.dndapp.ui.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    //TODO: add viewmodel in mainactivity???
    private lateinit var parentActivity: Activity
    private lateinit var  viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = activity?.run { ViewModelProvider(this).get(MainViewModel::class.java) }!!//: throw Exception("Invalid Activity")

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Add all supers again or check if needed

        initViews()
        initViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!

        ibDice.setOnClickListener {
            startDiceFragment()
        }
        ibCombat.setOnClickListener {
            startCombatFragment()
        }
    }

    private fun initViewModel() {
        viewModel.currentDnDCharacter.observe(viewLifecycleOwner, Observer { currentDnDCharacter ->
            if(currentDnDCharacter != null) {
                tvName.text = currentDnDCharacter.name
                Log.d("name textview", tvName.text as String)
                Log.d("name database", currentDnDCharacter.name)
                tvLevel.text = getString(R.string.level_indicator, currentDnDCharacter.level.toString())

                var charRace: String
                if(currentDnDCharacter.subRace != "" && currentDnDCharacter.subRace != null) {
                    charRace = currentDnDCharacter.subRace + " " + currentDnDCharacter.cclass
                } else {
                    charRace = currentDnDCharacter.race + " " + currentDnDCharacter.cclass
                }
                tvCharacterClassRace.text = charRace

                viewModel.getRaceSpeed(currentDnDCharacter.race)
                viewModel.getHPInfo(currentDnDCharacter.cclass)
            }
        })

        

        viewModel.raceSpeed.observe(viewLifecycleOwner, Observer { raceSpeed ->
            if(raceSpeed != null) {
                tvSpeedNumber.text = raceSpeed.toString()
            }
        })

        viewModel.charHP.observe(viewLifecycleOwner, Observer { charHP ->
            if(charHP != null) {
                tvHP.text = getString(R.string.hp_text, charHP.toString())
            }
        })

        viewModel.currentStrength.observe(viewLifecycleOwner, Observer { currentStrength ->
            if(currentStrength != null) {
                tvStatNumberStrength.text = currentStrength.stat.toString()
                tvModNumberStrength.text = getString(R.string.save_mod_placeholder, currentStrength.modifier.toString())
                tvSaveNumberStrength.text = getString(R.string.save_mod_placeholder, currentStrength.save.toString())
            }
        })
        viewModel.currentDexterity.observe(viewLifecycleOwner, Observer { currentDexterity ->
            if(currentDexterity != null) {
                tvStatNumberDexterity.text = currentDexterity.stat.toString()
                tvModNumberDexterity.text = getString(R.string.save_mod_placeholder, currentDexterity.modifier.toString())
                tvSaveNumberDexterity.text = getString(R.string.save_mod_placeholder, currentDexterity.save.toString())

                tvInitiativeNumber.text = currentDexterity.modifier.toString()
            }
        })
        viewModel.currentIntelligence.observe(viewLifecycleOwner, Observer { currentIntelligence ->
            if(currentIntelligence != null) {
                tvStatNumberIntelligence.text = currentIntelligence.stat.toString()
                tvModNumberIntelligence.text = getString(R.string.save_mod_placeholder, currentIntelligence.modifier.toString())
                tvSaveNumberIntelligence.text = getString(R.string.save_mod_placeholder, currentIntelligence.save.toString())
            }
        })
        viewModel.currentWisdom.observe(viewLifecycleOwner, Observer { currentWisdom ->
            if(currentWisdom != null) {
                tvStatNumberWisdom.text = currentWisdom.stat.toString()
                tvModNumberWisdom.text = getString(R.string.save_mod_placeholder, currentWisdom.modifier.toString())
                tvSaveNumberWisdom.text = getString(R.string.save_mod_placeholder, currentWisdom.save.toString())
            }
        })
        viewModel.currentCharisma.observe(viewLifecycleOwner, Observer { currentCharisma ->
            if(currentCharisma != null) {
                tvStatNumberCharisma.text = currentCharisma.stat.toString()
                tvModNumberCharisma.text = getString(R.string.save_mod_placeholder, currentCharisma.modifier.toString())
                tvSaveNumberCharisma.text = getString(R.string.save_mod_placeholder, currentCharisma.save.toString())
            }
        })
        viewModel.currentConstitution.observe(viewLifecycleOwner, Observer { currentConstitution ->
            if(currentConstitution != null) {
                tvStatNumberConstitution.text = currentConstitution.stat.toString()
                tvModNumberConstitution.text = getString(R.string.save_mod_placeholder, currentConstitution.modifier.toString())
                tvSaveNumberConstitution.text = getString(R.string.save_mod_placeholder, currentConstitution.save.toString())
            }
        })
    }

    public fun levelUp() {

    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }

    //////////////////////////////////////////////////////////
    //          ALL NAVIGATION                              //
    //////////////////////////////////////////////////////////

    //TODO: misschien nog ervoor zorgen dat je maar 1 functie aanroept en daaraan meegeeft welk fragment

    //Navigation to the dice fragment
    private fun startDiceFragment() {
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_dice)
    }

    //Navigation to the combat fragment
    private fun startCombatFragment() {
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_extra_combat)
    }
}