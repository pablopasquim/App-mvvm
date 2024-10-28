package com.example.mvvm.model

class Validacao{

    companion object{

        private var  autoId = 0

        fun getId() : Int{
            return autoId++
        }

        fun haCamposVazios(titulo: String, autor: String) : Boolean{
            return titulo.isBlank() || autor.isBlank()
        }
    }
}