package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding

class StatusDetailFragment: Fragment() {

    private lateinit var binding: FragmentStatusDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: StatusDetailFragmentArgs by navArgs()
    private var currentImageIndex = 0
    private lateinit var images: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatusDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        images = args.status.trim('[', ']').split(", ").filter { it.isNotEmpty() }
        if (images.isNotEmpty()) {
            showImage(currentImageIndex)
        } else {
            navigateBack()
        }
        binding.ivStatus.setOnClickListener {
            currentImageIndex++
            if (currentImageIndex < images.size) {
                showImage(currentImageIndex)
            } else {
                navigateBack()
            }
        }
        viewModel.markCurrentStatusAsRead()
    }
private fun showImage(index: Int) {
    val imageUrl = "http://81.169.201.230:8080/${images[index]}"
    binding.ivStatus.load(imageUrl)
}

private fun navigateBack() {
    findNavController().navigateUp()
}
}