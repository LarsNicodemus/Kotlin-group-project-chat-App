package com.syntax_institut.whatssyntax.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.ChatList
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.ItemChatBinding
import com.syntax_institut.whatssyntax.ui.ChatsFragmentDirections

class ChatAdapter(
    chatList: List<ChatList>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private var chats: List<ChatList> = chatList

    inner class ChatViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
        val contact = chat.contact
        holder.binding.apply {
            tvChatContactName.text = contact.name
            tvChatLastMess.text = chat.lastMessage.text
            ivChatContactImage.load(BASE_URL + contact.image)

            cvChat.setOnClickListener {
                viewModel.selectContact(contact)
                val action =
                    ChatsFragmentDirections.actionChatsFragmentToChatDetailFragment(chat.id)
                it.findNavController().navigate(action)
                notifyItemChanged(position)
            }
        }
    }

    fun updateChats(newChats: List<ChatList>) {
        Log.d("ChatAdapter", "Updating chats: $newChats")
        chats = newChats
        notifyDataSetChanged()
    }

}