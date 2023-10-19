package com.ieseljust.whatsdamgingerbread


class Messages(
    var usuari:String,
    var mensage:String
)

object Message{

    var llistatMsn =mutableListOf<Messages>()


    fun add (usuari:String, text:String){

        var mensage = Messages(usuari,text)
        llistatMsn.add(mensage)
    }
}