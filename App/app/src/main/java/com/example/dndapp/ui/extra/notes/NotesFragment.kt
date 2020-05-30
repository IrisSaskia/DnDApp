package com.example.dndapp.ui.extra.notes

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_notes.*
import kotlin.toString as toString

class NotesFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var parentActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

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
        notesViewModel.note.observe(viewLifecycleOwner, Observer { note ->
            if (note != null) {
                etNotes.setText(note)
            }
        })
    }

    private fun saveNotes() {
        Toast.makeText(notesViewModel.getApplication(), R.string.saving_text, Toast.LENGTH_SHORT).show()
        notesViewModel.note.value?.apply {
            notesViewModel.note = MutableLiveData(etNotes.text.toString())
        }

        notesViewModel.updateNotes()

        Toast.makeText(notesViewModel.getApplication(), R.string.saved_text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveNotes()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}