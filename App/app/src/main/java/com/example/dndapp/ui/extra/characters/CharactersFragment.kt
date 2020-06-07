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
import android.widget.Toast
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
    private var backgroundNames = mutableListOf<String>()
    private lateinit var layoutDialog: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        layoutDialog = inflater.inflate(R.layout.add_character_dialog, container, false)

        return inflater.inflate(R.layout.fragment_extra_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity?)?.checkCurrentFragment()

        initViews()
        initViewModel()
        observeViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!
        backgroundSpinner = layoutDialog.findViewById(R.id.spinnerBackground)

        rvCharacters.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvCharacters.adapter = characterAdapter
        rvCharacters.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

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

    //Button opens dialog for new character creation
    private fun newCharacter() {
        Toast.makeText(viewModel.getApplication(), R.string.add_character, Toast.LENGTH_SHORT).show()
        val newCharacterDialog = LayoutInflater.from(context).inflate(R.layout.add_character_dialog, null)

        val newCharacterBuilder = AlertDialog.Builder(context, R.style.DialogTheme)
            .setView(newCharacterDialog)
            .setTitle("MAAKT GE EENS EEN NIEUW POPSKE AN MAN")


        val alertDialog = newCharacterBuilder.show()

        initSpinner()

        alertDialog.show()
    }

    private fun initSpinner() {
        viewModel.getAllBackgrounds()

        /*viewModel.backgrounds.observe(this, Observer {backgrounds ->
            Log.d("result viewmodel", backgrounds[0].name)
        })*/
        //backgroundSpinner = parentActivity.findViewById<Spinner>(R.id.spinnerBackground)
        //if(spinnerBackground != null) {
//            viewModel.getBackgroundNames()
            //viewModel.backgrounds.observe(this, Observer {backgrounds ->
                /*viewModel.backgrounds.forEachIndexed {index, value ->
                    backgroundNames.plusAssign(value.name)
                    Log.d("naam", backgroundNames[index])
                    Log.d("naamview", value.name)
                }*/

        Log.d("halloooo??", "okayyy")
        /*for(background in viewModel.backgrounds) {
            backgroundNames.add(background.name)
            Log.d("naam", backgroundNames[1])
            Log.d("naamview", background.name)
            Log.d("gebeurt dit ooit", "ja")
        }*/

        /*viewModel.backgrounds.forEach {
            backgroundNames.plusAssign(it.name)

            Log.d("naam", backgroundNames[1])
            Log.d("naamview", it.name)
            Log.d("gebeurt dit ooit", "ja")
        }*/

        if(viewModel.backgrounds != null) {
            Log.d("please", "werk")
            Log.d("werkt dit", viewModel.backgrounds.size.toString())
        }

            //})
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, backgroundNames)
            backgroundSpinner.adapter = adapter
        /*} else {
            Log.d("null", "jup toch wel")
        }*/
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }

    private fun onCharacterClick(dndCharacter: DnDCharacter) {
        //Snackbar.make(rvCharacters, "Dit character is: ${dndCharacter.name}", Snackbar.LENGTH_LONG).show()
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
}