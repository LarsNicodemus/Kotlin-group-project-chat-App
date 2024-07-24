package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.databinding.FragmentChatsBinding
import com.syntax_institut.whatssyntax.utils.ChatAdapter

class ChatsFragment : Fragment() {

    private lateinit var binding: FragmentChatsBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var chatAdapter: ChatAdapter

    override fun onResume() {
        super.onResume()
        Log.d("ChatsFragment", "onResume called")
        viewModel.refreshChats()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvChats
        chatAdapter = ChatAdapter(emptyList(), viewModel)
        recyclerView.adapter = chatAdapter
        viewModel.chats.observe(viewLifecycleOwner) { chatList ->
            chatAdapter.updateChats(chatList)
            Log.d("ChatsFragment", "Observed new chat list: $chatList")
        }

    }

}