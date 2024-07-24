package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            binding.ivProfile.load(BASE_URL + profile.image) {
                transformations(CircleCropTransformation())
            }
            binding.tietProfileName.setText(profile.name)
            binding.tietProfileNumber.setText(profile.number)
        }

        binding.btProfileSave.setOnClickListener {
            val name = binding.tietProfileName.text.toString()
            val number = binding.tietProfileNumber.text.toString()
            val newProfile = Profile(name, number, viewModel.profile.value?.image)

            if (viewModel.isValidName(name) && viewModel.isValidPhoneNumber(number)) {
                viewModel.updateProfile(newProfile)
                findNavController().navigateUp()
            } else {

                if (!viewModel.isValidName(name)) {
                    binding.tietProfileName.error = "Ungültiger Name"
                }
                if (!viewModel.isValidPhoneNumber(number)) {
                    binding.tietProfileNumber.error = "Ungültige Telefonnummer"
                }
            }
        }
    }
}