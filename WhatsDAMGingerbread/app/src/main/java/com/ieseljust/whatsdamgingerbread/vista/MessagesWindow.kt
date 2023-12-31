package com.ieseljust.whatsdamgingerbread.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieseljust.whatsdamgingerbread.ViewModels.AdaptadorMensage
import com.ieseljust.whatsdamgingerbread.model.Mensages
import com.ieseljust.whatsdamgingerbread.model.Message.llistatMsn
import com.ieseljust.whatsdamgingerbread.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        val view2 = binding.root
        setContentView(view2)

        /**
         *Los intentos de intent los guardamos en variables
         * Buscamos connectionInfoTextView y le indicamos la cadena de texto
         **/

        val mensage = intent.getStringExtra("nickName")
        val ipValue = intent.getStringExtra(" ip")

        binding.connectionInfoTextView.text = "Conectado a " +ipValue+ " como " +mensage

        val mensageText=binding.MessageText
        val enviarMensage=binding.sendMessage

        val recyclerView=binding.MessagesRecyclerView

        val layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter = AdaptadorMensage(llistatMsn)
        recyclerView.adapter =adapter


        enviarMensage.setOnClickListener(){
            llistatMsn.add(Mensages(mensage.toString(),mensageText.text.toString()))
            binding.MessagesRecyclerView.adapter?.notifyItemInserted(llistatMsn.size-1)
            mensageText.text.clear()
        }
    }
}