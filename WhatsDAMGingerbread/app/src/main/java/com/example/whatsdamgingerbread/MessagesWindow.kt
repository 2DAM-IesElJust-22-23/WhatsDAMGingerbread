package com.example.whatsdamgingerbread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ieseljust.pmdm.whatsdamjellybean.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {

    private lateinit var binding: ActivityMessagesWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view2 = binding.root
        setContentView(view)

        /**
         *Los intentos de intent los guardamos en variables
         * Buscamos connectionInfoTextView y le indicamos la cadena de texto
         **/

       val mensage = intent.getStringExtra("nickName")
        val ipValue = intent.getStringExtra(" ip")
        binding.connecttionInfoTextView.text = "Conectado a "+ipValue+" como "+mensage

        binding.sendMensage.setOnClickListener{
            binding.MessageText.text.clear()
        }
    }
}