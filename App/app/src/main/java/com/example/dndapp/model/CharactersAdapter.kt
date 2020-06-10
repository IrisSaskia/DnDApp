package com.example.dndapp.model

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val dndCharacters: List<DnDCharacter>,
                       private val onSelect: (DnDCharacter) -> Unit,
                       private val onDelete: (DnDCharacter) -> Unit) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        )
    }
    /* Returns the size of the list*/

    override fun getItemCount(): Int {
        return dndCharacters.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dndCharacters[position])
    }




    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(dndCharacter: DnDCharacter){
            itemView.tvCharacterNameLevel.text = context.getString(R.string.character_in_list, dndCharacter.name, dndCharacter.level.toString())
            itemView.tvCharacterClassRace.text = dndCharacter.race //TODO: add class
        }
        init {
            itemView.findViewById<ConstraintLayout>(R.id.clCharacterInfo).setOnClickListener { onSelect(dndCharacters[adapterPosition]) }
            itemView.findViewById<ImageButton>(R.id.ibDeleteCharacter).setOnClickListener { onDelete(dndCharacters[adapterPosition]) }
        }
    }
}
