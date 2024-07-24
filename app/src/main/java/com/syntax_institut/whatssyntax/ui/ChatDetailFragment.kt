package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.Message
import com.syntax_institut.whatssyntax.databinding.FragmentChatDetailBinding
import com.syntax_institut.whatssyntax.utils.ChatDetailAdapter

class ChatDetailFragment : Fragment() {

    private lateinit var binding: FragmentChatDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: ChatDetailFragmentArgs by navArgs()
    private lateinit var adapter: ChatDetailAdapter
    private val handler = Handler(Looper.getMainLooper())
    private val pollInterval: Long = 3000 // 3 Sekunden

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chatContactID = args.contactId
        viewModel.getChat(chatContactID)
        val recyclerView = binding.rvMessages

        adapter = ChatDetailAdapter(mutableListOf(), viewModel)
        recyclerView.adapter = adapter

        viewModel.chat.observe(viewLifecycleOwner) { messageList ->
            adapter.updateMessages(messageList)
            recyclerView.scrollToPosition(messageList.size - 1)
        }

        binding.btSend.setOnClickListener {
            val messageText = binding.tietMessage.text.toString()
            if (messageText.isNotEmpty()) {
                val message = Message(messageText, false)
                viewModel.sendMessage(chatContactID, message)
                binding.tietMessage.setText("")
            }
        }
        startPolling()
    }

    private fun startPolling() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                viewModel.getChat(args.contactId)
                handler.postDelayed(this, pollInterval)
            }
        }, pollInterval)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }
}
