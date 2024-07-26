package com.syntax_institut.whatssyntax.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.ChatList
import com.syntax_institut.whatssyntax.data.datamodel.Note
import com.syntax_institut.whatssyntax.databinding.ItemNoteBinding
import com.syntax_institut.whatssyntax.databinding.ItemStatusBinding
import com.syntax_institut.whatssyntax.ui.NotesFragmentDirections

class NotesAdapter(
    private var notes: MutableList<Note>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.apply {
            tvNoteName.text = note.title
            tvNoteText.text = note.content

            cvNote.setOnClickListener {
                val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(note.id)
                it.findNavController().navigate(action)
                notifyItemChanged(position)
            }

        }
    }
    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateNotes(newNotes: MutableList<Note>) {
        Log.d("ChatAdapter", "Updating chats: $newNotes")
        notes = newNotes
        notifyDataSetChanged()
    }
}