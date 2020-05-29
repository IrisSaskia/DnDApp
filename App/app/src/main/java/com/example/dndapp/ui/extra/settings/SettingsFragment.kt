package com.example.dndapp.ui.extra.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.R
import kotlinx.android.synthetic.main.fragment_extra_settings.*

class SettingsFragment : Fragment() {
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_extra_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        Toast.makeText(settingsViewModel.getApplication(), R.string.saving_text, Toast.LENGTH_SHORT).show()

        Toast.makeText(settingsViewModel.getApplication(), R.string.saved_text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveSettings()
    }
}