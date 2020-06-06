package com.example.dndapp.ui.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.stats.Stat
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    //private lateinit var viewModel: MainViewModel
    //TODO: add viewmodel in mainactivity???
    private lateinit var parentActivity: Activity
    private lateinit var activityVarRef: MainActivity

    //lateinit var loadedCharacter: DnDCharacter

    /*var arrayOfStats: Array<Stat?> = arrayOfNulls(6)

    var arrayOfTVStat: Array<TextView?> = arrayOfNulls(6)
    var arrayOfTVMod: Array<TextView?> = arrayOfNulls(6)
    var arrayOfTVSave: Array<TextView?> = arrayOfNulls(6)*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //viewModel = activity?.run { ViewModelProvider(this).get(MainViewModel::class.java) }!!//: throw Exception("Invalid Activity")

            return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Add all supers again or check if needed

        initViews()
        initViewModel()
        initStatArrays()
    }

    private fun initViews() {
        //Log.d("ID vanuit homefragment:", viewModel.currentCharacterID.value.toString())
        parentActivity = activity!!
        activityVarRef = ((activity as MainActivity?)!!)

        ibDice.setOnClickListener {
            startDiceFragment()
        }
        ibCombat.setOnClickListener {
            startCombatFragment()
        }

        //arrayOfTVStat[0] = tvStatNumberStrength
    }

    private fun initStatArrays() {
        /*activityVarRef.viewModel.currentStrength.observe(viewLifecycleOwner, Observer { currentStrength ->
            if(currentStrength != null) {
                arrayOfStats[0] = currentStrength
                Log.d("strength", "is hier")
            }
        })
        activityVarRef.viewModel.currentDexterity.observe(viewLifecycleOwner, Observer { currentDexterity ->
            if(currentDexterity != null) {
                arrayOfStats[0] = currentDexterity
            }
        })
        activityVarRef.viewModel.currentConstitution.observe(viewLifecycleOwner, Observer { currentConstitution ->
            if(currentConstitution != null) {
                arrayOfStats[0] = currentConstitution
            }
        })
        activityVarRef.viewModel.currentIntelligence.observe(viewLifecycleOwner, Observer { currentIntelligence ->
            if(currentIntelligence != null) {
                arrayOfStats[0] = currentIntelligence
            }
        })
        activityVarRef.viewModel.currentWisdom.observe(viewLifecycleOwner, Observer { currentWisdom ->
            if(currentWisdom != null) {
                arrayOfStats[0] = currentWisdom
            }
        })
        activityVarRef.viewModel.currentCharisma.observe(viewLifecycleOwner, Observer { currentCharisma ->
            if(currentCharisma != null) {
                arrayOfStats[0] = currentCharisma
            }
        })

        arrayOfTVStat[0] = tvStatNumberStrength
        arrayOfTVStat[1] = tvStatNumberDexterity
        arrayOfTVStat[2] = tvStatNumberConstitution
        arrayOfTVStat[3] = tvStatNumberIntelligence
        arrayOfTVStat[4] = tvStatNumberWisdom
        arrayOfTVStat[5] = tvStatNumberCharisma


        arrayOfTVMod[0] = tvModNumberStrength
        arrayOfTVMod[1] = tvModNumberDexterity
        arrayOfTVMod[2] = tvModNumberConstitution
        arrayOfTVMod[3] = tvModNumberIntelligence
        arrayOfTVMod[4] = tvModNumberWisdom
        arrayOfTVMod[5] = tvModNumberCharisma*/
    }

    private fun initViewModel() {
        activityVarRef.viewModel.currentCharacterID.observe(viewLifecycleOwner, Observer { currentCharacterID ->
            if(currentCharacterID != null) {
                activityVarRef.viewModel.loadAllData(currentCharacterID)
                //loadedCharacter = viewModel.currentDnDCharacter.value!!
            }
        })

        activityVarRef.viewModel.currentDnDCharacter.observe(viewLifecycleOwner, Observer { currentDnDCharacter ->
            if(currentDnDCharacter != null) {
                tvName.text = currentDnDCharacter.name
                Log.d("name textview", tvName.text as String)
                Log.d("name database", currentDnDCharacter.name)
                tvLevel.text = getString(R.string.level_indicator, currentDnDCharacter.level.toString())
                //TODO: Add class
                var charRace: String
                if(currentDnDCharacter.subRace != "" && currentDnDCharacter.subRace != null) {
                    charRace = currentDnDCharacter.subRace + " " + currentDnDCharacter.race
                } else {
                    charRace = currentDnDCharacter.race
                }
                tvClassRace.text = charRace
                //TODO: Add HP

                //viewModel.loadAllData(currentDnDCharacter.id!!.toInt())
            }
        })

        /*for (i in arrayOfStats.size until arrayOfStats.size) {
            arrayOfTVStat[i]!!.text = arrayOfStats[i]!!.stat.toString()
        }*/
        activityVarRef.viewModel.currentStrength.observe(viewLifecycleOwner, Observer { currentStrength ->
            if(currentStrength != null) {
                tvStatNumberStrength.text = currentStrength.stat.toString()
                tvModNumberStrength.text = getString(R.string.save_mod_placeholder, currentStrength.modifier.toString())
                tvSaveNumberStrength.text = getString(R.string.save_mod_placeholder, currentStrength.save.toString())
            }
        })
        activityVarRef.viewModel.currentDexterity.observe(viewLifecycleOwner, Observer { currentDexterity ->
            if(currentDexterity != null) {
                tvStatNumberDexterity.text = currentDexterity.stat.toString()
                tvModNumberDexterity.text = getString(R.string.save_mod_placeholder, currentDexterity.modifier.toString())
                tvSaveNumberDexterity.text = getString(R.string.save_mod_placeholder, currentDexterity.save.toString())
            }
        })
        activityVarRef.viewModel.currentIntelligence.observe(viewLifecycleOwner, Observer { currentIntelligence ->
            if(currentIntelligence != null) {
                tvStatNumberIntelligence.text = currentIntelligence.stat.toString()
                tvModNumberIntelligence.text = getString(R.string.save_mod_placeholder, currentIntelligence.modifier.toString())
                tvSaveNumberIntelligence.text = getString(R.string.save_mod_placeholder, currentIntelligence.save.toString())
            }
        })
        activityVarRef.viewModel.currentWisdom.observe(viewLifecycleOwner, Observer { currentWisdom ->
            if(currentWisdom != null) {
                tvStatNumberWisdom.text = currentWisdom.stat.toString()
                tvModNumberWisdom.text = getString(R.string.save_mod_placeholder, currentWisdom.modifier.toString())
                tvSaveNumberWisdom.text = getString(R.string.save_mod_placeholder, currentWisdom.save.toString())
            }
        })
        activityVarRef.viewModel.currentCharisma.observe(viewLifecycleOwner, Observer { currentCharisma ->
            if(currentCharisma != null) {
                tvStatNumberCharisma.text = currentCharisma.stat.toString()
                tvModNumberCharisma.text = getString(R.string.save_mod_placeholder, currentCharisma.modifier.toString())
                tvSaveNumberCharisma.text = getString(R.string.save_mod_placeholder, currentCharisma.save.toString())
            }
        })
        activityVarRef.viewModel.currentConstitution.observe(viewLifecycleOwner, Observer { currentConstitution ->
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