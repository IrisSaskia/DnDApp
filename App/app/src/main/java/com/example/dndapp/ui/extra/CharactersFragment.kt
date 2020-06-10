package com.example.dndapp.ui.extra

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import com.example.dndapp.model.stats.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_extra_characters.*


class CharactersFragment : Fragment() {
    private lateinit var parentActivity: Activity
    private val dndCharacters = arrayListOf<DnDCharacter>()
    private val characterAdapter = CharacterAdapter(dndCharacters) { dndCharacter -> onCharacterClick(dndCharacter) }
    private lateinit var viewModel: MainViewModel

    //The chosen options for the character making process
    private lateinit var newCharacterRace: String
    private lateinit var newCharacterSubrace: String
    private lateinit var newCharacterCClass: String
    private lateinit var newCharacterBackground: String
    private var newCharacterStrength: Int = 0
    private var newCharacterDexterity: Int = 0
    private var newCharacterIntelligence: Int = 0
    private var newCharacterWisdom: Int = 0
    private var newCharacterCharisma: Int = 0
    private var newCharacterConstitution: Int = 0
    private lateinit var newCharacterName: String
    private lateinit var newCharacterAlignment: String
    private lateinit var newCharacterGender: String


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

        //Making the recyclerview for the list of all characters
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

    //This function makes an alert dialog
    private fun makeAlert(layout: Int, title: Int): Pair<AlertDialog, View> {
        val dialogView = LayoutInflater.from(context).inflate(layout, null)
        val newAlertBuilder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setView(dialogView)
            .setTitle(title)

        return Pair(newAlertBuilder.show(), dialogView)
    }
    //This function gets the selected value of a spinner
    private fun getSelectedCharacterOptions(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    //////////////////////////////////////////////////
    //Button opens dialog for new character creation//
    //////////////////////////////////////////////////
    private fun newCharacter() {
        val (alertDialog, alertView) = makeAlert(R.layout.add_character_dialog, R.string.add_character)

        val exitButton: Button = alertDialog.findViewById(R.id.btnCancel)
        val nextButton: Button = alertDialog.findViewById(R.id.btnNext)

        val raceSpinner: Spinner = alertDialog.findViewById(R.id.spinnerRace)
        val cclassSpinner: Spinner = alertDialog.findViewById(R.id.spinnerCClass)
        val backgroundSpinner: Spinner = alertDialog.findViewById(R.id.spinnerBackground)

        initRaceSpinner(raceSpinner)
        initCClassSpinner(cclassSpinner)
        initBackgroundSpinner(backgroundSpinner)

        exitButton.setOnClickListener {
            alertDialog.dismiss()
        }

        nextButton.setOnClickListener {
            //TODO: Save the currently chosen options!!

            if(raceSpinner.count != 0 &&
                cclassSpinner.count != 0 &&
                    backgroundSpinner.count != 0) {
                newCharacterRace = getSelectedCharacterOptions(raceSpinner)
                newCharacterCClass = getSelectedCharacterOptions(cclassSpinner)
                newCharacterBackground = getSelectedCharacterOptions(backgroundSpinner)

                Log.d("race", newCharacterRace)
                Log.d("cclass", newCharacterCClass)
                Log.d("background", newCharacterBackground)

                alertDialog.dismiss()
                selectSubRace()
            } else {
                //TODO: make string value
                Toast.makeText(context, "Wacht even op het laden", Toast.LENGTH_LONG).show()
            }
        }

        alertDialog.show()
    }

    //This function fills the race spinner with all race names gotten from the api
    private fun initRaceSpinner(raceSpinner: Spinner) {
        viewModel.getRaceNames().observe(this, Observer {raceNames ->
            Log.d("grootte", raceNames.size.toString())
            raceNames.forEachIndexed{ _, value ->
                Log.d("kan ik het ophalen", value)
            }
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, raceNames)
            raceSpinner.adapter = adapter
        })
    }

