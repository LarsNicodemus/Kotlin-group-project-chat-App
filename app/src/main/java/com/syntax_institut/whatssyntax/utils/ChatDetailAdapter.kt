package com.syntax_institut.whatssyntax.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.Message
import com.syntax_institut.whatssyntax.databinding.ItemChatInBinding
import com.syntax_institut.whatssyntax.databinding.ItemChatOutBinding

class ChatDetailAdapter(
    private val messageList: MutableList<Message>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class IncomingViewHolder(val binding: ItemChatInBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class OutgoingViewHolder(val binding: ItemChatOutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val incomeMessage = 1
    private val outgoingMessage = 2

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].incoming) {
            incomeMessage
        } else {
            outgoingMessage
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == incomeMessage) {
            val binding =
                ItemChatInBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            IncomingViewHolder(binding)
        } else {
            val binding =
                ItemChatOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            OutgoingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]

        if (holder is IncomingViewHolder) {
            holder.binding.apply {
                tvMessageIn.text = message.text
            }
        } else if (holder is OutgoingViewHolder) {
            holder.binding.apply {
                tvMessageOut.text = message.text
            }
        }
    }

    fun updateMessages(newMessages: List<Message>) {
        messageList.clear()
        messageList.addAll(newMessages)
        notifyDataSetChanged()
    }
}