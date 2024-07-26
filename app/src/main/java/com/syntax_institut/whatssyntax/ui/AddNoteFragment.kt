package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.datamodel.Note
import com.syntax_institut.whatssyntax.databinding.FragmentAddNoteBinding
import com.syntax_institut.whatssyntax.databinding.FragmentNoteDetailBinding

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val noteTitle = binding.addTextInputTitle.text.toString()
            val noteText = binding.addTextInputText.text.toString()
            if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {
                val newNote = Note(0, noteTitle, noteText)
                viewModel.insertNote(newNote)
                findNavController().navigateUp()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}