package com.ieseljust.whatsdamgingerbread.ViewModels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ieseljust.whatsdamgingerbread.model.Mensages
import com.ieseljust.whatsdamgingerbread.model.Message
import com.ieseljust.whatsdamgingerbread.R

class AdaptadorMensage(var listaMensajes: List<Mensages>) :RecyclerView.Adapter<MessageViewHolder>(){

    /*
    // Funció que crea una nova vista d'element en la llista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater=LayoutInflater.from((parent.context))
        val vista= inflater.inflate(
            R.layout.my_msg,
            parent, false)
        return MessageViewHolder(vista)
    }

    /*
    // Funció que actualitza una vista d'element amb dades específiques.
    */
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(listaMensajes[position])
    }

    /*
    // Funció per a obtindre la quantitar d'elements en la llista.
     */
    override fun getItemCount(): Int {
        return Message.llistatMsn.size
    }




}