package com.syntax_institut.whatssyntax.utils

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.databinding.ItemStatusBinding
import com.syntax_institut.whatssyntax.ui.StatusFragmentDirections

class StatusAdapter(
    contacts: List<Contact>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {
    private var contacts: List<Contact> = sortContacts(contacts)
    inner class StatusViewHolder(val binding: ItemStatusBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatusAdapter.StatusViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatusAdapter.StatusViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.apply {
            tvStatusName.text = contact.name
            ivStatusImage.load("http://81.169.201.230:8080/${contact.image}")

            var hasUnreadStatus = contact.status != null && !contact.status.isRead
            val saturation = if (hasUnreadStatus) 1f else 0f
            ivStatusImage.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply {
                setSaturation(saturation)
            })

            cvStatus.isClickable = hasUnreadStatus
            cvStatus.setOnClickListener {
                if (hasUnreadStatus) {
                    contact.markStatusAsRead()
                    val action = StatusFragmentDirections.actionStatusFragmentToStatusDetailFragment(
                        contact.status?.images.toString()
                    )
                    it.findNavController().navigate(action)
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun updateContacts(newContacts: List<Contact>) {
        contacts = sortContacts(newContacts)
        notifyDataSetChanged()
    }

    private fun sortContacts(contacts: List<Contact>): List<Contact> {
        return contacts.sortedWith(compareByDescending { it.status != null })
    }

}