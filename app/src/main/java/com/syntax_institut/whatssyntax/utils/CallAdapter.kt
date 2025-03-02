package com.syntax_institut.whatssyntax.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.datamodel.Call
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.ItemCallBinding

class CallAdapter(
    callList: List<Call>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<CallAdapter.ViewHolder>() {
    private var contacts: List<Call> = callList

    inner class ViewHolder(val binding: ItemCallBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val callResponse = contacts[position]
        val contact = contacts[position].contact
        holder.binding.apply {
            ivCallContactImage.load(BASE_URL + contact.image)
            tvCallName.text = contact.name
            tvCallTime.text = callResponse.time
            val imageResource = imageResource(callResponse)
            ivCallStatus.setImageResource(imageResource)
            ivCallStatus.rotation = if (callResponse.incoming) 180f else 0f
        }
    }

    private fun imageResource(call: Call): Int {
        val imageResource = when {
            call.incoming && call.accepted -> R.drawable.icon_call_accepted
            !call.incoming && call.accepted -> R.drawable.icon_call_accepted
            call.incoming && !call.accepted -> R.drawable.icon_call_missed
            else -> R.drawable.icon_call_missed
        }
        return imageResource
    }

}