package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.databinding.FragmentNotesBinding
import com.syntax_institut.whatssyntax.utils.NotesAdapter

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvNotes
        adapter = NotesAdapter(mutableListOf(), viewModel)
        recyclerView.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) {notesList ->
            adapter.updateNotes(notesList.toMutableList())
            Log.d("NotesFragment", "Observed new notes list: $notesList")
        }
        binding.fabAdd.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }

}