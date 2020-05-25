package com.example.dndapp.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dndapp.R

class BagFragment : Fragment() {

    private lateinit var bagViewModel: BagViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bagViewModel =
            ViewModelProvider(this).get(BagViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bag, container, false)
        val textView: TextView = root.findViewById(R.id.text_bag)
        bagViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}