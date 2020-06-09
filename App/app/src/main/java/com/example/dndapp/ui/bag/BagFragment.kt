package com.example.dndapp.ui.bag

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    private fun addItem() {

    }

    private fun onItemClick(bagItem: BagItem) {

    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity?)?.checkCurrentFragment()
    }
}