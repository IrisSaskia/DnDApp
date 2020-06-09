package com.example.dndapp.ui.extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.MainActivity
import com.example.dndapp.MainViewModel
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_settings.*

class SettingsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.checkCurrentFragment()

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            saveSettings()
        }
    }

    private fun initViewModel() {

    }

    private fun saveSettings() {
        Toast.makeText(viewModel.getApplication(), R.string.saving_text, Toast.LENGTH_SHORT).show()

        Toast.makeText(viewModel.getApplication(), R.string.saved_text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveSettings()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}