package com.example.dndapp.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CharacterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is character Fragment"
    }
    val text: LiveData<String> = _text
}