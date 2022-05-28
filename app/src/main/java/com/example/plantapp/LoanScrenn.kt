package com.example.plantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.plantapp.databinding.LoanScreenBinding

const val VALOR_MAXIMO = 8
var VALOR_MINIMO = 4


class LoanScrenn : AppCompatActivity() {

    private lateinit var binding: LoanScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoanScreenBinding.inflate(layoutInflater)
        val view = binding.root
        binding.hint.setText("4")
        setContentView(view)
        IntentSucesso()
        observaEditText()
    }

    private fun vaiParaSucesso() {
        val intent = Intent(this, Sucesso::class.java)
        startActivity(intent)
    }

    private fun observaEditText() {
        val quantidadeDigitada = binding.hint.text.toString()
        binding.hint.addTextChangedListener {
            if (!quantidadeDigitada.isNullOrBlank()) {
                gerenciaCampoEditText()
//                Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun gerenciaCampoEditText() {
        val quantidadeDigitada = binding.hint.text.toString()
        when {
            quantidadeDigitada > VALOR_MAXIMO.toString() -> {
                binding.buttonVender.setBackgroundResource(R.drawable.buttom_shape_desativado)
                binding.buttonVender.isClickable = false
                binding.textInputLayout.isHelperTextEnabled = true
                binding.textInputLayout.error = getString(R.string.helperValorMaximo)
            }
            quantidadeDigitada < VALOR_MINIMO.toString() -> {
                binding.textInputLayout.error = getString(R.string.helper)
                binding.buttonVender.setBackgroundResource(R.drawable.buttom_shape_desativado)
                binding.buttonVender.isClickable = false
                binding.textInputLayout.isHelperTextEnabled = true
            }
            else -> {
                binding.textInputLayout.error = ""
                binding.buttonVender.setBackgroundResource(R.drawable.buttom_shape_ativado)
                binding.buttonVender.isClickable = true

            }
        }
    }

    private fun IntentSucesso() {
        binding.buttonVender.setOnClickListener() {
            vaiParaSucesso()
        }
    }
}

