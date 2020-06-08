package com.example.dndapp.ui.extra.characters

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import com.example.dndapp.model.CharacterAdapter
import com.example.dndapp.model.DnDCharacter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_extra_characters.*

class CharactersFragment : Fragment() {
    private lateinit var parentActivity: Activity
    private val dndCharacters = arrayListOf<DnDCharacter>()
    private val characterAdapter = CharacterAdapter(dndCharacters) { dndCharacter -> onCharacterClick(dndCharacter) }
    private lateinit var viewModel: MainViewModel
    private lateinit var backgroundSpinner: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
        observeViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!
        /*backgroundSpinner = layoutDialog.findViewById(R.id.spinnerBackground)*/

        rvCharacters.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvCharacters.adapter = characterAdapter
        rvCharacters.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //Make the floating action button call the newCharacter() function
        fab.setOnClickListener {
            newCharacter()
        }
    }

    private fun initViewModel() {

    }

    private fun observeViewModel() {
        viewModel.dndCharacters.observe(this, Observer { dndCharacters ->
            this.dndCharacters.clear()
            this.dndCharacters.addAll(dndCharacters)
            characterAdapter.notifyDataSetChanged()
        })
    }

    //////////////////////////////////////////////////
    //Button opens dialog for new character creation//
    //////////////////////////////////////////////////
    private fun newCharacter() {
        //Toast.makeText(viewModel.getApplication(), R.string.add_character, Toast.LENGTH_SHORT).show()
        val newCharacterDialog = LayoutInflater.from(context).inflate(R.layout.add_character_dialog, null)

        initBackgroundSpinner(newCharacterDialog)

        val newCharacterBuilder = AlertDialog.Builder(context, R.style.DialogTheme)
            .setView(newCharacterDialog)
            .setTitle(R.string.add_character)


        val alertDialog = newCharacterBuilder.show()

        alertDialog.show()
    }

    private fun initRaceSpinner(alertDialog: View) {
        val raceSpinner: Spinner = alertDialog.findViewById(R.id.spinnerBackground)

        viewModel.getRaceNames().observe(this, Observer {raceNames ->
            Log.d("grootte", raceNames.size.toString())
            raceNames.forEachIndexed{index, value ->
                Log.d("kan ik het ophalen", value)
            }
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, raceNames)
            raceSpinner.adapter = adapter
        })
    }

    private fun initBackgroundSpinner(alertDialog: View) {
        val backgroundSpinner: Spinner = alertDialog.findViewById(R.id.spinnerBackground)

        viewModel.getBackgroundNames().observe(this, Observer {backgroundNames ->
            Log.d("grootte", backgroundNames.size.toString())
            backgroundNames.forEachIndexed{index, value ->
                Log.d("kan ik het ophalen", value)
            }
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, backgroundNames)
            backgroundSpinner.adapter = adapter
        })
    }

    //////////////////////////////////////////////////////////
    //This function handles the loading of another character//
    //////////////////////////////////////////////////////////
    private fun onCharacterClick(dndCharacter: DnDCharacter) {
        viewModel.currentDnDCharacter.observe(this, Observer {currentDnDCharacter ->
            if (currentDnDCharacter != null) {
                if(currentDnDCharacter.id == dndCharacter.id) {
                    Snackbar.make(rvCharacters, R.string.already_selected, Snackbar.LENGTH_SHORT).show()
                } else {
                    viewModel.changeCurrentCharacter(dndCharacter.id!!.toInt(), currentDnDCharacter.id!!.toInt())
                    startHomeFragment()
                }
            } else {
                Snackbar.make(rvCharacters, R.string.error, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun startHomeFragment(){
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
    }


    //This gets executed when the fragment is being left
    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}