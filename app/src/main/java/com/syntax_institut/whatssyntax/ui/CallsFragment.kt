package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.databinding.FragmentCallsBinding
import com.syntax_institut.whatssyntax.utils.CallAdapter

class CallsFragment : Fragment() {

    private lateinit var binding: FragmentCallsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvCalls
        viewModel.callResponse.observe(viewLifecycleOwner) {response ->

            Log.d("StatusFragment", "Contacts: $response")
            recyclerView.adapter = CallAdapter(response,viewModel)
        }
    }
}