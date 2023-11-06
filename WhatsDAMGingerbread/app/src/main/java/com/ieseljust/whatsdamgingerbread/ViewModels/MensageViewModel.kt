package com.ieseljust.whatsdamgingerbread.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ieseljust.whatsdamgingerbread.model.Message.llistatMsn
import com.ieseljust.whatsdamgingerbread.repositori.MessagesRepository

class MessagesViewModel(application: Application):AndroidViewModel(application){

    private val _adaptador = MutableLiveData<AdaptadorMensage>().apply {
        value = AdaptadorMensage(llistatMsn)
    }


    /**
     * Afig un mensaje fent crida la funció addMessages del Repositori i notifica al adaptador.
     */
    fun add(nom: String, mText: String){
        MessagesRepository.getInstance().addMessage(nom,mText)
        _adaptador.value?.notifyItemInserted(MessagesRepository.getInstance().getLlistaSize() - 1)
    }
}