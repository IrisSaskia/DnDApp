package com.example.dndapp.ui.extra.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_notes.*

class NotesFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            saveNotes()
        }
    }

    //@SuppressLint("StringFormatInvalid")
    private fun initViewModel() {
        notesViewModel.notesBeforeSending.observe(viewLifecycleOwner, Observer { notes ->
            if (notes != null) {
                etNotes.setText(notes.text)
            }
        })
    }

    private fun saveNotes() {
        Toast.makeText(notesViewModel.getApplication(), "Saving...", Toast.LENGTH_LONG).show()
        notesViewModel.notes.value?.apply {
            text = etNotes.text.toString()
        }

//        notesViewModel.notes.value!!.text = etNotes.text.toString()
        notesViewModel.updateNotes()


    }

    /*override fun onPause() {
        super.onPause()

        //TODO: use this to save the notes!
    }*/
}