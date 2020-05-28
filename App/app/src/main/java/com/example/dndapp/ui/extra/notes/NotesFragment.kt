package com.example.dndapp.ui.extra.notes.ui

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
        val root = inflater.inflate(R.layout.fragment_extra_notes, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            /*val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditActivity.EXTRA_NOTE, mainActivityViewModel.note.value)
            startActivity(intent)*/
            testFab()
        }
    }

    //@SuppressLint("StringFormatInvalid")
    private fun initViewModel() {
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        notesViewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
            if (notes != null) {
                etNotes.setText(notes.text)
            }
        })
    }

    private fun testFab() {
        Toast.makeText(notesViewModel.getApplication(), "Notes", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        //TODO: use this to save the notes!
    }
}
