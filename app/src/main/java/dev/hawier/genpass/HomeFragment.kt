package dev.hawier.genpass

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
        setListeners()
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
    }
}