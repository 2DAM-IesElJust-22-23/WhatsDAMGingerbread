package com.ieseljust.whatsdamgingerbread.repositori

import com.ieseljust.whatsdamgingerbread.model.Message

class MessagesRepository private constructor() {

    companion object{
        private var INSTANCE : MessagesRepository? = null

        fun getInstance():MessagesRepository{

            if(INSTANCE == null){
                INSTANCE = MessagesRepository()
            }

            return INSTANCE !!
        }
    }

    /**
     * Obté la llistaMensajes.
     */
    fun getLlistaMensajes() = Message.llistatMsn

    /**
     * Obté el tamany de la llistaMensajes.
     */
    fun getLlistaSize() = Message.llistatMsn.size

    /**
     * Afig un mensaje a la llistaMensajes.
     */
    fun addMessage(nom:String, mText:String) {
        Message.add(nom, mText)
    }
}