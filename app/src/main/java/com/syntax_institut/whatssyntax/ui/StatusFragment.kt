package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.databinding.FragmentStatusBinding
import com.syntax_institut.whatssyntax.utils.StatusAdapter

class StatusFragment : Fragment() {

    private lateinit var binding: FragmentStatusBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvContacts



        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            Log.d("StatusFragment", "Contacts: $contacts")
            recyclerView.adapter = StatusAdapter(contacts, viewModel)
        }

    }

}