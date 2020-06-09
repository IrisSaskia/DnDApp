package com.example.dndapp.ui.extra

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_notes.*
import kotlin.toString as toString

class NotesFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var parentActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        parentActivity = activity!!

        fab.setOnClickListener {
            saveNotes()
        }
    }

    private fun initViewModel() {
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            if (note != null) {
                etNotes.setText(note)
            }
        })
    }

    private fun saveNotes() {
        Toast.makeText(viewModel.getApplication(), R.string.saving_text, Toast.LENGTH_SHORT).show()
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

        saveNotes()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}