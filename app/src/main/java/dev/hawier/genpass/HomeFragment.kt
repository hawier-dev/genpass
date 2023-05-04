package dev.hawier.genpass

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.hawier.genpass.databinding.FragmentHomeBinding
import dev.hawier.genpass.utils.PasswordGenerator

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val passwordGenerator = PasswordGenerator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.setListeners()
        this.generatePassword()
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setListeners(){
        /*
        * Set listeners for the buttons and switches
        */
        this.binding.genPasswd.setOnClickListener {
            generatePassword()
        }

        this.binding.copyButton.setOnClickListener {
            passwordGenerator.copyTextToClipboard(requireContext())
        }

        this.binding.lengthBar.addOnChangeListener { _, value, _ ->
            passwordGenerator.length = value.toInt()
            this.binding.lengthLabel.text = value.toInt().toString()
            generatePassword()
        }

        this.binding.uppercaseSwitch.setOnCheckedChangeListener { _, isChecked ->
            passwordGenerator.uppercase = isChecked
            generatePassword()
        }

        this.binding.numbersSwitch.setOnCheckedChangeListener { _, isChecked ->
            passwordGenerator.numbers = isChecked
            generatePassword()
        }

        this.binding.symbolsSwitch.setOnCheckedChangeListener { _, isChecked ->
            passwordGenerator.symbols = isChecked
            generatePassword()
        }
    }
    private fun generatePassword() {
        passwordGenerator.generatePassword()
        this.binding.passwdText.text = passwordGenerator.password
        this.binding.ratingBar.rating = passwordGenerator.rating.toFloat()

        if (passwordGenerator.rating == 0) {
            this.binding.ratingLabel.text = "Very weak"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#ff7575"))
        } else if (passwordGenerator.rating == 1) {
            this.binding.ratingLabel.text = "Weak"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#ff7575"))
        } else if (passwordGenerator.rating == 2) {
            this.binding.ratingLabel.text = "Medium"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#f7c163"))
        } else if (passwordGenerator.rating == 3) {
            this.binding.ratingLabel.text = "Strong"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#45de8a"))
        } else if (passwordGenerator.rating == 4) {
            this.binding.ratingLabel.text = "Very strong"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#45de8a"))
        } else if (passwordGenerator.rating == 5) {
            this.binding.ratingLabel.text = "Extremely strong"
            this.binding.ratingLabel.setTextColor(Color.parseColor("#45de8a"))
        }
    }
}