    //This function fills the cclass spinner with all cclass names gotten from the api
    private fun initCClassSpinner(cclassSpinner: Spinner) {
        viewModel.getCClassNames().observe(this, Observer {cclassNames ->
            Log.d("grootte", cclassNames.size.toString())
            cclassNames.forEachIndexed{ _, value ->
                Log.d("kan ik het ophalen", value)
            }
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, cclassNames)
            cclassSpinner.adapter = adapter
        })
    }

    //This function fills the background spinner with all background names gotten from the api
    private fun initBackgroundSpinner(backgroundSpinner: Spinner) {
        viewModel.getBackgroundNames().observe(this, Observer {backgroundNames ->
            Log.d("grootte", backgroundNames.size.toString())
            backgroundNames.forEachIndexed{ _, value ->
                Log.d("kan ik het ophalen", value)
            }
            val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, backgroundNames)
            backgroundSpinner.adapter = adapter
        })
    }


    ///////////////////////////////////////////////////////////
    //This function is for the dialog for selecting a subrace//
    ///////////////////////////////////////////////////////////
    private fun selectSubRace() {
        viewModel.getSubraceNames(newCharacterRace).observe(this, Observer {subraceNames ->
            if(subraceNames != null && subraceNames.isNotEmpty()) {
                //TODO: hij opent soms dubbel?
                val (alertDialog, alertView) = makeAlert(R.layout.add_character_dialog_subrace, R.string.select_subrace)

                val subraceSpinner: Spinner = alertDialog.findViewById(R.id.spinnerSubrace)
                val adapter = ArrayAdapter(parentActivity, android.R.layout.simple_spinner_item, subraceNames)
                subraceSpinner.adapter = adapter

                val previousButton: Button = alertDialog.findViewById(R.id.btnSubracePrevious)
                val nextButton: Button = alertDialog.findViewById(R.id.btnSubraceNext)

                previousButton.setOnClickListener {
                    alertDialog.dismiss()
                    newCharacter()
                }

                nextButton.setOnClickListener {
                    //TODO: Save the currently chosen options!!
                    if(subraceSpinner.count != 0) {
                        newCharacterSubrace = getSelectedCharacterOptions(subraceSpinner)

                        Log.d("subrace", newCharacterSubrace)

                        alertDialog.dismiss()
                        selectStats()
                    } else {
                        //TODO: make string value
                        Toast.makeText(context, "Wacht even op het laden", Toast.LENGTH_LONG).show()
                    }

                    alertDialog.dismiss()
                }

                alertDialog.show()
            } else {
                selectStats()
            }
        })
    }

    //////////////////////////////////////////////////////////////////
    //This function handles the alert dialog for the stats selection//
    //////////////////////////////////////////////////////////////////
    private fun selectStats() {
        val (alertDialog, alertView) = makeAlert(R.layout.add_character_dialog_stats, R.string.select_stats)

        val previousButton: Button = alertDialog.findViewById(R.id.btnStatPrevious)
        val nextButton: Button = alertDialog.findViewById(R.id.btnStatNext)

        val strengthInputField = alertDialog.findViewById<EditText>(R.id.etStrengthSelect)
        val dexterityInputField = alertDialog.findViewById<EditText>(R.id.etDexteritySelect)
        val intelligenceInputField = alertDialog.findViewById<EditText>(R.id.etIntelligenceSelect)
        val wisdomInputField = alertDialog.findViewById<EditText>(R.id.etWisdomSelect)
        val charismaInputField = alertDialog.findViewById<EditText>(R.id.etCharismaSelect)
        val constitutionInputField = alertDialog.findViewById<EditText>(R.id.etConstitutionSelect)

        /*text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (your_string.equals(s.toString())) {
                    //do something
                } else {
                    //do something
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })*/

        /*val strengthMin = alertDialog.findViewById<ImageButton>(R.id.ibStrMin)
        val strengthPlus = alertDialog.findViewById<ImageButton>(R.id.ibStrPlus)

        val dexterityMin = alertDialog.findViewById<ImageButton>(R.id.ibDexMin)
        val dexterityPlus = alertDialog.findViewById<ImageButton>(R.id.ibDexPlus)

        val intelligenceMin = alertDialog.findViewById<ImageButton>(R.id.ibIntMin)
        val intelligencePlus = alertDialog.findViewById<ImageButton>(R.id.ibIntPlus)

        val wisdomMin = alertDialog.findViewById<ImageButton>(R.id.ibWisMin)
        val wisdomPlus = alertDialog.findViewById<ImageButton>(R.id.ibWisPlus)

        val charismaMin = alertDialog.findViewById<ImageButton>(R.id.ibCharMin)
        val charismaPlus = alertDialog.findViewById<ImageButton>(R.id.ibCharPlus)

        val constitutionMin = alertDialog.findViewById<ImageButton>(R.id.ibConMin)
        val constitutionPlus = alertDialog.findViewById<ImageButton>(R.id.ibConPlus)*/

        previousButton.setOnClickListener {
            alertDialog.dismiss()
            selectSubRace()
        }

        nextButton.setOnClickListener {
            //TODO: Save the currently chosen options!!
            if( strengthInputField.text.toString() != "" &&
                dexterityInputField.text.toString() != "" &&
                intelligenceInputField.text.toString() != "" &&
                wisdomInputField.text.toString() != "" &&
                charismaInputField.text.toString() != "" &&
                constitutionInputField.text.toString() != "")
            {
                newCharacterStrength = strengthInputField.text.toString().toInt()
                newCharacterDexterity = dexterityInputField.text.toString().toInt()
                newCharacterIntelligence = intelligenceInputField.text.toString().toInt()
                newCharacterWisdom = wisdomInputField.text.toString().toInt()
                newCharacterCharisma = charismaInputField.text.toString().toInt()
                newCharacterConstitution = constitutionInputField.text.toString().toInt()
                Log.d("str", newCharacterStrength.toString())

                alertDialog.dismiss()
                selectIdentity()
            } else {
                Toast.makeText(context, "Vul alle velden in aub", Toast.LENGTH_LONG).show()
            }
        }
        alertDialog.show()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //This function handles the alert dialog for name, alignment and gender of the character selection//
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun selectIdentity() {
        val (alertDialog, alertView) = makeAlert(R.layout.add_character_dialog_name, R.string.select_stats)

        val genderSpinner: Spinner = alertDialog.findViewById(R.id.spinnerGender)
        val alignmentSpinner: Spinner = alertDialog.findViewById(R.id.spinnerAlignment)

        val previousButton: Button = alertDialog.findViewById(R.id.btnNamePrevious)
        val nextButton: Button = alertDialog.findViewById(R.id.btnNameNext)

        previousButton.setOnClickListener {
            alertDialog.dismiss()
            selectStats()
        }

        nextButton.setOnClickListener {
            //TODO: Save the currently chosen options!!
            newCharacterName = alertDialog.findViewById<EditText>(R.id.etName).text.toString()
            newCharacterAlignment = getSelectedCharacterOptions(alignmentSpinner)
            newCharacterGender = getSelectedCharacterOptions(genderSpinner)

            alertDialog.dismiss()
            addNewCharacter()
        }

        alertDialog.show()
    }

    ////////////////////////////////////////
    //This function adds the new character//
    ///////////////////////////////////////
    private fun addNewCharacter() {
        val newCharacter = DnDCharacter(
            false,
            newCharacterName,
            newCharacterGender,
            newCharacterAlignment,
            1,
            "Note",//TODO: replace with string resource
            newCharacterBackground,
            newCharacterRace,
            newCharacterSubrace,
            newCharacterCClass)

        val strengthMod = calculateStatModifier(newCharacterStrength)
        val newStrength = Strength(
            newCharacterStrength,
            strengthMod,
            strengthMod,
            strengthMod,
            newCharacter.id
        )

        val dexterityMod = calculateStatModifier(newCharacterDexterity)
        val newDexterity = Dexterity(
            newCharacterDexterity,
            dexterityMod,
            dexterityMod,
            dexterityMod,
            dexterityMod,
            dexterityMod,
            newCharacter.id
        )

        val intelligenceMod = calculateStatModifier(newCharacterIntelligence)
        val newIntelligence = Intelligence(
            newCharacterIntelligence,
            intelligenceMod,
            intelligenceMod,
            intelligenceMod,
            intelligenceMod,
            intelligenceMod,
            intelligenceMod,
            intelligenceMod,
            newCharacter.id
        )

        val wisdomMod = calculateStatModifier(newCharacterWisdom)
        val newWisdom = Wisdom(
            newCharacterWisdom,
            wisdomMod,
            wisdomMod,
            wisdomMod,
            wisdomMod,
            wisdomMod,
            wisdomMod,
            wisdomMod,
            newCharacter.id
        )

        val charismaMod = calculateStatModifier(newCharacterCharisma)
        val newCharisma = Charisma(
            newCharacterCharisma,
            charismaMod,
            charismaMod,
            charismaMod,
            charismaMod,
            charismaMod,
            charismaMod,
            newCharacter.id
        )

        val constitutionMod = calculateStatModifier(newCharacterConstitution)
        val newConstitution = Constitution(
            newCharacterConstitution,
            constitutionMod,
            constitutionMod,
            newCharacter.id
        )

        viewModel.addCharacterToDatabase(newCharacter, newStrength, newDexterity, newIntelligence, newWisdom, newCharisma, newConstitution)
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

    ////////////////////////////////////////////////////////////////////
    //This function calculates the modifier based on the selected stat//
    ////////////////////////////////////////////////////////////////////
    private fun calculateStatModifier(statValue: Int): Int {
        var modifierValue = 0
        val calculation = statValue - 10
        var modifiedCalculationResult = 0

        if(calculation%2 != 0) {
            modifiedCalculationResult = calculation-1
        } else {
            modifiedCalculationResult = calculation
        }

        if(modifiedCalculationResult == 0) {
            modifierValue = 0
        } else {
            modifierValue = modifiedCalculationResult/2
        }

        return modifierValue
    }

    /*private fun buttonPressStats(statsView: View) {
        val strengthMin = statsView.findViewById<ImageButton>(R.id.ibStrMin)
        val strengthPlus = statsView.findViewById<ImageButton>(R.id.ibStrPlus)

        val dexterityMin = statsView.findViewById<ImageButton>(R.id.ibDexMin)
        val dexterityPlus = statsView.findViewById<ImageButton>(R.id.ibDexPlus)

        val intelligenceMin = statsView.findViewById<ImageButton>(R.id.ibIntMin)
        val intelligencePlus = statsView.findViewById<ImageButton>(R.id.ibIntPlus)

        val wisdomMin = statsView.findViewById<ImageButton>(R.id.ibWisMin)
        val wisdomPlus = statsView.findViewById<ImageButton>(R.id.ibWisPlus)

        val charismaMin = statsView.findViewById<ImageButton>(R.id.ibCharMin)
        val charismaPlus = statsView.findViewById<ImageButton>(R.id.ibCharPlus)

        val constitutionMin = statsView.findViewById<ImageButton>(R.id.ibConMin)
        val constitutionPlus = statsView.findViewById<ImageButton>(R.id.ibConPlus)
    }*/

    //////////////////////////////////////////////////////////
    //This function returns the cost of a certain stat value//
    //////////////////////////////////////////////////////////
    private fun calculatePointBuyCost(statValue: Int, remainingPoints: Int, typeOfButtonPressed: String): Pair<Int, Int> {
        var pointBuyCost = 0
        var isAllowed = true
        var evaluatedStat = 0
        var evaluatedPoints = 0

        val min = R.integer.minimumStatValueCharacterCreation
        val switch = R.integer.pointBuyMaximumValueForCostOfOne
        val max = R.integer.maximumStatValueCharacterCreation

        if(typeOfButtonPressed == "plus") {
            if(statValue in min..switch) {
                pointBuyCost = 1
                isAllowed = true
            } else if(statValue in (switch + 1)..max) {
                pointBuyCost = 2
                isAllowed = true
            } else {
                isAllowed = false
            }

            if(!isAllowed) {
                evaluatedStat = statValue
                evaluatedPoints = remainingPoints
            } else {
                evaluatedPoints = remainingPoints - pointBuyCost
                evaluatedStat = statValue+1
            }
        } else {
            if(statValue < min) {
                isAllowed = false
            } else if(statValue in (switch+1)..max) {
                pointBuyCost = 2
                isAllowed = true
            } else if(statValue in min..switch) {
                pointBuyCost = 1
                isAllowed = true
            }

            if(isAllowed) {
                evaluatedPoints = remainingPoints + pointBuyCost
                evaluatedStat = statValue-1
            } else {
                evaluatedStat = statValue
            }

        }

        return Pair(evaluatedPoints, evaluatedStat)
    }

    //This function switches the loaded fragment to be the home fragment
    private fun startHomeFragment(){
        parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
    }

    //This gets executed when the fragment is being left
    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}