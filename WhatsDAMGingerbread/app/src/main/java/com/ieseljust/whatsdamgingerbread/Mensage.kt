package com.ieseljust.whatsdamgingerbread


class Mensages(
    var usuari:String,
    var mensage:String
)

object Message{

    var llistatMsn =mutableListOf<Mensages>()


    fun add (usuari:String, text:String){

        var mensage = Mensages(usuari,text)
        llistatMsn.add(mensage)
    }
}