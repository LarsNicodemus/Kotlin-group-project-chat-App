package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.datamodel.Note
import com.syntax_institut.whatssyntax.databinding.FragmentNoteDetailBinding


class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNote(args.id).observe(viewLifecycleOwner) { note ->
            if (note != null) {
                binding.apply {
                    addTitleInputText.setText(note.title)
                    addTextInputText.setText(note.content)
                    btnEdit.setOnClickListener {
                        val noteText = addTextInputText.text.toString()
                        val noteTitle = addTitleInputText.text.toString()
                        val updatedNote = Note(note.id, noteTitle, noteText)
                        viewModel.updateNote(updatedNote)
                        findNavController().navigateUp()
                    }
                btnDelete.setOnClickListener {
                    viewModel.deleteNote(note.id)
                    findNavController().navigateUp()
                }
                btnCancel.setOnClickListener {
                    findNavController().navigateUp()
                }
            }

        }
    }
}
}