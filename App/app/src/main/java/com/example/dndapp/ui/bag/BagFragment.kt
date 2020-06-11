package com.example.dndapp.ui.bag

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import com.example.dndapp.model.BagAdapter
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.Money
import kotlinx.android.synthetic.main.fragment_bag.*

class BagFragment : Fragment() {
    private lateinit var parentActivity: Activity
    private val bagItems = arrayListOf<BagItem>()
    private val bagAdapter = BagAdapter(bagItems) {bagItem ->  onItemClick(bagItem)}
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity?)?.checkCurrentFragment()

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!

        //Making the recyclerview for the list of all characters
        rvBagItem.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvBagItem.adapter = bagAdapter
        rvBagItem.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //Make the floating action button call the newCharacter() function
        fab.setOnClickListener {
            addItem()
        }
    }

    private fun observeViewModel() {
        viewModel.bagItems.observe(this, Observer { bagItems ->
            this.bagItems.clear()
            this.bagItems.addAll(bagItems)
            bagAdapter.notifyDataSetChanged()
        })

        viewModel.money.observe(this, Observer {money ->
            if(money != null) {
                etPlatinum.setText(money.amountOfPlatinum.toString())
                etGold.setText(money.amountOfGold.toString())
                etElectrum.setText(money.amountOfElectrum.toString())
                etSilver.setText(money.amountOfSilver.toString())
                etCopper.setText(money.amountOfCopper.toString())
            }
        })
    }

    //This function makes an alert dialog TODO: standaard functies zoals deze in een apart bestand maken
    private fun makeAlert(layout: Int, title: Int): Pair<AlertDialog, View> {
        val dialogView = LayoutInflater.from(context).inflate(layout, null)
        val newAlertBuilder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setView(dialogView)
            .setTitle(title)

        return Pair(newAlertBuilder.show(), dialogView)
    }

    private fun addItem() {
        val (alertDialog, alertView) = makeAlert(R.layout.add_bagitem_dialog, R.string.title_add_item)

        val exitButton: Button = alertDialog.findViewById(R.id.btnItemCancel)
        val addButton: Button = alertDialog.findViewById(R.id.btnItemAdd)

        val amountInputField = alertDialog.findViewById<EditText>(R.id.etItemAmount)
        val nameInputField = alertDialog.findViewById<EditText>(R.id.etItemName)
        val weightInputField = alertDialog.findViewById<EditText>(R.id.etItemWeight)

        exitButton.setOnClickListener {
            alertDialog.dismiss()
        }

        addButton.setOnClickListener {
            if( amountInputField.text.toString() != "" &&
                nameInputField.text.toString() != "" &&
                weightInputField.text.toString() != ""
                    ) {
                val amount = amountInputField.text.toString().toInt()
                val name = nameInputField.text.toString()
                val weight = weightInputField.text.toString().toInt()

                viewModel.currentCharacterID.observe(this, Observer {currentCharacterID ->
                    if(currentCharacterID != null) {
                        val newBagItem = BagItem(amount, name, weight, currentCharacterID.toLong())
                        viewModel.addItemToDatabase(newBagItem)
                    } else {
                        Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show()
                    }
                })


                alertDialog.dismiss()
            } else {
                //TODO: make string value
                Toast.makeText(context, "Vul iets in, alsjeblieft", Toast.LENGTH_LONG).show()
            }
        }

        alertDialog.show()
    }

    private fun onItemClick(bagItem: BagItem) {

    }

    private fun saveMoney() {
        Toast.makeText(viewModel.getApplication(), R.string.saving_text, Toast.LENGTH_SHORT).show()
        var platinum = etPlatinum.text.toString().toInt()
        var gold = etGold.text.toString().toInt()
        var electrum = etElectrum.text.toString().toInt()
        var silver = etSilver.text.toString().toInt()
        var copper = etCopper.text.toString().toInt()
        val saveableMoney = Money(platinum, gold, electrum, silver, copper)
        viewModel.note.value?.apply {
            viewModel.note = MutableLiveData(etNotes.text.toString())
        }

        viewModel.currentCharacterID.observe(viewLifecycleOwner, Observer { currentCharacterID ->
            viewModel.updateNotes(currentCharacterID)
        })

        Toast.makeText(viewModel.getApplication(), R.string.saved_text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}